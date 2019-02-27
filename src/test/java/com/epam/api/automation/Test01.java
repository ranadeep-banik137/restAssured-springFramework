package com.epam.api.automation;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.api.automation.context.ContextInitiator;
import com.epam.api.automation.configurations.URIParameters.PathParams;
import com.epam.api.automation.configurations.URIParameters.QueryParams;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test01 {

	private RestJob restJob;
	
	@BeforeMethod
	private void initialize() {
		restJob = ContextInitiator.initiateContext().getRestJob();
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
		pathParams.setId("RANADEEPGEN545573");
		QueryParams queryParams = restJob.getUriParameters().getQueryParams();
		queryParams.setOauthToken(RandomStringUtils.randomAscii(50));
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
