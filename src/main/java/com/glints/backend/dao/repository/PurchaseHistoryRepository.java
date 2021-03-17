package com.glints.backend.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glints.backend.dao.entity.PurchaseHistory;
import com.glints.backend.response.TransactionCountResponse;
import com.glints.backend.response.UserDetailsResponse;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

	@Query(value = "select new com.glints.backend.response.UserDetailsResponse(sum(ph.transactionAmount) as total,ph.userDetails.id,ud.name) from PurchaseHistory ph join UserDetails ud on ud.id = ph.userDetails where DATE_FORMAT(STR_TO_DATE(ph.transactionDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  between "
			+ "DATE_FORMAT(STR_TO_DATE(:fromDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  AND "
			+ "DATE_FORMAT(STR_TO_DATE(:toDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d') " + "group by ph.userDetails ")
	public List<UserDetailsResponse> fetchTopUsers(@Param(value = "fromDate") String fromDate,
			@Param(value = "toDate") String toDate, Pageable page);

	@Query(value = "select new com.glints.backend.response.TransactionCountResponse (count(ph.transactionId) as totalCount, ph.restaurantName, 'TRANSACTION_COUNT' as fetchType) from PurchaseHistory ph group by ph.restaurantName")
	public List<TransactionCountResponse> fetchPopularRestaurantByCount(Sort sort);

	@Query(value = "select new com.glints.backend.response.TransactionCountResponse  (sum(ph.transactionAmount) as transactionAmount, ph.restaurantName, 'TRANSACTION_AMOUNT' as fetchType) from PurchaseHistory ph group by ph.restaurantName")
	public List<TransactionCountResponse> fetchPopularRestaurantByAmount(Sort sort);

	@Query(value = "select new com.glints.backend.response.UserDetailsResponse(sum(ph.transactionAmount) as total,ph.userDetails.id,ud.name) from PurchaseHistory ph join UserDetails ud on ud.id = ph.userDetails where DATE_FORMAT(STR_TO_DATE(ph.transactionDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  between "
			+ "DATE_FORMAT(STR_TO_DATE(:fromDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  AND "
			+ "DATE_FORMAT(STR_TO_DATE(:toDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d') " + "group by ph.userDetails "
			+ "having sum(ph.transactionAmount)  > :amount")
	public List<UserDetailsResponse> fetchTopUsersByAmountHigher(@Param(value = "fromDate") String fromDate,
			@Param(value = "toDate") String toDate, @Param(value = "amount") Double amount);
	
	@Query(value = "select new com.glints.backend.response.UserDetailsResponse(sum(ph.transactionAmount) as total,ph.userDetails.id,ud.name) from PurchaseHistory ph join UserDetails ud on ud.id = ph.userDetails where DATE_FORMAT(STR_TO_DATE(ph.transactionDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  between "
			+ "DATE_FORMAT(STR_TO_DATE(:fromDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d')  AND "
			+ "DATE_FORMAT(STR_TO_DATE(:toDate, '%Y-%m-%d %k:%i:%s'), '%Y-%m-%d') " + "group by ph.userDetails "
			+ "having sum(ph.transactionAmount)  < :amount")
	public List<UserDetailsResponse> fetchTopUsersByAmountLower(@Param(value = "fromDate") String fromDate,
			@Param(value = "toDate") String toDate, @Param(value = "amount") Double amount);

}
