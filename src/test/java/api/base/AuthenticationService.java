package api.base;

import java.util.HashMap;

import api.pojo.request.LoginRequest;
import api.pojo.request.ResetPasswordRequest;
import api.pojo.request.SignUpRequest;
import io.restassured.response.Response;

public class AuthenticationService extends BaseService{
	
	private static final String BASE_PATH ="/api/auth/";
	
	public Response login(LoginRequest payload) {
		
		return postRequest(payload, BASE_PATH+"login");
	}
	
	public Response signup(SignUpRequest payload) {
		
		return postRequest(payload, BASE_PATH+"signup");
	}
	
	public Response forgotPassword(String emailAddress) {
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("email", emailAddress);
		return postRequest(payload, BASE_PATH+"forgot-password");
	} 
	
	public Response resetPassword(ResetPasswordRequest payload,String token) {
		return postTokenSetPassword(payload, BASE_PATH+"rest-password", token);
		
		
	}
	
	

}
