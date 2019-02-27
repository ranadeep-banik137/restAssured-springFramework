package com.epam.api.automation.constants;

public enum QueryParams {
	
	OAUTH_TOKEN("oauthToken");
	
	private String queryParam;
	
	QueryParams(final String queryParam) {
		this.queryParam = queryParam;
	}

	public String getQueryParam() {
		return queryParam;
	}

}
