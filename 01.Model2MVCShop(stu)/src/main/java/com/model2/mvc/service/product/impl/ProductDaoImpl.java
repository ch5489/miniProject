package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void seSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	@Override
	public void addProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("ProductMapper.addProduct", productVO);
		
	}

	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	@Override
	public List<ProductVO> getProductList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductMapper.getProductList", searchVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("ProductMapper.updateProduct", productVO);
	}

	@Override
	public int getTotalCount(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount", searchVO);
	}

}
