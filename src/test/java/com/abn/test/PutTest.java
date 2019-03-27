package com.abn.test;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.abn.test.BaseConfig;
import org.junit.Assert;
import org.junit.Test;

public class PutTest extends BaseConfig {

	@Test
	public void putExample() {
		{
			// Have used sample public URL , it has to be replaced with the ABN project
			// specific URLs with the credentials (if credentials applicable)
			RestAssured.baseURI = "https://reqres.in";

			RestAssured.proxy("nl-proxy-access.net.abnamro.com", 8080);
			RestAssured.useRelaxedHTTPSValidation();
			REQUEST = RestAssured.given().contentType(ContentType.JSON);

			REQUEST.body("{\r\n" + "    \"name\": \"praveen\",\r\n" + "    \"job\": \"tester\"\r\n" + "}");
			Response response = REQUEST.put("/api/users/2");

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			System.out.println(response.asString());
			String name = response.jsonPath().get("name");
			Assert.assertEquals("check if results are matching", "praveen", name);
		}

	}

}
