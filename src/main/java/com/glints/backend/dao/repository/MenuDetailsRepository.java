package com.glints.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glints.backend.dao.entity.Menu;
import com.glints.backend.response.MenuDetailsResponse;
import com.glints.backend.response.RestaurantDetailsResponse;

@Repository
public interface MenuDetailsRepository extends JpaRepository<Menu, Long> {

	@Query(value = "select new com.glints.backend.response.RestaurantDetailsResponse(count(md.menuId), rd.restaurantName) from Menu md "
			+ "join RestaurantDetails rd on md.restaurantDetails = rd.restaurantId where md.price BETWEEN :fromPrice AND :toPrice GROUP by md.restaurantDetails having count(md.menuId) > :noOfDishes")
	public List<RestaurantDetailsResponse> fetchByDishesHigher(@Param(value = "noOfDishes") Long noOfDishes,
			@Param(value = "fromPrice") Double fromPrice, @Param(value = "toPrice") Double toPrice);

	@Query(value = "select new com.glints.backend.response.RestaurantDetailsResponse(count(md.menuId), rd.restaurantName) from Menu md "
			+ "join RestaurantDetails rd on md.restaurantDetails = rd.restaurantId where md.price BETWEEN :fromPrice AND :toPrice GROUP by md.restaurantDetails having count(md.menuId) < :noOfDishes")
	public List<RestaurantDetailsResponse> fetchByDishesLesser(@Param(value = "noOfDishes") Long noOfDishes,
			@Param(value = "fromPrice") Double fromPrice, @Param(value = "toPrice") Double toPrice);

	@Query(value = "select new com.glints.backend.response.MenuDetailsResponse(md.price, md.dishName,md.restaurantDetails.restaurantId,rd.restaurantName) from Menu md join RestaurantDetails rd on rd.restaurantId = md.restaurantDetails.restaurantId and rd.restaurantName= :restaurantName and md.dishName IN :dishNames  ")
	public List<MenuDetailsResponse> fetchMenuDetailsbyDishNames(@Param(value = "restaurantName") String restaurantName,
			@Param("dishNames") List<String> dishNames);

}
