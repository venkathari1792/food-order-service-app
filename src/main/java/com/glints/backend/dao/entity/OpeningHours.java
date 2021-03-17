package com.glints.backend.dao.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "opening_hours")
public class OpeningHours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4_940_129_662_422_964_466L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "day_of_week")
	private String dayOfWeek;

	@Column(name = "open_time")
	private String openTime;

	@Column(name = "close_time")
	private String closeTime;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private RestaurantDetails restaurantDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public DateTime getOpenTime() {
		DateTime openDateTime = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date newDate = dateFormat.parse(openTime);
			openDateTime = new DateTime(newDate.getTime());
		} catch (Exception ex) {
			System.out.println("Parse Exception....");
		}
		return openDateTime;
	}

	public void setOpenTime(Long openTime) throws ParseException {
		if (null != openTime) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			this.openTime = dateFormat.format(new Date(openTime));
		}
	}

	public DateTime getCloseTime() {
		DateTime closeDateTime = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date newDate = dateFormat.parse(closeTime);
			closeDateTime = new DateTime(newDate.getTime());
		} catch (Exception ex) {
			System.out.println("Parse Exception....");
		}
		return closeDateTime;
	}

	public void setCloseTime(Long closeTime) throws ParseException {
		if (null != closeTime) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			this.closeTime = dateFormat.format(new Date(closeTime));
			;
		}
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

}
