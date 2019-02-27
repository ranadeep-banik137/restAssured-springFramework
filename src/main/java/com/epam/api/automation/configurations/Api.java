package com.epam.api.automation.configurations;

import org.springframework.stereotype.Component;

@Component
public class Api {
	
	private static final String API_NAME = "api";
	private String apiName;

	Api() {
		setApiName(System.getProperty(API_NAME) == null ? (System.getenv(API_NAME) == null ? "default" : System.getenv(API_NAME)) : System.getProperty(API_NAME));
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

}
