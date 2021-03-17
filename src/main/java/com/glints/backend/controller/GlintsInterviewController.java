package com.glints.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glints.backend.constants.ApplicationEndpoint;
import com.glints.backend.request.DataLoadRequest;
import com.glints.backend.request.PurchaseOrderDetailsRequest;
import com.glints.backend.request.RestaurantsRequest;
import com.glints.backend.request.TransactionDetailsRequest;
import com.glints.backend.request.UserDetailsRequest;
import com.glints.backend.response.TransactionCountResponse;
import com.glints.backend.response.UserDetailsResponse;
import com.glints.backend.service.GlintsLoadDataService;
import com.glints.backend.service.GlintsRestaurantService;
import com.glints.backend.service.GlintsTransactionService;
import com.glints.backend.service.GlintsUserService;

@RestController
public class GlintsInterviewController {

	@Autowired
	private GlintsRestaurantService glintsRestaurantService;

	@Autowired
	private GlintsLoadDataService glintsLoadDataService;

	@Autowired
	private GlintsUserService glintsUserService;

	@Autowired
	private GlintsTransactionService glintsTransactionService;

	@GetMapping(value = ApplicationEndpoint.LOAD_DATA)
	public void loadRestaurantData(@RequestBody(required = false) DataLoadRequest dataLoadRequest,
			@PathVariable String type) {
		try {
			glintsLoadDataService.loadData(dataLoadRequest, type);
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}

	}

	@PostMapping(value = ApplicationEndpoint.FETCH_OPEN_RESTAURANTS)
	public List<String> fetchOpenRestaurants(@RequestBody RestaurantsRequest restaurantRequest) {
		try {
			if (null != restaurantRequest) {
				return glintsRestaurantService.fetchOpenRestaurants(restaurantRequest.getInputDate());
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_RESTAURANTS_WORK_HOURS)
	public Set<String> fetchRestaurantsByWorkHours(@RequestBody RestaurantsRequest restaurantRequest) {
		try {
			if (null != restaurantRequest) {
				return glintsRestaurantService.fetchRestaurantsByHours(restaurantRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_RESTAURANTS_BY_DISH_PRICE)
	public Set<String> fetchRestaurantsByDishPrice(@RequestBody RestaurantsRequest restaurantRequest) {
		try {
			if (null != restaurantRequest) {
				return glintsRestaurantService.fetchRestaurantsByDishPrice(restaurantRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_TOP_USERS)
	public List<UserDetailsResponse> fetchTopUsers(@RequestBody UserDetailsRequest userDetailsRequest) {
		try {
			if (null != userDetailsRequest) {
				return glintsUserService.fetchTopUsers(userDetailsRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_BY_TRANSACTIONS)
	public List<TransactionCountResponse> fetchPopularRestaurants (
			@RequestBody TransactionDetailsRequest transactionDetailsRequest) {
		try {
			if (null != transactionDetailsRequest) {
				return glintsTransactionService.fetchPopularRestaurants(transactionDetailsRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_TOTAL_USERS_BY_AMOUNT)
	public UserDetailsResponse fetchTopUsersByAmount(@RequestBody UserDetailsRequest userDetailsRequest) {
		try {
			if (null != userDetailsRequest) {
				return glintsUserService.fetchTopUsersByAmount(userDetailsRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return null;
	}
	
	@PostMapping(value = ApplicationEndpoint.PLACE_ORDER)
	public boolean placeOrder(@RequestBody PurchaseOrderDetailsRequest purchaseOrderDetailsRequest) {
		try {
			if (null != purchaseOrderDetailsRequest) {
				return glintsTransactionService.placeOrder(purchaseOrderDetailsRequest);
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
		}
		return false;
	}
}
