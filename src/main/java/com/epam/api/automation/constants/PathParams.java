package com.epam.api.automation.constants;

public enum PathParams {

	ID("id");
	
	private String pathParam;
	
	PathParams(final String pathParam) {
		this.pathParam = pathParam;
	}

	public String getPathParam() {
		return pathParam;
	}
}
