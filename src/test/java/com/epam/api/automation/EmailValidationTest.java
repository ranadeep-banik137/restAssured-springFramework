package com.epam.api.automation;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.api.automation.context.ContextInitiator;

import io.restassured.http.ContentType;

public class EmailValidationTest {

	
private RestJob restJob;
	
	@Parameters({"api"})
	@BeforeMethod
	private void initialize(final String api) {
		restJob = ContextInitiator.initiateContext(api).getRestJob();
		restJob.setBasicRestAssuredConfig();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	private void successGetCall() {
		given()
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
		restJob.getUriParameters().getQueryParams().setValueOf("email", restJob.getConfigUtil().getConfigByPath("testData.valid").getString("email"));
		given()
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
		restJob.getUriParameters().getQueryParams().setValueOf("email", restJob.getConfigUtil().getConfigByPath("testData.invalid").getAnyRef("email"));
		given()
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
