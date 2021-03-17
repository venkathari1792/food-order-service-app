package com.glints.backend.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.glints.backend.util.CommonUtils;

@Entity
@Table(name = "restaurant_details")
public class RestaurantDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5_883_487_246_009_465_799L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private Long restaurantId;

	@Column(name = "cash_balance")
	private Double cashBalance;

	@Transient
	private String openingHours;

	@Column(name = "restaurant_name")
	private String restaurantName;

	@OneToMany(mappedBy = "restaurantDetails", cascade = CascadeType.ALL)
	private List<Menu> menu;

	@OneToMany(mappedBy = "restaurantDetails", cascade = CascadeType.ALL)
	private List<OpeningHours> openingHoursDetails;

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(Double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
		List<OpeningHours> openingHoursDetails = CommonUtils.createOpeningHoursDetails(openingHours);
		this.setOpeningHoursDetails(openingHoursDetails);

	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
		menu.forEach(menuDet -> menuDet.setRestaurantDetails(this));
	}

	public List<OpeningHours> getOpeningHoursDetails() {
		return openingHoursDetails;
	}

	public void setOpeningHoursDetails(List<OpeningHours> openingHoursDetails) {
		this.openingHoursDetails = openingHoursDetails;
		openingHoursDetails.forEach(openingHoursDetail -> openingHoursDetail.setRestaurantDetails(this));
	}

}
