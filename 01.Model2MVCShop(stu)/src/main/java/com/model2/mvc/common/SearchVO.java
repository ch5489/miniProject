package com.model2.mvc.common;


public class SearchVO {
	
	private int page;
	String searchCondition;
	String searchKeyword;
	int pageSize;
	int allPageSize;
	
	public SearchVO(){
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getAllPageSize() {
		return allPageSize;
	}
	public void setAllPageSize(int allPageSize) {
		this.allPageSize = allPageSize;
	}
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}