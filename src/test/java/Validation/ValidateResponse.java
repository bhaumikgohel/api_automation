package Validation;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ValidateResponse {

	@Test(priority=1)
	void ValidateResponsedata() {
		
		given()
			
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.body("data.last_name", equalTo("Weaver"));
		
		
		
	}
	
	@Test(priority=2)
	void ValidareResponsedata_using_variable() {
		Response res = given()
					
				.when()
					.get("https://reqres.in/api/users/2");
		
		Assert.assertEquals(res.statusCode(), 200);
		
		String firstname = res.jsonPath().get("data.first_name").toString();
		
		Assert.assertEquals(firstname, "Janet");
		
		
	}
	
	@Test(priority=3)
	void Validate_Firstnames() {
		Response res = given()
				.when()
					.get("https://reqres.in/api/users");
		
		JSONObject jo = new JSONObject(res.asString());
		
		boolean status = false;
		// its fatch specific data from JSON Response Object 
		for(int i =0; i<jo.getJSONArray("data").length();i++) {
			//Get the JSON ARRAY, Get The value from i. Get The First name from JSON OBJ and Convert to String
			String user = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			
			//Print the data
			System.out.println(user);
			
			
			if(user.equals("George")) {
				
				status=true;
				break;
			}
		}
		
		
			Assert.assertEquals(status, true);
		
					
	}

	
}
