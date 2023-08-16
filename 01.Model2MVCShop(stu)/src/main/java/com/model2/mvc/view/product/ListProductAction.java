package com.model2.mvc.view.product;

import java.util.HashMap; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = null;
		//System.out.println(request.getQueryString());
		
		//if (request.getQueryString().equals("menu=search")) {
			
			int page = 1;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			System.out.println("받아오는 페이지 수"+page);
			
			
			SearchVO searchVO = new SearchVO();
			searchVO.setPage(page);
			searchVO.setSearchCondition(request.getParameter("searchCondition"));
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

			String pageSize = getServletContext().getInitParameter("pageSize");
			searchVO.setPageSize(Integer.parseInt(pageSize));
			
			String pageUnit = getServletContext().getInitParameter("pageUnit");
			request.setAttribute("pageUnit", pageUnit);

			ProductService service = new ProductServiceImpl();
			HashMap<String, Object> map = service.getProductList(searchVO);

			request.setAttribute("map", map);
			request.setAttribute("searchVO", searchVO);
			System.out.println("request.getParameter(\"searchCondition\")"+request.getParameter("searchCondition"));
			System.out.println("request.getParameter(\"searchKeyword\")"+request.getParameter("searchKeyword"));
			//System.out.println("request 파싱 :"+(request.getQueryString()).split("&")[1]);

//		String url = request.getQueryString();
//		String contextPath = request.getContextPath();
//		//String path = url.substring(contextPath.length());
//
//		System.out.println(url);
//		System.out.println(contextPath);
//		//System.out.println(path);

			url = "forward:/product/listProduct.jsp";

	//	} 
//		else if (request.getQueryString().equals("menu=manage")){
//
//			
//				int page = 1;
//				if (request.getParameter("page") != null)
//					page = Integer.parseInt(request.getParameter("page"));
//
//				SearchVO searchVO = new SearchVO();
//				searchVO.setPage(page);
//				searchVO.setSearchCondition(request.getParameter("searchCondition"));
//				searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
//
//				String pageUnit = getServletContext().getInitParameter("pageSize");
//				searchVO.setPageUnit(Integer.parseInt(pageUnit));
//
//				ProductService service = new ProductServiceImpl();
//				HashMap<String, Object> map = service.getProductList(searchVO);
//
//				request.setAttribute("map", map);
//				request.setAttribute("searchVO", searchVO);
//
//				
//			
//				url = "forward:/product/updateProductView.jsp";
//
//		}
		
		//System.out.println(url);
		return url;
	}

	// return "forward:/product/listProduct.jsp";
}


