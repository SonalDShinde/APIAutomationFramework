package api.base;

import api.pojo.request.LoginRequest;
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

}
