package com.glints.backend.request;

import java.io.Serializable;

public class MenuDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7_287_955_084_736_774_262L;
	
	private String dishName;
	
	private Integer quantity;

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
