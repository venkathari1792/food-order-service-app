package com.glints.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.entity.RestaurantDetails;
import com.glints.backend.dao.entity.UserDetails;
import com.glints.backend.dao.service.DaoService;
import com.glints.backend.request.DataLoadRequest;
import com.glints.backend.response.DataLoadResponse;
import com.glints.backend.util.CommonUtils;

@Service
public class GlintsLoadDataServiceImpl implements GlintsLoadDataService {

	@Autowired
	private RestApiService restApiService;

	@Autowired
	private DaoService daoService;

	@Override
	public DataLoadResponse loadData(DataLoadRequest dataLoadRequest, String type)
			throws JsonMappingException, JsonProcessingException {
		String rawDataUrl = getRawDataUrl(dataLoadRequest, type);
		if (!CommonUtils.isEmpty(rawDataUrl)) {
			if (ApplicationConstant.RESTAURANT.equalsIgnoreCase(type)) {
				List<RestaurantDetails> restaurantDetailsList = getRestaurantDetailsList(rawDataUrl);
				processRestaurantData(restaurantDetailsList);
			} else if (ApplicationConstant.USER.equalsIgnoreCase(type)) {
				List<UserDetails> userDetailsList = getUserDetailsList(rawDataUrl);
				processUserData(userDetailsList);
			}
		} else {
			// Exception Handling Scenario for Raw Data URL
		}
		return null;

	}

	private List<UserDetails> getUserDetailsList(String rawDataUrl) {
		try {
			return restApiService.getApiCall(rawDataUrl, null, new ParameterizedTypeReference<List<UserDetails>>() {
			});
		} catch (Exception ex) {
			// Exception Handling Scenario for Raw Data URL
			System.out.println("Exception Occured.....");
		}
		return null;
	}

	private List<RestaurantDetails> getRestaurantDetailsList(String url) {
		try {
			return restApiService.getApiCall(url, null, new ParameterizedTypeReference<List<RestaurantDetails>>() {
			});
		} catch (Exception ex) {
			// Exception Handling Scenario for Raw Data URL
			System.out.println("Exception Occured.....");
		}
		return null;
	}

	private <T> void processRestaurantData(List<RestaurantDetails> dataResponse) {
		daoService.insertRestaurantData(dataResponse);

	}

	private <T> void processUserData(List<UserDetails> dataResponse) {
		daoService.insertUserData(dataResponse);

	}

	private String getRawDataUrl(DataLoadRequest dataLoadRequest, String type) {
		String rawDataUrl = null;
		if (null == dataLoadRequest || CommonUtils.isEmpty(dataLoadRequest.getUrl())) {
			if (ApplicationConstant.RESTAURANT.equalsIgnoreCase(type)) {
				rawDataUrl = ApplicationConstant.RESTAURANT_RAW_DATA_URL;
			} else if (ApplicationConstant.USER.equalsIgnoreCase(type)) {
				rawDataUrl = ApplicationConstant.USER_RAW_DATA_URL;
			}
		} else {
			rawDataUrl = dataLoadRequest.getUrl();
		}
		return rawDataUrl;
	}

}
