package com.model2.mvc.service.product.dao;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {
	
	public ProductDAO() {
		
	}
	//insertProduct
	//findProduct
	//getProductList
	//updateProduct
	
	
public void addProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into PRODUCT values (seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)";
				//+ "(seq_product_prod_no,?,?,?,?,?,sysdate)";
				//PROD_NAME=?,PROD_DETAIL=?,MANUFACT=?,PRICE=?,IMAGE_FILE=?,REG_DATE=?
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		//regDate sysdate 설
		stmt.executeUpdate();
		
		con.close();
	}

	
	
	public ProductVO findProduct(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select * from PRODUCT where PROD_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			
		}

		con.close();

		return productVO;
	}
	  
	  public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception
	  {
	  
	  Connection con = DBUtil.getConnection();
	  
	  //int page =1;
	  //if(searchVO.getPage() != 0) {
		 // page = searchVO.getPage();
	 // }
	  
		String sql = "SELECT * FROM PRODUCT ";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE PROD_NO LIKE'%" + searchVO.getSearchKeyword() + "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE PROD_NAME LIKE'%" + searchVO.getSearchKeyword() + "%'";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += " WHERE PRICE=" + searchVO.getSearchKeyword();
			}
		}
		sql += "ORDER BY PROD_NO";
				
		
		System.out.println("수정전 sql : "+sql);

		PreparedStatement stmt1 = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rs1 = stmt1.executeQuery();

		rs1.last();
		int total = rs1.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));
		System.out.println(map);
		
		
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageSize());
		
		sql = makeCurrentPageSql(sql, searchVO);
		PreparedStatement stmt2 =con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rs2 = stmt2.executeQuery();
		System.out.println("변경됐나 sql : "+sql);
		
		rs2.absolute(1);
		//1 또는 가지고 오는 값 부터
		//System.out.println("rs2.absolute(1) ;"+rs2.absolute(1));
		//System.out.println("rs2.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1)"+rs2.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1));
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageSize(); i++) {
				ProductVO vo = new ProductVO();
				//System.out.println("1");
				//System.out.println(rs2.getInt("PROD_NO"));
				vo.setProdNo(rs2.getInt("PROD_NO"));
				//System.out.println("2");
				vo.setProdName(rs2.getString("PROD_NAME"));
				//System.out.println("3");
				vo.setProdDetail(rs2.getString("PROD_DETAIL"));
				//System.out.println("4");
				vo.setManuDate(rs2.getString("manufacture_day"));
				//System.out.println("5");
				vo.setPrice(rs2.getInt("PRICE"));
				//System.out.println("6");
				vo.setFileName(rs2.getString("IMAGE_FILE"));
			//	System.out.println("7");
				vo.setRegDate(rs2.getDate("REG_DATE"));
				//System.out.println("8");
				
				list.add(vo);
				System.out.println(list);
				if (!rs2.next())
					break;
			}
		}
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());

		con.close();
//		rs1.close();
//		rs2.close();

		return map;
	}
	
	  public void updateProduct(ProductVO productVO) throws Exception {
	  
	  Connection con = DBUtil.getConnection();
	  
	  String sql =
	  "update product set PROD_NAME = ?, PROD_DETAIL= ?, manufacture_day= ?, PRICE= ?, IMAGE_FILE = ? where prod_no = ?";
	  
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();

		con.close();
	}
	  
	String makeCurrentPageSql(String sql, SearchVO searchVO) {
		
//		int page =1;
//		  if(searchVO.getPage() != 0) {
//			  page = searchVO.getPage();
//		  }
		sql = "SELECT pt1.* FROM (SELECT ROWNUM rnum, pt.* FROM ("+sql+
				") pt) pt1 WHERE pt1.rnum BETWEEN "+((searchVO.getPage()*searchVO.getPageSize())-(searchVO.getPageSize()-1))+" and "+(searchVO.getPage()*searchVO.getPageSize());
		//System.out.println((page*searchVO.getPageUnit())-(searchVO.getPageUnit()-1));
		System.out.println("변경해야할 sql : "+sql);
		return sql;

	}
	 
}
//update product set PROD_NAME = 'chagetest', PROD_DETAIL= 'chagetest', manufacture_day= '00000000', PRICE= 000, IMAGE_FILE = 'chagetest' where prod_no = 10033;
//select* from product ;
