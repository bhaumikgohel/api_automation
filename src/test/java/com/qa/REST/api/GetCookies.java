package com.qa.REST.api;
import static io.restassured.RestAssured.*;

import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.http.Header;
import io.restassured.http.Headers;
public class GetCookies {
	
	//@Test
	public void Get_Single_cookie() {
		
	 Response res = given()
			
		.when()
			.get("https://www.google.com");
		
		System.out.println(res.getCookie("AEC"));

	}
	
	//@Test(priority=2)
	public void Get_Multiple_Cookies() {
		Response res = given()
					.when()
						.get("https://www.google.com");
		
		Map<String,String> data = res.getCookies();
		
		for(String Key : data.keySet()) {
			
			String cookievalue = res.getCookie(Key);
			
			System.out.println(Key + "     " + cookievalue);
			
		}
	}
	
	@Test(priority=3)
	public void get_header() {
		Response res = given()
				
				.when()
					.get("https://www.google.com");
		
		String h2 = res.getHeader("Content-Type");
		System.out.println("Header value is " + h2);
	}
	
	@Test(priority=4)
	public void get_Multiple_Headers() {
		
		Response res = given()
				
				.when()
					.get("https://www.google.com");
		
		Headers h2 = res.getHeaders();
		
		for (Header Key : h2) {
			
			System.out.println("Key : " + Key.getName());
			System.out.println("Value : "+ Key.getValue());
		}
	}
	
	
}
