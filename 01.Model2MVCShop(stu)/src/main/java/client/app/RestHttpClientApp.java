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

import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.LoginTest_Codehaus();		
		
//		System.out.println("\n====================================\n");
//		// 3.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.AddUserTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.AddUserTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.updateUserTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.updateUserTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 4.3 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.UpdateUserTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 4.4 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.UpdateUserTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 5.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.checkDuplicationTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 5.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.checkDuplicationTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 6.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.ListUserTest_JsonSimple();	
		
//		System.out.println("\n====================================\n");
//		// 6.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.ListUserTest_Codehaus();	
		
//		System.out.println("\n====================================\n");
//		// 6.3 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.ListUserTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		// 6.4 Http Post 방식 Request : CodeHaus lib 사용
		RestHttpClientApp.ListUserTest_Codehaus();	
		
		
		
		
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
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
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

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
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
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
	
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
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
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
	//================================================================//
		//3.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void AddUserTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/user/json/addUser";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("userId", "user8080");
			json.put("password", "8080");
			json.put("password2", "8080");
			json.put("userName", "팔공팔공");
			
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
		public static void AddUserTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/user/json/addUser";
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
			User user01 =  new User();
			user01.setUserId("user9090");
			user01.setPassword("9090");
			user01.setUserName("구공구공");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(user01);
			
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
			 User user = objectMapper.readValue(jsonobj.toString(), User.class);
			 System.out.println(user);
		}
		
//		//================================================================//
//		//4.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//		public static void updateUserTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/user/json/updateUser/user8080";
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
//		public static void updateUserTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/user/json/updateUser/user9090";
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
//			 User user = objectMapper.readValue(jsonobj.toString(), User.class);
//			 System.out.println(user);
//		}

		// ================================================================//
		// 4.3 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void UpdateUserTest_JsonSimple() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/user/json/updateUser";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ 방법 1 : String 사용]
//						String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//						HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

			// [ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("userId", "user8080");
			json.put("userName", "공팔공팔");
			json.put("phone", "010-1234-1234");
			json.put("addr", "애틀랜타");
			json.put("email", "abc@gmail.com");

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
		public static void UpdateUserTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/user/json/updateUser";
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
			User user01 = new User();
			user01.setUserId("user9090");
			user01.setUserName("공구공구");
			user01.setAddr("파리");
			user01.setEmail("abc@gmail.com");
			user01.setPhone("010-1234-1234");
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(user01);

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
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println(user);
		}
		
		// ================================================================//
		// 5.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void checkDuplicationTest_JsonSimple() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/user/json/checkDuplication";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ 방법 1 : String 사용]
//								String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//								HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

			// [ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("userId", "user8080");

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

		// 5.2 Http Protocol POST 방식 Request : FromData전달
		// ==> JsonSimple + codehaus 3rd party lib 사용
		public static void checkDuplicationTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/user/json/checkDuplication";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

//							//[ 방법 1 : String 사용]
//							String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//							HttpEntity httpEntity01 = new StringEntity(data,"utf-8");

//							//[ 방법 2 : JSONObject 사용]
//							JSONObject json = new JSONObject();
//							json.put("userId", "admin");
//							json.put("password", "1234");
//							HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			// [ 방법 3 : codehaus 사용]
			User user01 = new User();
			user01.setUserId("user9090");
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(user01);

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
			User user = objectMapper.readValue((String)jsonobj.get("userId"), User.class);
			System.out.println(user);
			Boolean boo = objectMapper.readValue(String.valueOf(jsonobj.get("result")), Boolean.class);
			
			System.out.println(boo);
		}
		
//		//================================================================//
//		//6.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
//		public static void ListUserTest_JsonSimple() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/user/json/listUser?currentPage=1";
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
//		public static void ListUserTest_Codehaus() throws Exception{
//			
//			// HttpClient : Http Protocol 의 client 추상화 
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			String url= 	"http://127.0.0.1:8080/user/json/listUser";
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
//			Map returnmap = new HashMap();
//			returnmap = objectMapper.readValue(jsonobj.toString(), Map.class);
//			 System.out.println(returnmap.get("list"));
//			 System.out.println(returnmap.get("resultPage"));
//		}
	//================================================================//	
		
	//================================================================//
		//6.3 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void ListUserTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/user/json/listUser";
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
		public static void ListUserTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/user/json/listUser";
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