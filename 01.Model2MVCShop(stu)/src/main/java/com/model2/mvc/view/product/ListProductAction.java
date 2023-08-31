package com.model2.mvc.view.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			
			int page = 1;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			System.out.println("받아오는 페이지 수"+page);
			
			int total = 0;
			int totalPage = 0;
			
			
			Search search = new Search();
			search.setPage(page);
			search.setSearchCondition(request.getParameter("searchCondition"));
			search.setSearchKeyword(request.getParameter("searchKeyword"));

			String pageSize = getServletContext().getInitParameter("pageSize");
			search.setPageSize(Integer.parseInt(pageSize));
			
			String pageUnit = getServletContext().getInitParameter("pageUnit");
			request.setAttribute("pageUnit", pageUnit);

			ProductService service = new ProductServiceImpl();
			Map<String, Object> map = service.getProductList(search);
			
			
			if (map != null) {
				total = ((Integer) map.get("count")).intValue();

			}
			
			if (total > 0) {
				totalPage = total / Integer.parseInt(pageSize);
				if (total % Integer.parseInt(pageSize) > 0)
					totalPage += 1;
			}
			//System.out.println(totalPage);
			
			search.setAllPageSize(totalPage);
			//searchVO.setAllPageSize(page);
			
			request.setAttribute("map", map);
			request.setAttribute("searchVO", search);
			System.out.println("request.getParameter(\"searchCondition\")"+request.getParameter("searchCondition"));
			System.out.println("request.getParameter(\"searchKeyword\")"+request.getParameter("searchKeyword"));
			//System.out.println("request 파싱 :"+(request.getQueryString()).split("&")[1]);


		return "forward:/product/listProduct.jsp";
	}

	
}

//굳이 여기서 나누지 말자!!

//System.out.println(request.getQueryString());

//if (request.getQueryString().equals("menu=search")) {

//String url = request.getQueryString();
//String contextPath = request.getContextPath();
////String path = url.substring(contextPath.length());
//
//System.out.println(url);
//System.out.println(contextPath);
////System.out.println(path);

	 

//	} 
//else if (request.getQueryString().equals("menu=manage")){
//
//	
//		int page = 1;
//		if (request.getParameter("page") != null)
//			page = Integer.parseInt(request.getParameter("page"));
//
//		Search searchVO = new Search();
//		searchVO.setPage(page);
//		searchVO.setSearchCondition(request.getParameter("searchCondition"));
//		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
//
//		String pageUnit = getServletContext().getInitParameter("pageSize");
//		searchVO.setPageUnit(Integer.parseInt(pageUnit));
//
//		ProductService service = new ProductServiceImpl();
//		HashMap<String, Object> map = service.getProductList(searchVO);
//
//		request.setAttribute("map", map);
//		request.setAttribute("searchVO", searchVO);
//
//		
//	
//		url = "forward:/product/updateProductView.jsp";
//
//}

//System.out.println(url);
//return "forward:/product/listProduct.jsp";


