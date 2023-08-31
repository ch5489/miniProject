package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductViewAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		//System.out.println(prodNo);
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		//System.out.println("productVO :"+productVO);
		request.setAttribute("productVO", product);
		//System.out.println("request.attribute :"+request.getAttribute("productVO"));

		return "forward:/product/updateProductView.jsp";
	}

}
