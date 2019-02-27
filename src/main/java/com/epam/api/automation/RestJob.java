package com.epam.api.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.configurations.Api;
import com.epam.api.automation.configurations.Endpoint;
import com.epam.api.automation.configurations.Headers;
import com.epam.api.automation.configurations.JSONParser;
import com.epam.api.automation.configurations.URIParameters;
import com.epam.api.automation.utils.ConfigUtil;

import io.restassured.RestAssured;

@Component
public class RestJob {
	
	@Autowired
	private Api api;
	@Autowired
	private Endpoint endpoint;
	@Autowired
	private Headers headers;
	@Autowired
	private URIParameters uriParameters;
	@Autowired
	private ConfigUtil configUtil;
	@Autowired
	private JSONParser jsonParser;
	
	
	public JSONParser getJsonParser() {
		return jsonParser;
	}
	public void setJsonParser(JSONParser jsonParser) {
		this.jsonParser = jsonParser;
	}
	
	public ConfigUtil getConfigUtil() {
		return configUtil;
	}
	public void setConfigUtil(ConfigUtil configUtil) {
		this.configUtil = configUtil;
	}
	
	public Api getApi() {
		return api;
	}
	public void setApi(Api api) {
		this.api = api;
	}
	public Endpoint getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}
	public Headers getHeaders() {
		return headers;
	}
	public void setHeaders(Headers headers) {
		this.headers = headers;
	}
	public URIParameters getUriParameters() {
		return uriParameters;
	}
	public void setUriParameters(URIParameters uriParameters) {
		this.uriParameters = uriParameters;
	}
	
	public void setBasicRestAssuredConfig() {
		RestAssured.basePath = getEndpoint().getBasePath();
		RestAssured.baseURI = getEndpoint().getBaseUri();
		RestAssured.useRelaxedHTTPSValidation();
	}

}
