package com.glints.backend.dao.service;

import java.util.List;
import java.util.Optional;

import com.glints.backend.dao.entity.PurchaseHistory;
import com.glints.backend.dao.entity.RestaurantDetails;
import com.glints.backend.dao.entity.UserDetails;
import com.glints.backend.response.MenuDetailsResponse;
import com.glints.backend.response.RestaurantDetailsResponse;
import com.glints.backend.response.TransactionCountResponse;
import com.glints.backend.response.UserDetailsResponse;

public interface DaoService {

	public boolean insertRestaurantData(List<RestaurantDetails> entityObjects);

	public boolean insertUserData(List<UserDetails> entityObjects);

	public List<RestaurantDetailsResponse> fetchRestaurantsByDay(String day);

	public List<RestaurantDetailsResponse> fetchByWorkHours();

	public List<RestaurantDetailsResponse> fetchByDishPrice(Double fromPrice, Double toPrice, Integer noOfDishes,
			String relation);

	public List<UserDetailsResponse> fetchTopUsers(String fromDate, String toDate, Integer topUsers);

	public List<TransactionCountResponse> fetchPopularRestaurantByCount();

	public List<TransactionCountResponse> fetchPopularRestaurantByAmount();

	public List<UserDetailsResponse> fetchTopUsersByAmountHigher(String fromDate, String toDate, Double amount);

	public List<UserDetailsResponse> fetchTopUsersByAmountLower(String fromDate, String toDate, Double amount);

	public List<MenuDetailsResponse> fetchMenuDetailsbyDishNames(String restaurantName, List<String> dishNames);

	public void savePurchaseHistory(List<PurchaseHistory> purchaseHistoryList);

	public RestaurantDetails findByRestaurantName(String restaurantName);

	public Optional<UserDetails> findbyUserId(Long userId);

	public void saveUserDetails(UserDetails userDetails);

	public void saveRestaurantDetails(RestaurantDetails restaurantDetails);
}
