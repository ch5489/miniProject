package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Product product = new Product();
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate((request.getParameter("manuDate")).replaceAll("-", ""));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setProdDetail(request.getParameter("prodDetail"));

		product.setProdName(request.getParameter("prodName"));
		
		//productVO.setRegDate(request.getParameter("regDate"));
		//productVO.setProTranCode(request.getParameter("proTranCode"));
		

		System.out.println("데이터가 등록 되었습니다. : "+product);

		ProductService service = new ProductServiceImpl();
		service.addProduct(product);
		

		request.setAttribute("productVO", product);
		
		//System.out.println(productVO);
		System.out.println(request.getAttribute("productVO"));
		
		
		return "forward:/product/addProduct.jsp";
	}

}
