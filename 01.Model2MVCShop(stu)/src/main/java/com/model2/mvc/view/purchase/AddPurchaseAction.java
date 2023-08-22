package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddPurchaseAction extends Action{
	
	public String execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		Purchase purchase = new Purchase();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("[addproductAction] prodNo : "+prodNo);
		
		ProductService prodservice = new ProductServiceImpl();
		ProductVO product = prodservice.getProduct(prodNo);
		System.out.println("[addproductAction] productVO : "+product);
		
		String buyerId = request.getParameter("buyerId");
		UserService userservice=new UserServiceImpl();
		User user = userservice.getUser(buyerId);
		
		purchase.setBuyer(user);
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setPaymentOption(request.getParameter("receiverAddr"));
		purchase.setPurchaseProd(product);
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		
		System.out.println("데이터가 취합 되었습니다. : "+purchase);
		
		PurchaseService purchaseservice = new PurchaseServiceImpl();
		purchaseservice.insertPurchase(purchas);
		
		return "forward:/getPurchase.do?";
		
	}

}
