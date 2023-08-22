package com.model2.mvc.service.user.dao;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.User;


public interface UserDAO {
	
	
	public void addUser(User user) throws Exception ;

	public User getUser(String userId) throws Exception ;

	public List<User> getUserList(SearchVO searchVO) throws Exception ;

	public void updateUser(User user) throws Exception ;
	
	public int getTotalCount(SearchVO searchVO) throws Exception ;
	
	
	
}

//{
//	
//	Connection con = DBUtil.getConnection();
//
//	String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
//	
//	PreparedStatement stmt = con.prepareStatement(sql);
//	stmt.setString(1, user.getUserId());
//	stmt.setString(2, user.getUserName());
//	stmt.setString(3, user.getPassword());
//	stmt.setString(4, user.getSsn());
//	stmt.setString(5, user.getPhone());
//	stmt.setString(6, user.getAddr());
//	stmt.setString(7, user.getEmail());
//	stmt.executeUpdate();
//	
//	con.close();
//}

//{
//	
//	Connection con = DBUtil.getConnection();
//
//	String sql = "select * from USERS where USER_ID=?";
//	
//	PreparedStatement stmt = con.prepareStatement(sql);
//	stmt.setString(1, userId);
//
//	ResultSet rs = stmt.executeQuery();
//
//	User user = null;
//	while (rs.next()) {
//		user = new User();
//		user.setUserId(rs.getString("USER_ID"));
//		user.setUserName(rs.getString("USER_NAME"));
//		user.setPassword(rs.getString("PASSWORD"));
//		user.setRole(rs.getString("ROLE"));
//		user.setSsn(rs.getString("SSN"));
//		user.setPhone(rs.getString("CELL_PHONE"));
//		user.setAddr(rs.getString("ADDR"));
//		user.setEmail(rs.getString("EMAIL"));
//		user.setRegDate(rs.getDate("REG_DATE"));
//	}
//	
//	con.close();
//
//	return user;
//}
//
//{
//	
//	Map<String,Object> map = new HashMap<String,Object>();
//	
//	Connection con = DBUtil.getConnection();
//	
//	
//	String sql = "SELECT USER_ID, USER_NAME, EMAIL FROM USERS ";
//	if (searchVO.getSearchCondition() != null) {
//		if (searchVO.getSearchCondition().equals("0") && !searchVO.getSearchCondition().equals("")) {
//			sql += " where USER_ID LIKE '%" + searchVO.getSearchKeyword()
//					+ "%'";
//		} else if (searchVO.getSearchCondition().equals("1")&& !searchVO.getSearchCondition().equals("")) {
//			sql += " where USER_NAME LIKE '%" + searchVO.getSearchKeyword()
//					+ "%'";
//		}
//	}
//	sql += " order by USER_ID";
//	
//	System.out.println("UserDAO::Original SQL :: " + sql);
//	
//
//	int totalCount = this.getTotalCount(sql);
//	System.out.println("UserDAO :: totalCount  :: " + totalCount);
//	
//	sql = makeCurrentPageSql(sql, searchVO);
//	PreparedStatement pStmt = con.prepareStatement(sql);
//	ResultSet rs = pStmt.executeQuery();
//	
//	System.out.println(searchVO);
//	List<User> list = new ArrayList<User>();
//	
//	while(rs.next()) {
//		
//		User user = new User();
//		user.setUserId(rs.getString("USER_ID"));
//		user.setUserName(rs.getString("USER_NAME"));
//		user.setEmail(rs.getString("EMAIL"));
//		list.add(user);
//	}
//	
//	// ==> totalCount 정보 저장
//	map.put("totalCount", new Integer(totalCount));
//	// ==> currentPage 의 게시물 정보 갖는 List 저장
//	map.put("list", list);
//	//System.out.println(list);
//	//System.out.println("map.put(\"list\")"+map.get("list"));
//	
//	rs.close();
//	pStmt.close();
//	con.close();
//		
//	return map;
//}
//
//{
//	
//	Connection con = DBUtil.getConnection();
//
//	String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
//	
//	PreparedStatement stmt = con.prepareStatement(sql);
//	stmt.setString(1, user.getUserName());
//	stmt.setString(2, user.getPhone());
//	stmt.setString(3, user.getAddr());
//	stmt.setString(4, user.getEmail());
//	stmt.setString(5, user.getUserId());
//	stmt.executeUpdate();
//	
//	con.close();
//}


// private String makeCurrentPageSql(String sql, SearchVO searchVO) ;
//{
//	
//	sql = "SELECT COUNT(*) "+
//			"FROM ( " +sql+ " ) counTable";
//	
//	Connection con = DBUtil.getConnection();
//	PreparedStatement pStmt = con.prepareStatement(sql);
//	ResultSet rs = pStmt.executeQuery();
//	
//	int totalCount = 0;
//	if( rs.next()) {
//		totalCount = rs.getInt(1);
//	}
//	
//	pStmt.close();
//	con.close();
//	rs.close();
//	
//	return totalCount;
//}
//
//{
//	sql = "SELECT * "+
//			"FROM (	SELECT inner_table.* , ROWNUM AS row_seq"+
//							" FROM ( " +sql+ " ) inner_table "+
//						 	" WHERE ROWNUM <= "+searchVO.getPage()*searchVO.getPageSize()+" )"+
//			"WHERE row_seq BETWEEN "+((searchVO.getPage()-1)*searchVO.getPageSize()+1) +" AND "+searchVO.getPage()*searchVO.getPageSize();
//	
//	System.out.println("UserDAO :: make SQL :: "+ sql);	
//	
//	return sql;
//					
//}