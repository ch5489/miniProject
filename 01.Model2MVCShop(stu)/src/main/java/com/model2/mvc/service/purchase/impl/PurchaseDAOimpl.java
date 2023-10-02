package com.model2.mvc.service.purchase.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

@Repository("purchaseDaoImpl")
public class PurchaseDAOimpl implements PurchaseDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDAOimpl() {
		System.out.println(this.getClass());
	}

	public void addPurchase(Purchase purchase)throws Exception{
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

//	public PurchaseDAOimpl() {
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
