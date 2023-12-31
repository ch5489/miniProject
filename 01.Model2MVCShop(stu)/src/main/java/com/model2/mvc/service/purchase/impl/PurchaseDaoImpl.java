package com.model2.mvc.service.purchase.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	@Override
	public void addPurchase(Purchase purchase)throws Exception{
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}
	@Override
	public List<Purchase> getPurchaseList(Search search)throws Exception {
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", search);
		
	}
	@Override
	public int getTotalCount(Search search) throws Exception{
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	public Purchase getPurchaseProd(int prodNo) throws Exception {
		//System.out.println(";;;;;;getPurchaseProd.dao;;;;;;;"+prodNo);
		return sqlSession.selectOne("PurchaseMapper.getPurchaseProd", prodNo);
	}
	
	public void updatePurchase(Purchase purchase)throws Exception{
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	public List<Purchase> getSaleList(Search search)throws Exception {
		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
		
	}
	
	public void updateTranCode(Purchase purchase)throws Exception{
		//System.out.println(";;;;;;updateTranCode.dao;;;;;;;"+purchase);
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
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
