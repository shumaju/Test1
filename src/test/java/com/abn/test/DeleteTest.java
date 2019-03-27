package com.abn.test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.abn.test.BaseConfig;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class DeleteTest extends BaseConfig {

	@Test
	public void deleteExample() {
		{
			// Have used sample public URL , it has to be replaced with the ABN project
			// specific URLs with the credentials (if credentials applicable)
			RestAssured.baseURI = "https://reqres.in";

			RestAssured.proxy("nl-proxy-access.net.abnamro.com", 8080);
			RestAssured.useRelaxedHTTPSValidation();
			REQUEST = RestAssured.given().contentType(ContentType.JSON);

			Response response = REQUEST.delete("/api/users/2");

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 204);
			System.out.println(response.asString());

		}

	}

}
