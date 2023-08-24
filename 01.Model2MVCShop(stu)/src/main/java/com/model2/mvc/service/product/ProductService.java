package com.model2.mvc.service.product;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;



public interface ProductService {
	
	public void addProduct(ProductVO productVO) throws Exception;

	public Map<String, Object> getProductList(SearchVO searchVO) throws Exception;

	public ProductVO getProduct(int prodNo) throws Exception;

	public void updateProduct(ProductVO productVO) throws Exception;
	/*
	 * public UserVO loginUser(UserVO userVO) throws Exception;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public boolean checkDuplication(String userId) throws Exception;
	 */
}