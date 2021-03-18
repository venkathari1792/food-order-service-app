package com.glints.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.entity.RestaurantDetails;
import com.glints.backend.dao.entity.UserDetails;
import com.glints.backend.dao.service.DaoService;
import com.glints.backend.error.ErrorCode;
import com.glints.backend.request.DataLoadRequest;
import com.glints.backend.util.CommonUtils;

@Service
public class GlintsLoadDataServiceImpl implements GlintsLoadDataService {

	@Autowired
	private RestApiService restApiService;

	@Autowired
	private DaoService daoService;

	@Override
	public Boolean loadData(DataLoadRequest dataLoadRequest, String type) throws Exception {
		String rawDataUrl = getRawDataUrl(dataLoadRequest, type);
		if (!CommonUtils.isEmpty(rawDataUrl)) {
			if (ApplicationConstant.RESTAURANT.equalsIgnoreCase(type)) {
				daoService.deleteRestaurantData();
				List<RestaurantDetails> restaurantDetailsList = getRestaurantDetailsList(rawDataUrl);
				processRestaurantData(restaurantDetailsList);
			} else if (ApplicationConstant.USER.equalsIgnoreCase(type)) {
				daoService.deleteUserData();
				List<UserDetails> userDetailsList = getUserDetailsList(rawDataUrl);
				processUserData(userDetailsList);
			}
			return true;
		} else {
			return false;
		}

	}

	private List<UserDetails> getUserDetailsList(String rawDataUrl) throws Exception {
		try {
			return restApiService.getApiCall(rawDataUrl, null, new ParameterizedTypeReference<List<UserDetails>>() {
			});
		} catch (Exception ex) {
			System.out.println("Exception Occured.....");
			throw new Exception(ErrorCode.FETCH_DATA_ERROR.getDescription(), ex);
		}
	}

	private List<RestaurantDetails> getRestaurantDetailsList(String url) throws Exception {
		try {
			return restApiService.getApiCall(url, null, new ParameterizedTypeReference<List<RestaurantDetails>>() {
			});
		} catch (Exception ex) {
			System.out.println("Exception Occured.....");
			throw new Exception(ErrorCode.FETCH_DATA_ERROR.getDescription(), ex);
		}
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
