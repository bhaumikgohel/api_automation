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
		
		for(int i =0; i<jo.getJSONArray("data").length();i++) {
			String user = jo.getJSONArray("data").getJSONObject(i).toString();
			System.out.println(user);
			
			if(user.equals(user)) {
				
				status=true;
				break;
			}
		}
					
	}

	
}
