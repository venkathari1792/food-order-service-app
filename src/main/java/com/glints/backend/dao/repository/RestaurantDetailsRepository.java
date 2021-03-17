package com.glints.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glints.backend.dao.entity.RestaurantDetails;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long> {
	
	public RestaurantDetails findByRestaurantName(String restaurantName);

}
