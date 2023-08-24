package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO productDao;
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}

	public void addProduct(ProductVO productVO) throws Exception {
		productDao.addProduct(productVO);
	}
	
	
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}

	public Map<String,Object> getProductList(SearchVO searchVO) throws Exception {
		List<ProductVO> list = productDao.getProductList(searchVO);
		int totalCount = productDao.getTotalCount(searchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void updateProduct(ProductVO productVO) throws Exception {
		productDao.updateProduct(productVO);
	}
}
