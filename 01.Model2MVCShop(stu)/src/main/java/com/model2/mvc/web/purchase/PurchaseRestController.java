package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public PurchaseRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
//	@RequestMapping(value="/json/updateTranCode/{prodNo}")
//	public Model updateTranCode(@PathVariable("prodNo") int prodNo, @RequestParam("tranCode") String tranCode, Purchase purchase, Model model) throws Exception{
//		
//		System.out.println("/purchase/json/updateTranCode : GET");
//		
//		System.out.println(";;;;전;;;;"+purchase);
//		
//		purchase.setTranCode(tranCode);
//		purchase.setPurchaseProd(productService.getProduct(prodNo));
//		
//		System.out.println(";;;;후;;;;"+purchase);
//		
//		purchaseService.updateTranCode(purchase);
//		
//		purchase = purchaseService.getPurchaseProd(prodNo);
//		System.out.println(";;;;;;;purchase 에 값이 많으면 get은 잘 돌았따,,"+purchase);
//		
//		model.addAttribute("purchase", purchase);
//		System.out.println("\b;;;;;;;;;내보낼 modle 값"+model);
//		
//		return model;
//	}
	
	@RequestMapping(value="/json/updateTranCode/{prodNo}")
	public Purchase updateTranCode(@PathVariable("prodNo") int prodNo, @RequestParam("tranCode") String tranCode, Purchase purchase, Model model) throws Exception{
		
		System.out.println("/purchase/json/updateTranCode : GET");
		
		System.out.println(";;;;전;;;;"+purchase);
		
		purchase.setTranCode(tranCode);
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		System.out.println(";;;;후;;;;"+purchase);
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchaseProd(prodNo);
		System.out.println(";;;;;;;purchase 에 값이 많으면 get은 잘 돌았따,,"+purchase);
		
		model.addAttribute("purchase", purchase);
		System.out.println("\b;;;;;;;;;내보낼 modle 값"+model);
		
		return purchase;
	}
	
}
