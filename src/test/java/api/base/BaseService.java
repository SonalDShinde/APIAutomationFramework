package api.base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.apache.poi.sl.usermodel.ObjectMetaData.Application;

import api.pojo.request.LoginRequest;
import api.pojo.request.ResetPasswordRequest;


public class BaseService {

/* what base service is doing 
 * BaseUri
 * Creating Request
 * Handling Response*/	
	
	public static final String BASE_URI="http://64.227.160.186:8080";
	private RequestSpecification requestseSpecification;
	
	public BaseService() {
		
		requestseSpecification = 
				 given()
				.baseUri(BASE_URI);
	}
	
	/*public Response postRequest(LoginRequest payload, String endpoint) {
		
		return requestseSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
	}
	This Post Request we need to use for other request classes like----> UserManagementRequest 
	for that we need to make this method generalize to use all over the project 
	this method should be loosely coupled
	so passing the Reference of parent class i.e superclass of all class 
	we passing Object----> see the below method 
	 * */
	
	public Response postRequest(Object payload, String endpoint) {
		
		return requestseSpecification
				.contentType(ContentType.JSON)
				.body(payload)
				.post(endpoint)
				.then()
				.extract()
				.response();
				
	}
	
	public Response postTokenSetPassword(Object payload, String endpoint, String token) {
		
		return requestseSpecification 
				.header("Authorization", "Bearer "+token)
				.contentType(ContentType.JSON)
				.body(payload)
				.post(endpoint);
	}
	
	public void setAuthToken(String token) {
		requestseSpecification
			.header("Authorization", "Bearer "+token);
	}
	
	public Response  getRequest(String endpoint) {
		
		return requestseSpecification
				.get(endpoint);
	}
	
	public Response putRequest(Object payload, String endpoint) {
		
		return requestseSpecification
				.contentType(ContentType.JSON)
				.body(payload)
				.put(endpoint);
				
	}
}
