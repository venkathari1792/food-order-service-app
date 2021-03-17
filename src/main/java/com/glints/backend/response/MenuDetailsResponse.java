package com.glints.backend.response;

import java.io.Serializable;

public class MenuDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3_989_726_353_022_871_298L;
	
	private Double price;
	
	private String dishName;
	
	private Long restaurantId;
	
	private String restaurantName;
	

	public MenuDetailsResponse(Double price, String dishName, Long restaurantId, String restaurantName) {
		this.price = price;
		this.dishName = dishName;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

}
