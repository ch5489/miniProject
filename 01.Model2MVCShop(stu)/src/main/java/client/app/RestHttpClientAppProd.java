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
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.getProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.getProductTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 3.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.AddProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.AddProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.updateProductTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.updateProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.3 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.updateProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 4.4 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.updateProductTest_Codehaus();	
	
//		System.out.println("\n====================================\n");
//		// 6.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.ListProductTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 6.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.ListProductTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 6.3 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientAppProd.ListProductTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 6.4 Http Post ��� Request : CodeHaus lib ���
		RestHttpClientAppProd.ListProductTest_Codehaus();	
		
		
		
		
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getProductTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10000";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10000";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
//================================================================//	
	
	//================================================================//
		//3.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
		public static void AddProductTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/addProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ ��� 1 : String ���]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ ��� 2 : JSONObject ���]
			JSONObject json = new JSONObject();
			json.put("prodName", "RestTest03");
			json.put("prodDetail", "RestTest03");
			json.put("manuDate", "2023-09-09");
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
		}
		
		
		//3.2 Http Protocol POST ��� Request : FromData���� 
		//==> JsonSimple + codehaus 3rd party lib ���
		public static void AddProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/addProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ ��� 2 : JSONObject ���]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			
			//[ ��� 3 : codehaus ���]
			Product prod01 =  new Product();
			prod01.setProdName("RestTest04");
			prod01.setProdDetail("RestTest04");
			prod01.setManuDate("2023-09-09");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(prod01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			Product user = objectMapper.readValue(jsonobj.toString(), Product.class);
			 System.out.println(user);
		}
		
//		//================================================================//
//		//4.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
//		public static void updateProductTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol �� client �߻�ȭ 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/updateProduct/1500000";
//					
//			// HttpGet : Http Protocol �� GET ��� Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol ���� Message �߻�ȭ
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response Ȯ��
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response �� entity(DATA) Ȯ��
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream ����
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
//			
//			//==> �����б�(JSON Value Ȯ��)
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
//			System.out.println(jsonobj);
//		}
//		
//		
//		//4.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
//		public static void updateProductTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol �� client �߻�ȭ 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/updateProduct/1510000";
//
//			// HttpGet : Http Protocol �� GET ��� Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol ���� Message �߻�ȭ
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response Ȯ��
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response �� entity(DATA) Ȯ��
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream ����
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			//==> �ٸ� ������� serverData ó�� 
//			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
//			//String serverData = br.readLine();
//			//System.out.println(serverData);
//			
//			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
//			System.out.println(jsonobj);
//		
//			ObjectMapper objectMapper = new ObjectMapper();
//			 Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
//			 System.out.println(user);
//		}

		// ================================================================//
		// 4.3 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
		public static void updateProductTest_JsonSimple() throws Exception {

			// HttpClient : Http Protocol �� client �߻�ȭ
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ ��� 1 : String ���]
//						String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//						HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

			// [ ��� 2 : JSONObject ���]
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

			// ==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);

			// ==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject) JSONValue.parse(serverData);
			System.out.println(jsonobj);

		}

		// 4.4 Http Protocol POST ��� Request : FromData����
		// ==> JsonSimple + codehaus 3rd party lib ���
		public static void updateProductTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol �� client �߻�ȭ
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

//					//[ ��� 1 : String ���]
//					String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//					HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//					//[ ��� 2 : JSONObject ���]
//					JSONObject json = new JSONObject();
//					json.put("userId", "admin");
//					json.put("password", "1234");
//					HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			// [ ��� 3 : codehaus ���]
			
			Product product = new Product();
			product.setProdName("RestChangeTest02");
			product.setProdDetail("RestChangeTest02");
			product.setManuDate("2023-09-08");
			product.setPrice(15000);
			product.setFileName("RestChangeTest02");
			product.setProdNo(1510000);
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(product);

			System.out.println(jsonValue);

			HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			// ==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			// ==> �ٸ� ������� serverData ó��
			// System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			// String serverData = br.readLine();
			// System.out.println(serverData);

			// ==> API Ȯ�� : Stream ��ü�� ���� ����
			JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
			System.out.println(jsonobj);

			ObjectMapper objectMapper = new ObjectMapper();
			Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
			System.out.println(user);
		}
		
//		//================================================================//
//		//6.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
//		public static void ListProductTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol �� client �߻�ȭ 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/listProduct?currentPage=5";
//					
//			// HttpGet : Http Protocol �� GET ��� Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol ���� Message �߻�ȭ
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response Ȯ��
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response �� entity(DATA) Ȯ��
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream ����
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
//			
//			//==> �����б�(JSON Value Ȯ��)
//			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
//			System.out.println(jsonobj);
//		}
//		
//		
//		//6.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
//		public static void ListProductTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol �� client �߻�ȭ 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/product/json/listProduct?currentPage=5";
//
//			// HttpGet : Http Protocol �� GET ��� Request
//			HttpGet httpGet = new HttpGet(url);
//			httpGet.setHeader("Accept", "application/json");
//			httpGet.setHeader("Content-Type", "application/json");
//			
//			// HttpResponse : Http Protocol ���� Message �߻�ȭ
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			
//			//==> Response Ȯ��
//			System.out.println(httpResponse);
//			System.out.println();
//
//			//==> Response �� entity(DATA) Ȯ��
//			HttpEntity httpEntity = httpResponse.getEntity();
//			
//			//==> InputStream ����
//			InputStream is = httpEntity.getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//			
//			//==> �ٸ� ������� serverData ó�� 
//			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
//			//String serverData = br.readLine();
//			//System.out.println(serverData);
//			
//			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
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
		//6.3 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
		public static void ListProductTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ ��� 1 : String ���]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ ��� 2 : JSONObject ���]
			JSONObject json = new JSONObject();
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
		}
		
		
		//6.4 Http Protocol POST ��� Request : FromData���� 
		//==> JsonSimple + codehaus 3rd party lib ���
		public static void ListProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ ��� 2 : JSONObject ���]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			
			//[ ��� 3 : codehaus ���]
			
			Search search = new Search();
			search.setCurrentPage(5);
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(search);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			 Map user = objectMapper.readValue(jsonobj.toString(), Map.class);
			 System.out.println(user);
		}	
		

	
}