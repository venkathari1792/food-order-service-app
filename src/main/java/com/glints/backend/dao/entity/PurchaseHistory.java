package com.glints.backend.dao.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4_881_209_580_591_791_118L;

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@Column(name = "dish_name")
	private String dishName;

	@Column(name = "restaurant_name")
	private String restaurantName;

	@Column(name = "transaction_amount")
	private Double transactionAmount;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetails userDetails;

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			this.transactionDate = formatter.parse(transactionDate);
		} catch (Exception ex) {
			this.transactionDate = null;
		}

	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
