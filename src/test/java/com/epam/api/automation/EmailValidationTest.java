package com.epam.api.automation;

import org.apache.http.HttpStatus;
import org.hamcrest.core.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.api.automation.context.ContextInitiator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class EmailValidationTest {

	
private RestJob restJob;
	
	@BeforeMethod
	private void initialize() {
		restJob = ContextInitiator.initiateContext().getRestJob();
		restJob.setBasicRestAssuredConfig();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	private void successGetCall() {
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			.authentication().basic("ranadeep137", "rana#123")
			.log().all()
			.when()
			.get()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.body(restJob.validateSchemaFromClassPath("responseSchema.json"))
			.body("domain", IsEqual.equalToObject("yahoo.com"))
			.body("valid", Is.is(true));
	}
	
	@Test
	private void successGetCallWithOtherValidEmail() {
		restJob.getUriParameters().getQueryParams().setValueOf("email", restJob.getConfigUtil().getConfigByPath("test.valid").getString("email"));
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			.log().all()
			.when()
			.get()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.body(restJob.validateSchemaFromClassPath("responseSchema.json"))
			.body("domain", IsEqual.equalToObject("gmail.com"))
			.body("valid", Is.is(true));
	}
	
	@Test
	private void testGetCallWithInvalidEmail() {
		restJob.getUriParameters().getQueryParams().setValueOf("email", restJob.getConfigUtil().getConfigByPath("test.invalid").getAnyRef("email"));
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			.log().all()
			.when()
			.get()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);
	}
}
