package com.model2.mvc.web.purchase;

import java.io.File; 
import java.net.URLEncoder;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value = "addPurchase", method = RequestMethod.GET)
	public ModelAndView addPurchase(@RequestParam("prodNo") int prodNo, HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchase : GET");
		String viewName = "/purchase/addPurchaseView.jsp";
		
		
		Product product = productService.getProduct(prodNo);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("user",session.getAttribute("user"));
		
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase( @ModelAttribute Purchase purchase, @RequestParam("prodNo") int prodNo, HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchase : POST");
		
		String viewName = "/purchase/readPurchase.jsp";
		
		Product product = productService.getProduct(prodNo);
		purchase.setPurchaseProd(product);
		purchase.setBuyer((User)session.getAttribute("user"));
		
		purchaseService.addPurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("purchase",purchase);
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "getPurchase", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("tranNo") int tranNo, @RequestParam("buyerId") String buyerId, Purchase purchase) throws Exception{
		
		System.out.println("/Purchase/getPurchase : GET");
		String viewName = "/purchase/getPurchase.jsp";
		
		User user = userService.getUser(buyerId);
		
		purchase.setBuyer(user);
		purchase.setTranNo(tranNo);
		
		purchaseService.getPurchase(purchase);
		
		
		model.addAttribute("product", product);
		System.out.println(product.getFileName());
		return "forward:/product/readProduct.jsp";
	}
	
	
	//@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println("/product/updateProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	//@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product ,  @RequestParam("fileNamePost") MultipartFile file, Model model , HttpSession session) throws Exception{
		
		System.out.println("/product/updateProduct : POST");
		String temDir = "/Users/ssg/miniProject/01.Model2MVCShop(stu)/src/main/webapp/images/uploadFiles";

		if (!file.isEmpty()) {

			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(temDir, fileName).toString();
			System.out.println();
			File dest = new File(filePath);
			file.transferTo(dest);
			product.setFileName(fileName);
		}
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?prodNo=" + product.getProdNo()+"&menu=ok";
	}
	@RequestMapping(value = "listPurchase")
	public ModelAndView listPurchase( @ModelAttribute("search") Search search, HttpSession session) throws Exception{
		
		System.out.println("/purchase/listPurchase : GET / POST");
		String viewName = "/purchase/listPurchase.jsp";
		
		//System.out.println(search);
		//System.out.println(session);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		User user = (User)session.getAttribute("user");
		
		search.setBuyerId(user.getUserId());
		
		System.out.println(";;;;;;;;;;;;;;;;;;;;"+search);
		
		// Business logic ผ๖วเ
		Map<String , Object> map=purchaseService.getPurchaseList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println(resultPage);
		System.out.println(map);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		
		//model.addAttribute("search", search);

		
		return modelAndView;
	}
	
}
