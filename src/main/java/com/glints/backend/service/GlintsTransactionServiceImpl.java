package com.glints.backend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.entity.PurchaseHistory;
import com.glints.backend.dao.entity.RestaurantDetails;
import com.glints.backend.dao.entity.UserDetails;
import com.glints.backend.dao.service.DaoService;
import com.glints.backend.request.MenuDetailsRequest;
import com.glints.backend.request.PurchaseOrderDetailsRequest;
import com.glints.backend.request.TransactionDetailsRequest;
import com.glints.backend.response.MenuDetailsResponse;
import com.glints.backend.response.TransactionCountResponse;

@Service
public class GlintsTransactionServiceImpl implements GlintsTransactionService {

	@Autowired
	private DaoService daoService;

	@Override
	public List<TransactionCountResponse> fetchPopularRestaurants(TransactionDetailsRequest transactionDetailsRequest) {
		if (ApplicationConstant.TRANSACTION_COUNT.equalsIgnoreCase(transactionDetailsRequest.getFetchType())) {
			return daoService.fetchPopularRestaurantByCount();
		} else if (ApplicationConstant.TRANSACTION_AMOUNT.equalsIgnoreCase(transactionDetailsRequest.getFetchType())) {
			return daoService.fetchPopularRestaurantByAmount();
		}
		return null;
	}

	@Override
	public boolean placeOrder(PurchaseOrderDetailsRequest purchaseOrderDetailsRequest) {
		if (null != purchaseOrderDetailsRequest.getMenuDetails()) {
			List<String> dishNames = new ArrayList<>();
			Map<String, Double> itemOrderAmountMap = new HashMap<>();
			purchaseOrderDetailsRequest.getMenuDetails()
					.forEach(menuDetails -> addDishNamesToList(menuDetails, dishNames));
			List<MenuDetailsResponse> menuDetails = daoService
					.fetchMenuDetailsbyDishNames(purchaseOrderDetailsRequest.getRestaurantName(), dishNames);
			Double totalOrderAmount = calculateTotalOrderAmount(menuDetails, dishNames, purchaseOrderDetailsRequest,
					itemOrderAmountMap);
			updatePurchaseInfo(itemOrderAmountMap, totalOrderAmount, purchaseOrderDetailsRequest);
		}
		return false;
	}

	private void updatePurchaseInfo(Map<String, Double> itemOrderAmountMap, Double totalOrderAmount,
			PurchaseOrderDetailsRequest purchaseOrderDetailsRequest) {
		List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
		RestaurantDetails restaurantDetails = daoService
				.findByRestaurantName(purchaseOrderDetailsRequest.getRestaurantName());
		if (null != restaurantDetails) {
			restaurantDetails.setCashBalance(restaurantDetails.getCashBalance() + totalOrderAmount);
			daoService.saveRestaurantDetails(restaurantDetails);
		}
		Optional<UserDetails> optionalUserDetails = daoService.findbyUserId(purchaseOrderDetailsRequest.getUserId());
		if (optionalUserDetails.isPresent()) {
			UserDetails userDetails = optionalUserDetails.get();
			userDetails.setCashBalance(userDetails.getCashBalance()-totalOrderAmount);
			daoService.saveUserDetails(userDetails);
		}
		addPurchaseHistoryToList(itemOrderAmountMap, purchaseHistoryList, purchaseOrderDetailsRequest);
		daoService.savePurchaseHistory(purchaseHistoryList);

	}

	private void addPurchaseHistoryToList(Map<String, Double> itemOrderAmountMap,
			List<PurchaseHistory> purchaseHistoryList, PurchaseOrderDetailsRequest purchaseOrderDetailsRequest) {
		itemOrderAmountMap.forEach((dishName, price) -> setPurchaseDetails(dishName, price, purchaseOrderDetailsRequest,
				purchaseHistoryList));
	}

	private void setPurchaseDetails(String dishName, Double price,
			PurchaseOrderDetailsRequest purchaseOrderDetailsRequest, List<PurchaseHistory> purchaseHistoryList) {
		PurchaseHistory purchaseHistory = new PurchaseHistory();
		purchaseHistory.setDishName(dishName);
		purchaseHistory.setTransactionAmount(price);
		purchaseHistory.setTransactionDate(new SimpleDateFormat("dd/MM/yyyy hh:mm aa").format(new Date()));
		purchaseHistory.setRestaurantName(purchaseOrderDetailsRequest.getRestaurantName());
		UserDetails userDetails = new UserDetails();
		userDetails.setId(purchaseOrderDetailsRequest.getUserId());
		purchaseHistory.setUserDetails(userDetails);
		purchaseHistoryList.add(purchaseHistory);
	}

	private Double calculateTotalOrderAmount(List<MenuDetailsResponse> menuDetails, List<String> dishNames,
			PurchaseOrderDetailsRequest purchaseOrderDetailsRequest, Map<String, Double> itemOrderAmountMap) {
		Double totalOrderAmount = 0.0;
		if (null != menuDetails) {

			Map<String, Double> itemPriceMap = menuDetails.stream()
					.collect(Collectors.toMap(MenuDetailsResponse::getDishName, MenuDetailsResponse::getPrice));
			totalOrderAmount = purchaseOrderDetailsRequest.getMenuDetails().stream()
					.mapToDouble(menu -> setItemOrderAmountMap(menu, itemPriceMap, itemOrderAmountMap)).sum();
		}
		return totalOrderAmount;
	}

	private Double setItemOrderAmountMap(MenuDetailsRequest menu, Map<String, Double> itemPriceMap,
			Map<String, Double> itemOrderAmountMap) {
		Double totalAmount = itemPriceMap.get(menu.getDishName()) * menu.getQuantity();
		itemOrderAmountMap.put(menu.getDishName(), totalAmount);
		return totalAmount;
	}

	private void addDishNamesToList(MenuDetailsRequest menuDetails, List<String> dishNames) {
		dishNames.add(menuDetails.getDishName());
	}

}
