package api.base;

import api.pojo.request.ProfileRequest;
import io.restassured.response.Response;

public class UserManagementService extends BaseService {

	private static final String Base_Path ="/api/users/";
	
	public Response getProfile(String token) {
		setAuthToken(token);
		return getRequest(Base_Path+"profile");
	}
	
	public Response updaterofile(String token,ProfileRequest payload) {
		setAuthToken(token);
		return putRequest(payload, Base_Path+"profile");
	}
}
