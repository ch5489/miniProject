package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddProductAction extends Action {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductVO productVO = new ProductVO();
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate((request.getParameter("manuDate")).replaceAll("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setProdDetail(request.getParameter("prodDetail"));

		productVO.setProdName(request.getParameter("prodName"));
		
		//productVO.setRegDate(request.getParameter("regDate"));
		//productVO.setProTranCode(request.getParameter("proTranCode"));
		

		System.out.println("데이터가 등록 되었습니다. : "+productVO);

		ProductService service = new ProductServiceImpl();
		service.addProduct(productVO);
		

		request.setAttribute("productVO", productVO);
		
		//System.out.println(productVO);
		System.out.println(request.getAttribute("productVO"));
		
		
		return "forward:/product/addProduct.jsp";
	}

}
