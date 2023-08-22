package com.model2.mvc.common;


public class SearchVO {
	
	private int page; //curruntPage 로 수정하자! 헷갈린다,,
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private int allPageSize;
	
	private int endRowNum;
	private int startRowNum;
	
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
	public int getEndRowNum() {
		return getPage()*getPageSize();
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getStartRowNum() {
		return (getPage()-1)*getPageSize()+1;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	@Override
	public String toString() {
		return "Search [Page=" + page + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageSize=" + pageSize + "]";
	}	
	
}