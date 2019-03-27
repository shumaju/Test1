package com.abn.test;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostTests extends BaseConfig {

	@Test
	public void postExample() {
		{
			// Have used sample public URL , it has to be replaced with the ABN project
			// specific URLs with the credentials (if credentials applicable)
			RestAssured.baseURI = "https://reqres.in";

			RestAssured.proxy("nl-proxy-access.net.abnamro.com", 8080);
			RestAssured.useRelaxedHTTPSValidation();
			REQUEST = RestAssured.given().contentType(ContentType.JSON);

			REQUEST.body("{\r\n" + "    \"name\": \"eric\",\r\n" + "    \"job\": \"dev\"\r\n" + "}");
			Response response = REQUEST.get("/api/users");

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			System.out.println(response.asString());
			int total = response.jsonPath().get("total");
			Assert.assertEquals("compare actual and expected", 12, total);
		}

	}

	@Test
	public void postExample2() {
		{
			RestAssured.baseURI = "http://vm400011830.nl.eu.abnamro.com";
			RestAssured.port = 9080;

			RestAssured.proxy("nl-proxy-access.net.abnamro.com", 8080);
			RestAssured.useRelaxedHTTPSValidation();
			REQUEST = RestAssured.given().contentType(ContentType.JSON);
			java.io.File file = new java.io.File(".\\TC01_IP.json");
			REQUEST.body(file);
			Response response = REQUEST.post("/gic/genericIMSConnect/triggertransaction");
			System.out.println(response.asString());
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			System.out.println(response.asString());
			int total = response.jsonPath().get("total");
			Assert.assertEquals("compare actual and expected", 12, total);
		}

	}

}
