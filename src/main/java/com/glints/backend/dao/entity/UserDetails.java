package com.glints.backend.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5_872_992_657_661_525_118L;

	@Id
	@Column(name = "user_id")
	@JsonProperty(value = "id")
	private Long id;

	@Column(name = "cash_balance")
	private Double cashBalance;

	@Column(name = "user_name")
	private String name;

	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<PurchaseHistory> purchaseHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(Double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PurchaseHistory> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
		purchaseHistory.forEach(purchaseDet -> purchaseDet.setUserDetails(this));
	}

}
