package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO productDao;
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDAO purchaseDao;
	
	public void setPurchaseDao(PurchaseDAO purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}

	public void addProduct(Product product) throws Exception {
		
		productDao.addProduct(product);
	}
	
	
	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}

	public Map<String,Object> getProductList(Search search) throws Exception {
		List<Product> list = productDao.getProductList(search);
		List<Purchase> listP = purchaseDao.getSaleList(search);
		int totalCount = productDao.getTotalCount(search);
		for(int i= 0; i<list.size();i++) {
			if(listP.get(i).getTranCode() == null) {
				list.get(i).setProTranCode("ÆÇ¸ÅÁß");
			}else {
				list.get(i).setProTranCode(listP.get(i).getTranCode());
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
}
