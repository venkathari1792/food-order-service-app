package com.glints.backend.response;

import java.io.Serializable;

public class RestaurantDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1_287_487_087_129_248_178L;

	private String openHours;

	private String openMinutes;

	private String closeHours;

	private String workHours;

	private Long restaurantId;

	private String closeMinutes;

	private String day;

	private String restaurantName;

	private Long noOfDishes;

	public RestaurantDetailsResponse(Long noOfDishes, String restaurantName) {
		this.noOfDishes = noOfDishes;
		this.restaurantName = restaurantName;
	}

	public RestaurantDetailsResponse(String workHours, String day, Long restaurantId, String restaurantName) {
		this.workHours = workHours;
		this.restaurantId = restaurantId;
		this.day = day;
		this.restaurantName = restaurantName;
	}

	public RestaurantDetailsResponse(String openHours, String openMinutes, String closeHours, String closeMinutes,
			String day, String restaurantName) {
		this.openHours = openHours;
		this.openMinutes = openMinutes;
		this.closeHours = closeHours;
		this.closeMinutes = closeMinutes;
		this.day = day;
		this.restaurantName = restaurantName;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getOpenMinutes() {
		return openMinutes;
	}

	public void setOpenMinutes(String openMinutes) {
		this.openMinutes = openMinutes;
	}

	public String getCloseHours() {
		return closeHours;
	}

	public void setCloseHours(String closeHours) {
		this.closeHours = closeHours;
	}

	public String getCloseMinutes() {
		return closeMinutes;
	}

	public void setCloseMinutes(String closeMinutes) {
		this.closeMinutes = closeMinutes;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getNoOfDishes() {
		return noOfDishes;
	}

	public void setNoOfDishes(Long noOfDishes) {
		this.noOfDishes = noOfDishes;
	}

}
