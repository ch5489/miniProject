package com.model2.mvc.common;


public class Search {
	
	private int currentPage; //curruntPage 로 수정하자! 헷갈린다,,
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private int allPageSize;
	
	private int endRowNum;
	private int startRowNum;
	
	//private User buyer;
	private String buyerId;
	
	public Search(){
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
		//System.out.println("--------getSearchCondition 확인 : "+searchKeyword+" --------");
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		//System.out.println("--------setSearchCondition 확인 : "+searchKeyword+" --------");
		this.searchKeyword = searchKeyword;
	}
	public int getEndRowNum() {
		System.out.println(";;;;;;;;;;;또 뭐냐고,,"+getCurrentPage()*getPageSize());
		return getCurrentPage()*getPageSize();
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getStartRowNum() {
		return (getCurrentPage()-1)*getPageSize()+1;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	@Override
	public String toString() {
		return "Search [currentPage=" + currentPage + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageSize=" + pageSize + ", endRowNum=" + endRowNum
				+ ", allPageSize=" + allPageSize+ ", startRowNum=" + startRowNum+ "]";
	}	
	
}