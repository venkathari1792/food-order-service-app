package com.glints.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glints.backend.dao.entity.OpeningHours;
import com.glints.backend.response.RestaurantDetailsResponse;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long>{

	
	@Query(value = "select new com.glints.backend.response.RestaurantDetailsResponse(DATE_FORMAT(STR_TO_DATE(oh.openTime, '%Y-%m-%d %k:%i:%s'), '%k'), DATE_FORMAT(STR_TO_DATE(oh.openTime, '%Y-%m-%d %k:%i:%s'), '%i'), DATE_FORMAT(STR_TO_DATE(oh.closeTime, '%Y-%m-%d %k:%i:%s'), '%k'), DATE_FORMAT(STR_TO_DATE(oh.closeTime, '%Y-%m-%d %k:%i:%s'), '%i'), oh.dayOfWeek, rd.restaurantName) from OpeningHours oh join RestaurantDetails rd on oh.restaurantDetails = rd.restaurantId where oh.dayOfWeek = :day")
	public List<RestaurantDetailsResponse> fetchRestaurantsByDay(@Param(value = "day") String day);
	
	@Query(value = "select new com.glints.backend.response.RestaurantDetailsResponse(DATE_FORMAT(TIMEDIFF(DATE_FORMAT(STR_TO_DATE(oh.closeTime, '%Y-%m-%d %k:%i:%s'), '%k:%i:%s'), DATE_FORMAT(STR_TO_DATE(oh.openTime, '%Y-%m-%d %k:%i:%s'), '%k:%i:%s')), '%k:%i:%s'), oh.dayOfWeek,rd.restaurantId,rd.restaurantName) from OpeningHours oh join RestaurantDetails rd on oh.restaurantDetails = rd.restaurantId ")
	public List<RestaurantDetailsResponse> fetchByWorkHours();
}
