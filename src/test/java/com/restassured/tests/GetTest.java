package com.restassured.tests;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.restassured.config.BaseConfig;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@RunWith(DataProviderRunner.class)
public class GetTest extends BaseConfig {

	@Before
	public void beforeAPIGetCall() {

	}

	@DataProvider
	public static Object[][] testData() {
		return new Object[][] { { "/posts/1", 200 }, { "/posts/2", 200 } };
	}

	@Test
	@UseDataProvider("testData")
	public void validateGetResponse(String uri, int statusCode) {
		/*
		 * the below will trigger the HTTP get call with the uri value read from the
		 * test data and assert it - repeated number of data objects*/
		 RestAssuredConfig rc = RestAssured.config().sslConfig(
					new SSLConfig().allowAllHostnames());
		ExtractableResponse<Response> response = REQUEST.given().config(rc).get(uri).then().extract();

		Assert.assertEquals(200, response.statusCode());
		HashMap<String, Object> jsonResponse = response.jsonPath().getJsonObject("$");
		for (String key : jsonResponse.keySet()) {
			Object value = jsonResponse.get(key);
			System.out.println("json response attribute = " + key + ", and it's value = " + value.toString());
			Assert.assertNotEquals(value.toString(), "");
			// or
			Assert.assertNotNull(value);
		}

		// this is to show the response returned from the above GET call, in the console
		System.out.println(response.asString());
	}

	@After
	public void afterAPIGetCall() {
	}

}
