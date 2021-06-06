package com.glints.backend.service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import com.glints.backend.request.RestaurantsRequest;
import com.glints.backend.response.RestaurantDetailsResponse;

public interface GlintsRestaurantService {

	public List<String> fetchOpenRestaurants(String dateTime) throws ParseException;
	
	public Set<String> fetchRestaurantsByHours(RestaurantsRequest restaurantsRequest);

	public Set<String> fetchRestaurantsByDishPrice(RestaurantsRequest restaurantRequest);

	public RestaurantDetailsResponse searchByRestaurantOrDishName(String searchTerm);

}
