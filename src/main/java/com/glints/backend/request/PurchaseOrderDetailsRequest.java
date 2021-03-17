package com.glints.backend.request;

import java.io.Serializable;
import java.util.List;

public class PurchaseOrderDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3_179_925_270_889_680_463L;
	
	private Long userId;
	
	private String restaurantName;
	
	private List<MenuDetailsRequest> menuDetails;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<MenuDetailsRequest> getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(List<MenuDetailsRequest> menuDetails) {
		this.menuDetails = menuDetails;
	}

}
