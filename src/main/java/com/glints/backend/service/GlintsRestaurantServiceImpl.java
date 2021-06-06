package com.glints.backend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.backend.constants.ApplicationConstant;
import com.glints.backend.dao.service.DaoService;
import com.glints.backend.request.RestaurantsRequest;
import com.glints.backend.response.RestaurantDetailsResponse;

@Service
public class GlintsRestaurantServiceImpl implements GlintsRestaurantService {

	@Autowired
	private DaoService daoService;

	private static Map<Integer, String> daysMap = new HashMap<>();

	static {
		daysMap.put(1, "Mon");
		daysMap.put(2, "Tues");
		daysMap.put(3, "Weds");
		daysMap.put(4, "Thurs");
		daysMap.put(5, "Fri");
		daysMap.put(6, "Sat");
		daysMap.put(7, "Sun");

	}

	@Override
	public List<String> fetchOpenRestaurants(String dateTime) throws ParseException {
		List<String> openRestaurants = new ArrayList<>();
		DateTime inputDate = getDateTimeFromDate(dateTime);
		int day = inputDate.getDayOfWeek();
		int hours = inputDate.withZone(DateTimeZone.UTC).getHourOfDay();
		int minutes = inputDate.withZone(DateTimeZone.UTC).getMinuteOfHour();
		List<RestaurantDetailsResponse> restuarantsList = daoService.fetchRestaurantsByDay(daysMap.get(day));
		if (null != restuarantsList) {
			restuarantsList.stream().forEach(
					restaurantDetails -> addOpenRestaurants(restaurantDetails, hours, minutes, openRestaurants));
		}

		return openRestaurants;
	}

	private static void addOpenRestaurants(RestaurantDetailsResponse restaurantDetails, int hours, int minutes,
			List<String> openRestaurants) {
		if (null != restaurantDetails.getOpenHours() && null != restaurantDetails.getOpenMinutes()
				&& Integer.valueOf(restaurantDetails.getOpenHours()) <= hours
				&& Integer.valueOf(restaurantDetails.getOpenMinutes()) <= minutes
				&& null != restaurantDetails.getCloseHours() && null != restaurantDetails.getCloseMinutes()
				&& Integer.valueOf(restaurantDetails.getCloseHours()) >= hours
				&& Integer.valueOf(restaurantDetails.getCloseMinutes()) >= minutes) {
			openRestaurants.add(restaurantDetails.getRestaurantName());
		}
	}

	private DateTime getDateTimeFromDate(String dateTime) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date newDate = dateFormat.parse(dateTime);
		DateTime inputDate = new DateTime(newDate.getTime());
		return inputDate;
	}

	@Override
	public Set<String> fetchRestaurantsByHours(RestaurantsRequest restaurantsRequest) {
		Set<String> restaurantList = new HashSet<>();
		List<RestaurantDetailsResponse> restaurantDetailsResponse = daoService.fetchByWorkHours();
		if (null != restaurantDetailsResponse) {
			if (ApplicationConstant.DAY.equalsIgnoreCase(restaurantsRequest.getDuration())
					&& ApplicationConstant.MORE.equalsIgnoreCase(restaurantsRequest.getRelation())) {
				restaurantDetailsResponse.stream().forEach(restaurantDetails -> checkWorkHoursGreater(
						Double.valueOf(restaurantsRequest.getHours()), restaurantDetails, restaurantList));
			} else if (ApplicationConstant.DAY.equalsIgnoreCase(restaurantsRequest.getDuration())
					&& ApplicationConstant.LESS.equalsIgnoreCase(restaurantsRequest.getRelation())) {
				restaurantDetailsResponse.stream().forEach(restaurantDetails -> checkWorkHoursLesser(
						Double.valueOf(restaurantsRequest.getHours()), restaurantDetails, restaurantList));
			}
		}
		return restaurantList;
	}

	private boolean checkWorkHoursGreater(double inputHours, RestaurantDetailsResponse restaurantDetails,
			Set<String> restaurantList) {
		if (!restaurantList.contains(restaurantDetails.getRestaurantName())) {
			double workHours = getWorkHours(restaurantDetails.getWorkHours());
			if (workHours > Double.valueOf(inputHours)) {
				restaurantList.add(restaurantDetails.getRestaurantName());
			}
		}
		return true;
	}

	private boolean checkWorkHoursLesser(double inputHours, RestaurantDetailsResponse restaurantDetails,
			Set<String> restaurantList) {
		if (!restaurantList.contains(restaurantDetails.getRestaurantName())) {
			double workHours = getWorkHours(restaurantDetails.getWorkHours());
			if (workHours < Double.valueOf(inputHours)) {
				restaurantList.add(restaurantDetails.getRestaurantName());
			}
		}
		return true;

	}

	private double getWorkHours(String workHours) {
		double workHoursValue = 0.00;
		double minutes = 0.00;
		if (null != workHours) {
			String[] wokrHourArray = workHours.split(ApplicationConstant.COLON);
			int hours = 0;
			if (wokrHourArray.length == 3) {
				hours = Integer.valueOf(wokrHourArray[0]);
				minutes = Integer.valueOf(wokrHourArray[1]) / 60.0;
				if (hours < 0) {
					workHoursValue = hours - minutes + 24;
				}
				workHoursValue = hours + minutes;
			}
		}
		return workHoursValue;
	}

	@Override
	public Set<String> fetchRestaurantsByDishPrice(RestaurantsRequest restaurantRequest) {
		Set<String> restaurantList = new HashSet<>();
		List<RestaurantDetailsResponse> restaurantDetails = daoService.fetchByDishPrice(
				restaurantRequest.getFromPrice(), restaurantRequest.getToPrice(), restaurantRequest.getNoOfDishes(),
				restaurantRequest.getRelation());
		if (null != restaurantDetails) {
			restaurantDetails.forEach(restaurantDet -> addRestaurantNameToList(restaurantDet, restaurantList));
		}
		return restaurantList;
	}

	private void addRestaurantNameToList(RestaurantDetailsResponse restaurantDetails, Set<String> restaurantList) {
		restaurantList.add(restaurantDetails.getRestaurantName());
	}

	@Override
	public RestaurantDetailsResponse searchByRestaurantOrDishName(String searchTerm) {
		return null;
	}

}
