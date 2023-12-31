package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction  extends Action{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt( request.getParameter("prodNo"));
		
		//Product vo=(Product)session.getAttribute("productVO");
		
		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate").replaceAll("-", ""));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));

		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);

		HttpSession session = request.getSession();
		session.setAttribute("prduct", product);
//		int sessionNo = ((Product) session.getAttribute("product")).getProdNo();
//		//System.out.println("데이터가 수정 되었습니다. : "+productVO);
//
//		if (sessionNo == prodNo) {
//			session.setAttribute("prduct", productVO);
//		}
		
		//System.out.println("UpdateProductAction 의 세션값 : "+session);

		return "redirect:/getProduct.do?prodNo=" + prodNo+"&menu=ok";
	}

}



