package com.qa.REST.api;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class QueryAndPathParameter {
	
	@Test
	void QueryandPathParameter() {
		// https://reqres.in/api/users?page=2
		
		given()
			.pathParam("path1", "users")
			.queryParam("page", "0")
			
		
		.when()
			.get("https://reqres.in/api/{path1}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
