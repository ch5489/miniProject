package com.model2.mvc.web.product;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value = "addProduct", method = RequestMethod.GET)
	public String addProduct() throws Exception{
		
		System.out.println("/product/addProduct : GET");
		
		return "redirect:/product/addProduct.jsp";
	}
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("fileNamePost") MultipartFile file) throws Exception{
		
		System.out.println("/product/addProduct : POST");
		
		String temDir ="/Users/ssg/git/miniProject/01.Model2MVCShop(stu)/src/main/webapp/images/uploadFiles";
		
		
		if(!file.isEmpty()) {
			
			String fileName = file.getOriginalFilename();
			String utf8fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
			String filePath = Paths.get(temDir, utf8fileName).toString();
			
            File dest = new File(filePath);
            file.transferTo(dest);
            System.out.println(utf8fileName);
			product.setFileName(utf8fileName);
		}
		
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.addProduct(product);
		return "forward:/product/addProduct.jsp";
	}
	
	
	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println("/product/getProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		
		model.addAttribute("product", product);
		System.out.println(product.getFileName());
		return "forward:/product/readProduct.jsp";
	}
	
	
	@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
		
		System.out.println("/product/updateProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product ,  @RequestParam("fileNamePost") MultipartFile file, Model model , HttpSession session) throws Exception{
		
		System.out.println("/product/updateProduct : POST");
		String temDir = "/Users/ssg/git/miniProject/01.Model2MVCShop(stu)/src/main/webapp/images/uploadFiles";

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
	

	@RequestMapping(value = "listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/product/listProduct : GET / POST");
		
		System.out.println(search);
		System.out.println(model);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		//System.out.println(search);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		//System.out.println("model.addAttribute-----1");
		model.addAttribute("resultPage", resultPage);
		//System.out.println("model.addAttribute-----2");
		model.addAttribute("search", search);
		//System.out.println("model.addAttribute-----3");
		
		return "forward:/product/listProduct.jsp";
	}
	
}
