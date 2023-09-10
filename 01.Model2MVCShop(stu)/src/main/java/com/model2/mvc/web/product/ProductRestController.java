package com.model2.mvc.web.product;

import java.util.HashMap;
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

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	//@RequestMapping(value = "addProduct", method = RequestMethod.GET)
	public String addProduct() throws Exception{
		
		System.out.println("/product/addProduct : GET");
		
		return "redirect:/product/addProduct.jsp";
	}
	
	@RequestMapping(value = "/json/addProduct", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		productService.addProduct(product);
		
		return productService.getProduct(1530000);
	}
	
	
	@RequestMapping(value = "/json/getProduct/{prodNo}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		
		return product;
	}
	
	
	@RequestMapping(value="/json/updateProduct/{prodNo}", method=RequestMethod.GET)
	public Map updateProduct(@PathVariable("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println("/product/json/updateProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		Map map = new HashMap();
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping(value="/json/updateProduct", method=RequestMethod.POST)
	public Product updateProduct( @RequestBody Product product , HttpSession session) throws Exception{
		
		System.out.println("/product/json/updateProduct : POST");
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		System.out.println(product);
		productService.updateProduct(product);
		
		return productService.getProduct(product.getProdNo());
	}
	

	@RequestMapping(value = "/json/listProduct")
	public Map listProduct( @ModelAttribute("search") Search searchGet ,@RequestBody Search searchPost , HttpServletRequest request) throws Exception{
		
		System.out.println("/product/json/listProduct : GET / POST");
		
		Search search = (searchGet.getCurrentPage() == 0? searchPost:searchGet);
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map returnmap = new HashMap();
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		//System.out.println(search);
		
		// Model 과 View 연결
		returnmap.put("list", map.get("list"));
		//System.out.println("model.addAttribute-----1");
		returnmap.put("resultPage", resultPage);
		//System.out.println("model.addAttribute-----2");
		returnmap.put("search", search);
		//System.out.println("model.addAttribute-----3");
		
		return returnmap;
	}
	
}
