package com.glints.backend.service;

import java.util.List;

import com.glints.backend.request.PurchaseOrderDetailsRequest;
import com.glints.backend.request.TransactionDetailsRequest;
import com.glints.backend.response.TransactionCountResponse;

public interface GlintsTransactionService {

	public List<TransactionCountResponse> fetchPopularRestaurants(TransactionDetailsRequest transactionDetailsRequest);

	public boolean placeOrder(PurchaseOrderDetailsRequest purchaseOrderDetailsRequest);

}
