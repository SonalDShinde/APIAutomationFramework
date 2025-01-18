package api.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.base.AuthenticationService;
import api.base.UserManagementService;
import api.pojo.request.LoginRequest;
import api.pojo.request.ProfileRequest;
import api.pojo.response.LoginResponse;
import api.pojo.response.UserProfileResponse;
import io.restassured.response.Response;


@Listeners(api.listeners.TestListener.class)
public class UserManagementTest {
	
	@Test
	public void getProfileTest() {
		
		AuthenticationService authenticationService = new AuthenticationService();
		LoginRequest loginRequest = new LoginRequest("Xyz@123", "123456789");
		Response response = authenticationService.login(loginRequest);
		//Response response= authenticationService.login(new LoginRequest("Xyz@123","123456789"));
		LoginResponse loginResponse=response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		
		UserManagementService userManagementService = new UserManagementService();
		response = userManagementService.getProfile(loginResponse.getToken()); 
		System.out.println(response.as(UserProfileResponse.class)); //De-serialization 
	}
	@Test
	public void updateProfileTest() {
		AuthenticationService authenticationService = new  AuthenticationService();
		Response response = authenticationService.login(new LoginRequest("Xyz@123","123456789"));
		LoginResponse loginResponse =response.as(LoginResponse.class);
		System.out.println(response.asPrettyString());
		
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		UserManagementService managementService = new UserManagementService();
		response = managementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		UserProfileResponse profileResponse = response.as(UserProfileResponse.class);
		Assert.assertEquals(profileResponse.getUsername(), "Xyz@123");
		
		System.out.println("-------------------------------------------------------------------------------------------------------");

		ProfileRequest profileRequest = new ProfileRequest.Builder()
				.firstName("Sona")
				.lastName("anthony")
				.email("77soanash15@gmail.com")
				.mobileNumber("9800657438")
				.build();
		
		response = managementService.updaterofile(loginResponse.getToken(), profileRequest);
		System.out.println(response.asPrettyString());
		}

}
