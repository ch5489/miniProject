package com.model2.mvc.service.product.test;

import java.util.List; 
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transacㅊㅊㅊ tion.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		ProductVO product = new ProductVO();
		product.setFileName("testFile");
		product.setManuDate("20230808");
		product.setPrice(999999999);
		product.setProdDetail("testFile입니다");
		product.setProdName("testFile");
		
		
		productService.addProduct(product);
		
		product = productService.getProduct(590000);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testFile", product.getFileName());
		Assert.assertEquals("20230808", product.getManuDate());
		Assert.assertEquals(999999999, product.getPrice());
		Assert.assertEquals("testFile입니다", product.getProdDetail());
		Assert.assertEquals("testFile", product.getProdName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		ProductVO product = new ProductVO();
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		product = productService.getProduct(590000);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("changetestFile", product.getFileName());
		Assert.assertEquals("20230909", product.getManuDate());
		Assert.assertEquals(500000, product.getPrice());
		Assert.assertEquals("changetestFile입니다", product.getProdDetail());
		Assert.assertEquals("changetestFile", product.getProdName());

		Assert.assertNotNull(productService.getProduct(100000));
		//Assert.assertNotNull(productService.getProduct(48484848));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		ProductVO product = productService.getProduct(590000);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testFile", product.getFileName());
		Assert.assertEquals("20230808", product.getManuDate());
		Assert.assertEquals(999999999, product.getPrice());
		Assert.assertEquals("testFile입니다", product.getProdDetail());
		Assert.assertEquals("testFile", product.getProdName());

		product.setFileName("changetestFile");
		product.setManuDate("20230909");
		product.setPrice(500000);
		product.setProdDetail("changetestFile입니다");
		product.setProdName("changetestFile");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(590000);
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("changetestFile", product.getFileName());
		Assert.assertEquals("20230909", product.getManuDate());
		Assert.assertEquals(500000, product.getPrice());
		Assert.assertEquals("changetestFile입니다", product.getProdDetail());
		Assert.assertEquals("changetestFile", product.getProdName());
	 }
	 
//	//@Test
////	public void testCheckDuplication() throws Exception{
////
////		//==> 필요하다면...
//////		User user = new User();
//////		user.setUserId("testUserId");
//////		user.setUserName("testUserName");
//////		user.setPassword("testPasswd");
//////		user.setSsn("1111112222222");
//////		user.setPhone("111-2222-3333");
//////		user.setAddr("경기도");
//////		user.setEmail("test@test.com");
//////		
//////		userService.addUser(user);
////		
////		//==> console 확인
////		System.out.println(userService.checkDuplication("testUserId"));
////		System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
////
////		//==> API 확인
////		Assert.assertFalse( userService.checkDuplication("testUserId") );
////	 	Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
////		 	
////	}
//	
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	SearchVO searchVO = new SearchVO();
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	searchVO.setSearchCondition("0");
	 	searchVO.setSearchKeyword("");
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	// @Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	SearchVO searchVO= new SearchVO();
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	searchVO.setSearchCondition("0");
	 	searchVO.setSearchKeyword("10000");
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//System.out.println(list);
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("0");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
//	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	SearchVO searchVO = new SearchVO();
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	searchVO.setSearchCondition("1");
	 	searchVO.setSearchKeyword("태현");
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("1");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 @Test
	 public void testGetProductListByPrice() throws Exception{
		 
	 	SearchVO searchVO = new SearchVO();
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	searchVO.setSearchCondition("2");
	 	searchVO.setSearchKeyword("111");
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("2");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	
}