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

import io.restassured.response.Response;

@RunWith(DataProviderRunner.class)
public class PostTest extends BaseConfig {

	int statusCode;

	@Before
	public void beforeAPIGetCall() {
		Response response = REQUEST.get("https://jsonplaceholder.typicode.com/posts/1");
		this.statusCode = response.getStatusCode();
	}

	@DataProvider
	public static Object[][] testDataPost() {
		return new Object[][] { { ".//src//test//resources//TC02_POST.json", "/posts", 200 },
				{ ".//src//test//resources//TC01_POST.json", "/posts", 201 } };
	}

	@Test
	@UseDataProvider("testDataPost")
	public void validatePostResponse(String body, String uri, int responseCode) {
		{
			if (statusCode == 200) {
				// Reading the json body to post to the different URIs
				java.io.File file = new java.io.File(body);
				REQUEST.body(file);
				Response response = REQUEST.post(uri);

				/*
				 * assert the response code if it is corresponding to the given uri and posted
				 * json body/message
				 */
				Assert.assertEquals(response.getStatusCode(), responseCode);
				HashMap<String, Object> jsonResponse = response.jsonPath().getJsonObject("$");
				for (String key : jsonResponse.keySet()) {
					Object value = jsonResponse.get(key);
					System.out.println("json response attribute = " + key + ", and it's value = " + value.toString());
					Assert.assertNotNull(value);
					// Assert.assertEquals(101, value);
				}
				System.out.println(response.asString());
			} else {
				Assert.assertTrue("the post can not be performed since the pre requisite get got failed", false);
			}

		}

	}

	@After
	public void afterAPIPostCall() {
	}

}
