package com.abn.test;

import io.restassured.response.Response;
import io.restassured.RestAssured;

import com.abn.test.BaseConfig;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetTests extends BaseConfig {

	private String username = "Stapless";
	private String password = "Karrim30";

	// Assertion for status code 200 on ABNAMRO internet homepage on ET1
	@org.junit.Test
	public void shouldLoadIntranet() {
		REQUEST.auth().preemptive().basic(username, password).get("https://www-et1.abnamro.nl/nl/prive/index.html")
				.then().statusCode(200);
	}

	// Assertion for status code 200 and message on ABNAMRO intranet homepage
	@org.junit.Test
	public void shouldHaveProperMessages() {
		Response response = REQUEST.auth().preemptive().basic(username, password)
				.get("https://www-et1.abnamro.nl/nl/widgetcontent/ib2/generiek/algemeen/algemeen.json");
		response.then().statusCode(200).and().body("Generic-ASLErrorMsg", equalTo(
				"De server is momenteel te druk om uw verzoek uit te voeren. Probeer het over 20 seconden opnieuw."));
		System.out.println(response.asString());
	}

}
