package client.app;

import java.io.BufferedReader;  
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientAppProd {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.getProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.getProductTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 3.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.AddProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.AddProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.updateProductTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.updateProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.3 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.updateProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 4.4 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.updateProductTest_Codehaus();	
	
//		System.out.println("\n====================================\n");
//		// 6.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.ListProductTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 6.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.ListProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 6.3 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientAppProd.ListProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 6.4 Http Post 방식 Request : CodeHaus lib 사용
		RestHttpClientAppProd.ListProductTest_Codehaus();	
		
		
		
		
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getProductTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10000";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10000";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
//================================================================//	
	
	//================================================================//
		//3.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void AddProductTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/addProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("prodName", "RestTest03");
			json.put("prodDetail", "RestTest03");
			json.put("manuDate", "2023-09-09");
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
		}
		
		
		//3.2 Http Protocol POST 방식 Request : FromData전달 
		//==> JsonSimple + codehaus 3rd party lib 사용
		public static void AddProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/addProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ 방법 2 : JSONObject 사용]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			
			//[ 방법 3 : codehaus 사용]
			Product prod01 =  new Product();
			prod01.setProdName("RestTest04");
			prod01.setProdDetail("RestTest04");
			prod01.setManuDate("2023-09-09");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(prod01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			Product user = objectMapper.readValue(jsonobj.toString(), Product.class);
			 System.out.println(user);
		}
		
//		//================================================================//
//		//4.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//		public static void updateProductTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/updateProduct/1500000";
//					
//			// HttpGet : Http Protocol 의 GET 방식 Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol 응답 Message 추상화
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response 확인
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response 중 entity(DATA) 확인
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream 생성
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			System.out.println("[ Server 에서 받은 Data 확인 ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
//			
//			//==> 내용읽기(JSON Value 확인)
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
//			System.out.println(jsonobj);
//		}
//		
//		
//		//4.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
//		public static void updateProductTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/updateProduct/1510000";
//
//			// HttpGet : Http Protocol 의 GET 방식 Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol 응답 Message 추상화
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response 확인
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response 중 entity(DATA) 확인
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream 생성
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			//==> 다른 방법으로 serverData 처리 
//			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
//			//String serverData = br.readLine();
//			//System.out.println(serverData);
//			
//			//==> API 확인 : Stream 객체를 직접 전달 
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
//			System.out.println(jsonobj);
//		
//			ObjectMapper objectMapper = new ObjectMapper();
//			 Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
//			 System.out.println(user);
//		}

		// ================================================================//
		// 4.3 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void updateProductTest_JsonSimple() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ 방법 1 : String 사용]
//						String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//						HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

			// [ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("prodName", "RestChangeTest01");
			json.put("prodDetail", "RestChangeTest01");
			json.put("manuDate", "2023-09-08");
			json.put("price", 15000);
			json.put("fileName", "RestChangeTest01");
			json.put("prodNo", 1500000);

			HttpEntity httpEntity01 = new StringEntity(json.toString(), "utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			// ==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);

			// ==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
			System.out.println(jsonobj);

		}

		// 4.4 Http Protocol POST 방식 Request : FromData전달
		// ==> JsonSimple + codehaus 3rd party lib 사용
		public static void updateProductTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

//					//[ 방법 1 : String 사용]
//					String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//					HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//					//[ 방법 2 : JSONObject 사용]
//					JSONObject json = new JSONObject();
//					json.put("userId", "admin");
//					json.put("password", "1234");
//					HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			// [ 방법 3 : codehaus 사용]
			
			Product product = new Product();
			product.setProdName("RestChangeTest02");
			product.setProdDetail("RestChangeTest02");
			product.setManuDate("2023-09-08");
			product.setPrice(15000);
			product.setFileName("RestChangeTest02");
			product.setProdNo(1510000);
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(product);

			System.out.println(jsonValue);

			HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			// ==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			// ==> 다른 방법으로 serverData 처리
			// System.out.println("[ Server 에서 받은 Data 확인 ] ");
			// String serverData = br.readLine();
			// System.out.println(serverData);

			// ==> API 확인 : Stream 객체를 직접 전달
			JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
			System.out.println(jsonobj);

			ObjectMapper objectMapper = new ObjectMapper();
			Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
			System.out.println(user);
		}
		
//		//================================================================//
//		//6.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//		public static void ListProductTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/listProduct?currentPage=5";
//					
//			// HttpGet : Http Protocol 의 GET 방식 Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol 응답 Message 추상화
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response 확인
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response 중 entity(DATA) 확인
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream 생성
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			System.out.println("[ Server 에서 받은 Data 확인 ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
//			
//			//==> 내용읽기(JSON Value 확인)
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
//			System.out.println(jsonobj);
//		}
//		
//		
//		//6.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
//		public static void ListProductTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/listProduct?currentPage=5";
//
//			// HttpGet : Http Protocol 의 GET 방식 Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol 응답 Message 추상화
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response 확인
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response 중 entity(DATA) 확인
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream 생성
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			//==> 다른 방법으로 serverData 처리 
//			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
//			//String serverData = br.readLine();
//			//System.out.println(serverData);
//			
//			//==> API 확인 : Stream 객체를 직접 전달 
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
//			System.out.println(jsonobj);
//		
//			ObjectMapper objectMapper = new ObjectMapper();
//			 
//			 Map returnmap = objectMapper.readValue(jsonobj.toString(), Map.class);
//			 System.out.println(returnmap);
//			 
//		}
	//================================================================//	
		
	//================================================================//
		//6.3 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void ListProductTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
		}
		
		
		//6.4 Http Protocol POST 방식 Request : FromData전달 
		//==> JsonSimple + codehaus 3rd party lib 사용
		public static void ListProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ 방법 2 : JSONObject 사용]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			
			//[ 방법 3 : codehaus 사용]
			
			Search search = new Search();
			search.setCurrentPage(5);
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(search);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			 Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
			 System.out.println(user);
		}	
		

	
}