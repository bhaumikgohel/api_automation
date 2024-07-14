package com.qa.REST.api;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.testng.annotations.Test;

public class BodyDataCreation {
	
	//Body data creation for HashMap
	//@Test(priority=1)
	public void DataCreationUsing_HashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Andraw");
		data.put("job", "Team Leader");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Andraw"))
			.body("job", equalTo("Team Leader"))
			.log().all();
		
	}
	//Body data creation for Orgjson
	//@Test(priority=1)
	public void BodyDataCreationUsing_OrgJson() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Aryan");
		data.put("job", "Steel Work");
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Aryan"))
			.body("job",equalTo("Steel Work"))
			.log().all();
		
	}
	//Create body data using POJO
	//@Test(priority=1)
	public void BodyDataCreateUsing_PojoClass() {
		
		
		Pojo_Create data = new Pojo_Create();
		
		data.setName("Harish");
		data.setJob("Welder");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo(data.getName()))
			.body("job", equalTo(data.getJob()))
			.log().all();
		
		
	}
	
	@Test(priority=1)
	public void BodyDataCreateusingExternalFile() throws FileNotFoundException {
		
		File f = new File(".\\PostData.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
	
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("morpheus"))
			.body("job", equalTo("leader"))
			.log().all();
					
		
	}

}
