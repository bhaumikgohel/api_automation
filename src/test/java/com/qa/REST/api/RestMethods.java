package com.qa.REST.api;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;



public class RestMethods {

	String url = "https://reqres.in/";
	String ContentType = "application/json";
	
	int id;
	
	@Test(priority=1)
	public void Get_users() {
		
		given().
		
		when().
			get(url+"api/users?page=2")
		.then()
		
		.assertThat()
			.statusCode(200) // compare the status code 
			.body("page", equalTo(2)) // Compare how many pages are available in response
			.log().all(); // Print response in console
			
	}
	
	@Test(priority=2)
	public void Create_User() {
		
		HashMap <String,String> data = new HashMap<String,String>();
		
		data.put("name", "Bhaumik");
		data.put("job", "QA Engineer");
		
		//it generate cretea resonse and get id from it,
		// id store in global veriable for future use
		
		id=given()// It return the id from json path by using GetInt("id") Method
			.contentType(ContentType)
			.body(data)
		.when()
			.post(url+"api/users") // it create new user
			.jsonPath().getInt("id"); // it get id from json and return to global veriable
		/*.then()
			.statusCode(201)
			.log().all();
		*/
		
		
	}
	
	@Test(priority=3,dependsOnMethods = {"Create_User"})
	public void User_update() {
		
		HashMap updatedata = new HashMap();
		
		updatedata.put("name", "Ajay");
		updatedata.put("job", "Admin");
		
		given()
			.contentType(ContentType)
			.body(updatedata)
		.when()
			.put(url + "api/users/" + id)
		.then()
			.statusCode(200)
			.log().all();
			
		
	}
	
	@Test(priority=4,dependsOnMethods = "User_update")
	public void Delete_User() {
		when()
			.delete(url+"api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
		
	}
}
