package com.restassured.config;

import java.io.IOException;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author
 *
 */
public class BaseConfig {

	public RequestSpecification REQUEST;
	public Faker FAKER = new Faker();

	public BaseConfig() {
		try {
			PropertiesReader pr = new PropertiesReader();
			RestAssured.baseURI = pr.getProperty("prod.uri");

			RestAssured.port = Integer.valueOf(pr.getProperty("prod.port"));
			//RestAssured.useRelaxedHTTPSValidation();
			// uncomment below if it uses proxy
			// RestAssured.proxy(pr.getProperty("prod.proxy"), 8080);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Header header = new Header("Content-type", "application/json; charset=UTF-8");
		//(OR) REQUEST = RestAssured.given().contentType(ContentType.JSON);
		REQUEST = RestAssured.given().header(header);
	}
}
