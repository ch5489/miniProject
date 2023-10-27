package com.model2.mvc.service.purchase.dao;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDAO {

	public void addPurchase(Purchase purchase)throws Exception;
	
	public List<Purchase> getPurchaseList(Search search)throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Purchase getPurchaseProd(int prodNo) throws Exception;
	
	public void updatePurchase(Purchase purchase)throws Exception;
	
	public  List<Purchase> getSaleList(Search search) throws Exception;
	
	public void updateTranCode(Purchase purchase)throws Exception;
//
//	public PurchaseDAO() {
//		
//	}
//	
//	public void findPurchase() {
//		
//	}
//	
//	public void getPurchaseList() {
//		
//	
//	}
//	
//	public void getSaleList() {
//		
//	}
//	
//	
//	public void updatePurchase() {
//		
//	}
//	
//	public void updateTranCode() {
//		
//	}
}
