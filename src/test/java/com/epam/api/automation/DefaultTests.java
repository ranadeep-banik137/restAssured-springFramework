package com.epam.api.automation;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.api.automation.configurations.URIParameters.PathParams;
import com.epam.api.automation.configurations.URIParameters.QueryParams;
import com.epam.api.automation.context.ContextInitiator;
import com.jayway.jsonpath.DocumentContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DefaultTests {

	private RestJob restJob;
	
	@Parameters({"api"})
	@BeforeMethod
	private void initialize(String api) {
		restJob = ContextInitiator.initiateContext(api).getRestJob();
		restJob.setBasicRestAssuredConfig();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	private void testWithGetCall() {
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(restJob.getUriParameters().getPathParams().getPathParamMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			.authentication().basic("ranadeep123", "rana#123")
			.log().all()
			.when()
			.get()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	private void testByChangingParamsInRuntime() {
		PathParams pathParams= restJob.getUriParameters().getPathParams();
		pathParams.setValueOf(com.epam.api.automation.constants.PathParams.ID.getPathParam(), "RANADEEPGEN545573");
		QueryParams queryParams = restJob.getUriParameters().getQueryParams();
		queryParams.setValueOf(com.epam.api.automation.constants.QueryParams.OAUTH_TOKEN.getQueryParam(), RandomStringUtils.randomAlphanumeric(50));
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(pathParams.getPathParamMapper())
			.queryParams(queryParams.getQueryParamMapper())
			.accept(ContentType.JSON)
			.authentication().basic("ranadeep123", "rana#123")
			.log().all()
			.when()
			.get()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	private void testPostCall() {
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(restJob.getUriParameters().getPathParams().getPathParamMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			//.authentication().basic("ranadeep123", "rana#123")
			.body(restJob.getJsonParser().parseWithFileNameInClassPath("request.json").jsonString())
			.log().all()
			.when()
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	private void testByChangingJsonRequestInRuntime() {
		DocumentContext context = restJob.getJsonParser().parseWithFileNameInClassPath("request.json");
		context.set("$.name", "Ranadeep Banik");
		context.set("$.files[0].details", RandomStringUtils.randomAlphanumeric(50));
		context.set("$.files[1].details", RandomStringUtils.randomAlphanumeric(50));
		context.set("$.files[2].details", RandomStringUtils.randomAlphanumeric(50));
		Object object = context.read("$.files[0]");
		context.add("$.files", object);
		context.set("$.files[3].profileName", RandomStringUtils.randomAlphabetic(10).toUpperCase());
		context.put("$", "Contact", "+91-7378332802");
		restJob.getJsonParser().setContext(context);
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(restJob.getUriParameters().getPathParams().getPathParamMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			//.authentication().basic("ranadeep123", "rana#123")
			.body(restJob.getJsonParser().getContext().jsonString())
			.log().all()
			.when()
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	private void testByChangingBaseUri() {
		restJob.getEndpoint().setBaseUri("https://www.google.com/api");
		restJob.setBasicRestAssuredConfig();
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(restJob.getUriParameters().getPathParams().getPathParamMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			//.authentication().basic("ranadeep123", "rana#123")
			.body(restJob.getJsonParser().parseWithFileNameInClassPath("request.json").jsonString())
			.log().all()
			.when()
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	private void testByChangingBasePath() {
		restJob.getEndpoint().setBasePath("{name}/photos");
		restJob.setBasicRestAssuredConfig();
		Map<String, Object> mapper = new HashMap<>();
		mapper.put("name", "ranadeep137");
		PathParams pathParams = restJob.getUriParameters().getPathParams();
		pathParams.setPathParamMapper(mapper);
		RestAssured.given()
			.contentType(ContentType.JSON)
			.headers(restJob.getHeaders().getHeaderMapper())
			.pathParams(pathParams.getPathParamMapper())
			.queryParams(restJob.getUriParameters().getQueryParams().getQueryParamMapper())
			.accept(ContentType.JSON)
			//.authentication().basic("ranadeep123", "rana#123")
			.body(restJob.getJsonParser().parseWithFileNameInClassPath("request.json").jsonString())
			.log().all()
			.when()
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}
