package com.abn.test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

public class BaseConfig {
	public RequestSpecification REQUEST;
	public Faker FAKER = new Faker();

	public BaseConfig() {
		try {
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

			// Rest Assured Proxy Setting
			RestAssured.baseURI = props.getProperty("api.uri");
			RestAssured.port = Integer.valueOf(props.getProperty("api.port"));
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.proxy("nl-proxy-access.net.abnamro.com", 8080);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		REQUEST = RestAssured.given().contentType(ContentType.JSON);
	}
}
