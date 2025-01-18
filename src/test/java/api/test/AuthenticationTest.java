package api.test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.base.AuthenticationService;
import api.pojo.request.LoginRequest;
import api.pojo.request.SignUpRequest;
import api.pojo.request.ResetPasswordRequest;
import api.pojo.response.LoginResponse;

@Listeners(api.listeners.TestListener.class)
public class AuthenticationTest {
	

	
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
		
		
		AuthenticationService authenticationService = new AuthenticationService();
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
				.email("neha123@gmail.com")
				.firstName("Neha")
				.lastName("Paygude")
				.userName("Neha@123")
				.password("Neha12345")
				.mobileNumber("5264898366")
				.Build();
		
		AuthenticationService authenticationService = new AuthenticationService();
		Response response = authenticationService.signup(signUpRequest);
		System.out.println(response.asPrettyString());
		//Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	@Test
	public void forgotPassword() {
		
		AuthenticationService authenticationService = new AuthenticationService();
		Response response= authenticationService.forgotPassword("neha123@gmail.com");
		System.out.println(response.asPrettyString());
		String token = response.jsonPath().getString("token");
		System.out.println(token);

		
	}
	
	@Test
	public void resetPassword() {
		AuthenticationService authenticationService = new AuthenticationService();
		Response response= authenticationService.forgotPassword("neha123@gmail.com");
		String token = response.jsonPath().getString("token");
		System.out.println(token);
		
		ResetPasswordRequest rs = new ResetPasswordRequest();
		rs.setToken(token);
		rs.setNewPassword("Neha@123");
		rs.setConfirmPassword("123@Neha");
		
		response = authenticationService.resetPassword(rs, token);
		System.out.println(response.asPrettyString());
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
