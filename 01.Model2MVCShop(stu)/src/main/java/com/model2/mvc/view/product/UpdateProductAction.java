package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction  extends Action{
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt( request.getParameter("prodNo"));
		
		//ProductVO vo=(ProductVO)session.getAttribute("productVO");
		
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replaceAll("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));

		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);

		HttpSession session = request.getSession();
		session.setAttribute("prduct", productVO);
//		int sessionNo = ((ProductVO) session.getAttribute("product")).getProdNo();
//		//System.out.println("데이터가 수정 되었습니다. : "+productVO);
//
//		if (sessionNo == prodNo) {
//			session.setAttribute("prduct", productVO);
//		}
		
		//System.out.println("UpdateProductAction 의 세션값 : "+session);

		return "redirect:/getProduct.do?prodNo=" + prodNo+"&menu=ok";
	}

}



