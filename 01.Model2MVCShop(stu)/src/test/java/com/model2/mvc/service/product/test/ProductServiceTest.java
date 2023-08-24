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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transac������ tion.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		ProductVO product = new ProductVO();
		product.setFileName("testFile");
		product.setManuDate("20230808");
		product.setPrice(999999999);
		product.setProdDetail("testFile�Դϴ�");
		product.setProdName("testFile");
		
		
		productService.addProduct(product);
		
		product = productService.getProduct(590000);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("testFile", product.getFileName());
		Assert.assertEquals("20230808", product.getManuDate());
		Assert.assertEquals(999999999, product.getPrice());
		Assert.assertEquals("testFile�Դϴ�", product.getProdDetail());
		Assert.assertEquals("testFile", product.getProdName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		ProductVO product = new ProductVO();
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		product = productService.getProduct(590000);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("changetestFile", product.getFileName());
		Assert.assertEquals("20230909", product.getManuDate());
		Assert.assertEquals(500000, product.getPrice());
		Assert.assertEquals("changetestFile�Դϴ�", product.getProdDetail());
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
		Assert.assertEquals("testFile�Դϴ�", product.getProdDetail());
		Assert.assertEquals("testFile", product.getProdName());

		product.setFileName("changetestFile");
		product.setManuDate("20230909");
		product.setPrice(500000);
		product.setProdDetail("changetestFile�Դϴ�");
		product.setProdName("changetestFile");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(590000);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(user);
			
		//==> API Ȯ��
		Assert.assertEquals("changetestFile", product.getFileName());
		Assert.assertEquals("20230909", product.getManuDate());
		Assert.assertEquals(500000, product.getPrice());
		Assert.assertEquals("changetestFile�Դϴ�", product.getProdDetail());
		Assert.assertEquals("changetestFile", product.getProdName());
	 }
	 
//	//@Test
////	public void testCheckDuplication() throws Exception{
////
////		//==> �ʿ��ϴٸ�...
//////		User user = new User();
//////		user.setUserId("testUserId");
//////		user.setUserName("testUserName");
//////		user.setPassword("testPasswd");
//////		user.setSsn("1111112222222");
//////		user.setPhone("111-2222-3333");
//////		user.setAddr("��⵵");
//////		user.setEmail("test@test.com");
//////		
//////		userService.addUser(user);
////		
////		//==> console Ȯ��
////		System.out.println(userService.checkDuplication("testUserId"));
////		System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
////
////		//==> API Ȯ��
////		Assert.assertFalse( userService.checkDuplication("testUserId") );
////	 	Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
////		 	
////	}
//	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	SearchVO searchVO = new SearchVO();
	 	searchVO.setPage(1);
	 	searchVO.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
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
	 	
	 	//==> console Ȯ��
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
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("0");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
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
	 	searchVO.setSearchKeyword("����");
	 	Map<String,Object> map = productService.getProductList(searchVO);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("1");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
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
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	searchVO.setSearchCondition("2");
	 	searchVO.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(searchVO);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	
}