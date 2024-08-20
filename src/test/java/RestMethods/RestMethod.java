package RestMethods;
import java.util.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class RestMethod{

int id;
String contenttype = "application/json";
String url = "https://reqres.in/";
	
	@Test(priority=1)
	public void CreateUser() {
		
		HashMap hm = new HashMap();
		hm.put("name", "Bhaumik");
		hm.put("job", "TeamLeader");
		
		
		id=given()
			.contentType(contenttype)
			.body(hm)
		
		
		.when()
			.post(url+"api/users")
			.jsonPath().getInt("id");
		
		System.out.println(id);
		/*
		.then()
			.statusCode(201)
			.log().all();
		*/
		
	}
	
	@Test(priority=2, dependsOnMethods = "CreateUser")
	public void UpdateUser() {
		
		HashMap updatedatabody = new HashMap();
		updatedatabody.put("name", "Kartik");
		updatedatabody.put("job", "Intern");
		
		given()
			.contentType(contenttype)
			.body(updatedatabody)
			
		.when()
			.put(url+"api/users/"+id)
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	
	@Test(priority=3)
	public void Userlist() {
		
		given()
			.contentType(contenttype)
		
		.when()
			.get(url+"api/users/2")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=4)
	public void DeleteUser() {
		given()
			.contentType(contenttype)
			
		.when()
			.delete(url+"api/users/2")
		
		.then()
			.statusCode(204)
			.log().all();
	}
}
