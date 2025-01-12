package api.test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.base.AuthenticationService;
import api.pojo.request.LoginRequest;
import api.pojo.request.SignUpRequest;
import api.pojo.response.LoginResponse;


public class AuthenticationTest {
	
	AuthenticationService authenticationService;
	Response response;

	
	@Test
	public void login() {
		/*
		RestAssured.baseURI="http://64.227.160.186:8080/";
		RequestSpecification x = RestAssured.given();
		RequestSpecification y = x.header("Content-Type","application/json");
		RequestSpecification z = y.body("{\"username\": \"Xyz@123\",\"password\": \"123456789\"}");
		Response response = z.post("api/auth/login");
		*/
		
		/*
		Response response = 
			given()
				.baseUri("http://64.227.160.186:8080/")
				.header("Content-Type","application/json")
				.body("{\"username\": \"Xyz@123\",\"password\": \"123456789\"}")
			.when()
				.post("api/auth/login");
		*/
		
		
		//AuthenticationService authenticationService = new AuthenticationService();
		LoginRequest loginRequest = new LoginRequest("Xyz@123", "123456789");
		//Serialization------> Converting POJO object Into JSON by passing LoginRequest
		Response response =authenticationService.login(loginRequest);
		//De-Serialization ----> To get  Login Response in the form of our created pojo class 
		//converting JSON Response into POJO Object  
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(response.asPrettyString());
		System.out.println("Token  :"+loginResponse.getToken());
		Assert.assertTrue(loginResponse.getToken() != null);;
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	
	@Test
	public void createAccount() {
		
		SignUpRequest signUpRequest = new SignUpRequest.Builder() //Builder is static class so we call it on over class 
				.email("abc123@gmail.com")
				.firstName("Disha")
				.lastName("Patni")
				.userName("Disha@123")
				.password("789456123")
				.mobileNumber("5264198366")
				.Build();
		
		//AuthenticationService authenticationService = new AuthenticationService();
		Response response = authenticationService.signup(signUpRequest);
		System.out.println(response);
		//Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	/*public static void loginUser() {	
		String bseurl = "http://64.227.160.186:8080/api/auth";
		String payload = "{\n" + "\"username\":\"Xyz@123\", \n" + "\"password\":\"123456789\" \n"+"}";
		
		Response response = 
				 given()
				 	.baseUri(bseurl)
				 	.header("Content-Type","application/json")
				 	.body(payload)
				.when()
					.post("/login")
				.then()
					.statusCode(200)
					.extract().response();
		
		System.out.println(response.asString());
		String token = response.jsonPath().getString("token");	
		System.out.println("User Login Token  :\n"+token);
				
	}*/

}
