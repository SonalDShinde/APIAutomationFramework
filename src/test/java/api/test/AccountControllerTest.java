package api.test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.apache.http.auth.AUTH;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AccountControllerTest {

	
	


/*
	    @Test
	    public void testGetAccountDetails() {
	        // Step 1: Send POST request to get account number
	        String postPayload = "{\n" +
	                             "  \"username\": \"testuser\",\n" +
	                             "  \"password\": \"password123\"\n" +
	                             "}";

	        Response postResponse = given()
	                                    .baseUri("http://64.227.160.186:8080/api")  // Adjust base URI
	                                    .header("Content-Type", "application/json")
	                                    .body(postPayload)
	                                .when()
	                                    .post("/login")
	                                .then()
	                                    .statusCode(200) // Check status code
	                                    .extract()
	                                    .response();

	        // Extract account number from POST response body
	        String accountNumber = postResponse.jsonPath().getString("accountNumber");

	        // Step 2: Use the extracted account number in the GET request
	        Response getResponse = given()
	                                    .baseUri("http://64.227.160.186:8080/api")  // Adjust base URI
	                                    .pathParam("accountNumber", accountNumber)   // Use path param
	                                .when()
	                                    .get("/accounts/{accountNumber}")
	                                .then()
	                                    .statusCode(200) // Check status code
	                                    .extract()
	                                    .response();
*/
	        // Step 3: Validate the GET response (e.g., check if account details are correct)
//	        getResponse.then()
//	                   .body("accountNumber", equalTo(accountNumber)); // Validate account number in response
//	    
	
	   

	        private String accountNumber;
	        private String token;
	        

	    	//@Test
	    	public void loginUser() {	
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
	    				
	    	}

	      //  @Test(priority = 1,dependsOnMethods = "AuthenticationTest.loginUser")
	        public void postLoginAndExtractAccountNumber() {
	            // POST request to login and extract account number
	            String postPayload = "{\n" +
	                                 "  \"accountType\": \"SAVINGS\",\n" +
	                                 "  \"branch\": \"Main Branch\"\n" +
	                                 "}";

	            Response postResponse = given()
	                                        .baseUri("http://64.227.160.186:8080/api")
	                                        .header("Authorization", "Bearer "+token)
	                                        .header("Content-Type", "application/json")
	                                        .body(postPayload)
	                                    .when()
	                                        .post("/accounts")
	                                    .then()
	                                       // .statusCode(200) // Check if the response is successful
	                                        .extract()
	                                        .response();

	            // Extract account number from POST response
	            System.out.println(postResponse.asString());
	            System.out.println(postResponse.jsonPath().getString("accountNumber"));
//	            accountNumber = postResponse.jsonPath().getString("accountNumber");
//	            System.out.println(accountNumber);
	        }

	       // @Test(priority = 2, dependsOnMethods = "postLoginAndExtractAccountNumber")
	        public void getAccountDetails() {
	            // GET request to retrieve account details using the account number from POST
	            Response getResponse = given()
	                                        .baseUri("http://64.227.160.186:8080/api")
	                                        .pathParam("accountNumber", accountNumber)  // Use accountNumber from POST
	                                    .when()
	                                        .get("/accounts/{accountNumber}")
	                                    .then()
	                                        .statusCode(200) // Check if the response is successful
	                                        .extract()
	                                        .response();

	            // Validate the account number in the GET response
//	            getResponse.then()
//	                       .body("accountNumber", equalTo(accountNumber));  // Validate if the accountNumber matches
//	            
	            
	            Assert.assertEquals(getResponse.jsonPath().getString("accountNumber"),accountNumber);
	            System.out.println(getResponse);
	        }
	    }



