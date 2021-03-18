package com.glints.backend.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.entity.PurchaseHistory;
import com.glints.backend.dao.entity.RestaurantDetails;
import com.glints.backend.dao.entity.UserDetails;
import com.glints.backend.dao.repository.MenuDetailsRepository;
import com.glints.backend.dao.repository.OpeningHoursRepository;
import com.glints.backend.dao.repository.PurchaseHistoryRepository;
import com.glints.backend.dao.repository.RestaurantDetailsRepository;
import com.glints.backend.dao.repository.UserDetailsRepository;
import com.glints.backend.response.MenuDetailsResponse;
import com.glints.backend.response.RestaurantDetailsResponse;
import com.glints.backend.response.TransactionCountResponse;
import com.glints.backend.response.UserDetailsResponse;

@Service
public class DaoServiceImpl implements DaoService {

	@Autowired
	private RestaurantDetailsRepository restuarantRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private OpeningHoursRepository openingHoursRepository;

	@Autowired
	private MenuDetailsRepository menuDetailsRepository;

	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;

	@Override
	public boolean insertRestaurantData(List<RestaurantDetails> entityObjects) {
		if (null != entityObjects && !entityObjects.isEmpty()) {
			try {
				restuarantRepository.saveAll(entityObjects);
			} catch (Exception ex) {
				System.out.println("Exception Occured in DB Call");
				return false;
			}
		}
		return true;

	}

	@Override
	public boolean insertUserData(List<UserDetails> entityObjects) {
		if (null != entityObjects && !entityObjects.isEmpty()) {
			try {
				userDetailsRepository.saveAll(entityObjects);
			} catch (Exception ex) {
				System.out.println("Exception Occured in DB Call");
				return false;
			}
		}
		return true;

	}

	@Override
	public List<RestaurantDetailsResponse> fetchRestaurantsByDay(String day) {
		return openingHoursRepository.fetchRestaurantsByDay(day);
	}

	@Override
	public List<RestaurantDetailsResponse> fetchByWorkHours() {
		return openingHoursRepository.fetchByWorkHours();
	}

	@Override
	public List<RestaurantDetailsResponse> fetchByDishPrice(Double fromPrice, Double toPrice, Integer noOfDishes,
			String relation) {
		if (ApplicationConstant.MORE.equalsIgnoreCase(relation)) {
			return menuDetailsRepository.fetchByDishesHigher(Long.valueOf(noOfDishes.toString()), fromPrice, toPrice);
		} else if (ApplicationConstant.LESS.equalsIgnoreCase(relation)) {
			return menuDetailsRepository.fetchByDishesLesser(Long.valueOf(noOfDishes.toString()), fromPrice, toPrice);
		}
		return null;
	}

	@Override
	public List<UserDetailsResponse> fetchTopUsers(String fromDate, String toDate, Integer topUsers) {
		return purchaseHistoryRepository.fetchTopUsers(fromDate, toDate,
				PageRequest.of(0, topUsers, Sort.by(Sort.Direction.DESC, "total")));

	}

	@Override
	public List<TransactionCountResponse> fetchPopularRestaurantByCount() {
		return purchaseHistoryRepository.fetchPopularRestaurantByCount(Sort.by(Sort.Direction.DESC, "totalCount"));
	}

	@Override
	public List<TransactionCountResponse> fetchPopularRestaurantByAmount() {
		return purchaseHistoryRepository
				.fetchPopularRestaurantByAmount(Sort.by(Sort.Direction.DESC, "transactionAmount"));
	}

	@Override
	public List<UserDetailsResponse> fetchTopUsersByAmountHigher(String fromDate, String toDate, Double amount) {
		return purchaseHistoryRepository.fetchTopUsersByAmountHigher(fromDate, toDate, amount);
	}

	@Override
	public List<UserDetailsResponse> fetchTopUsersByAmountLower(String fromDate, String toDate, Double amount) {
		return purchaseHistoryRepository.fetchTopUsersByAmountLower(fromDate, toDate, amount);
	}

	@Override
	public List<MenuDetailsResponse> fetchMenuDetailsbyDishNames(String restaurantName, List<String> dishNames) {
		return menuDetailsRepository.fetchMenuDetailsbyDishNames(restaurantName, dishNames);
	}

	@Override
	public void savePurchaseHistory(List<PurchaseHistory> purchaseHistoryList) {
		purchaseHistoryRepository.saveAll(purchaseHistoryList);
	}

	@Override
	public RestaurantDetails findByRestaurantName(String restaurantName) {
		return restuarantRepository.findByRestaurantName(restaurantName);
	}

	@Override
	public Optional<UserDetails> findbyUserId(Long userId) {
		return userDetailsRepository.findById(userId);
	}

	@Override
	public void saveUserDetails(UserDetails userDetails) {
		userDetailsRepository.save(userDetails);
	}

	@Override
	public void saveRestaurantDetails(RestaurantDetails restaurantDetails) {
		restuarantRepository.save(restaurantDetails);
	}

	@Override
	public void deleteRestaurantData() {
		restuarantRepository.deleteAll();
		
	}

	@Override
	public void deleteUserData() {
		userDetailsRepository.deleteAll();
	}
}
