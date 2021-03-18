package com.glints.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.constants.ApplicationEndpoint;
import com.glints.backend.error.ErrorCode;
import com.glints.backend.request.DataLoadRequest;
import com.glints.backend.request.PurchaseOrderDetailsRequest;
import com.glints.backend.request.RestaurantsRequest;
import com.glints.backend.request.TransactionDetailsRequest;
import com.glints.backend.request.UserDetailsRequest;
import com.glints.backend.response.BaseResponseTO;
import com.glints.backend.response.ResponseDataTO;
import com.glints.backend.response.StatusTO;
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
	public BaseResponseTO<Boolean> loadData(@RequestBody(required = false) DataLoadRequest dataLoadRequest,
			@PathVariable String type) {
		Boolean response = null;
		try {
			response = glintsLoadDataService.loadData(dataLoadRequest, type);
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(false, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		if (response) {
			return new ResponseDataTO<>(response,
					new StatusTO(200, "Data Loaded Successfully", ApplicationConstant.SUCCESS));
		} else {
			return new ResponseDataTO<>(response,
					new StatusTO(200, "Invalid Input URL Specified", ApplicationConstant.FAILURE));
		}
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_OPEN_RESTAURANTS)
	public BaseResponseTO<List<String>> fetchOpenRestaurants(@RequestBody RestaurantsRequest restaurantRequest) {
		List<String> response = null;
		try {
			if (null != restaurantRequest) {
				response = glintsRestaurantService.fetchOpenRestaurants(restaurantRequest.getInputDate());
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_RESTAURANTS_WORK_HOURS)
	public BaseResponseTO<Set<String>> fetchRestaurantsByWorkHours(@RequestBody RestaurantsRequest restaurantRequest) {
		Set<String> response = null;
		try {
			if (null != restaurantRequest) {
				response = glintsRestaurantService.fetchRestaurantsByHours(restaurantRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_RESTAURANTS_BY_DISH_PRICE)
	public BaseResponseTO<Set<String>> fetchRestaurantsByDishPrice(@RequestBody RestaurantsRequest restaurantRequest) {
		Set<String> response = null;
		try {
			if (null != restaurantRequest) {
				response = glintsRestaurantService.fetchRestaurantsByDishPrice(restaurantRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_TOP_USERS)
	public BaseResponseTO<List<UserDetailsResponse>> fetchTopUsers(@RequestBody UserDetailsRequest userDetailsRequest) {
		List<UserDetailsResponse> response = null;
		try {
			if (null != userDetailsRequest) {
				response = glintsUserService.fetchTopUsers(userDetailsRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_BY_TRANSACTIONS)
	public BaseResponseTO<List<TransactionCountResponse>> fetchPopularRestaurants(
			@RequestBody TransactionDetailsRequest transactionDetailsRequest) {
		List<TransactionCountResponse> response = null;
		try {
			if (null != transactionDetailsRequest) {
				response = glintsTransactionService.fetchPopularRestaurants(transactionDetailsRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.FETCH_TOTAL_USERS_BY_AMOUNT)
	public BaseResponseTO<UserDetailsResponse> fetchTopUsersByAmount(
			@RequestBody UserDetailsRequest userDetailsRequest) {
		UserDetailsResponse response = null;
		try {
			if (null != userDetailsRequest) {
				response = glintsUserService.fetchTopUsersByAmount(userDetailsRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(null, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response, new StatusTO());
	}

	@PostMapping(value = ApplicationEndpoint.PLACE_ORDER)
	public BaseResponseTO<Boolean> placeOrder(@RequestBody PurchaseOrderDetailsRequest purchaseOrderDetailsRequest) {
		Boolean response = null;
		try {
			if (null != purchaseOrderDetailsRequest) {
				response = glintsTransactionService.placeOrder(purchaseOrderDetailsRequest);
			} else {
				return new ResponseDataTO<>(null,
						new StatusTO(ErrorCode.INVALID_INPUT_FORMAT.getCode(), ApplicationConstant.FAILURE));
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured...");
			return new ResponseDataTO<>(false, new StatusTO(ErrorCode.DEFAULT_SYSTEM_ERROR.getCode(),
					ex.getLocalizedMessage(), ApplicationConstant.FAILURE));
		}
		return new ResponseDataTO<>(response,
				new StatusTO(200, "Placed Order Successfully", ApplicationConstant.SUCCESS));
	}
}
