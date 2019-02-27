package com.epam.api.automation.constants;

public enum HeaderKey {
	
	RAPID_API_KEY("rapidApiKey"), CONTENT_TYPE("contentType"), SAML_TOKEN("samlToken");
	
	private String key;
	

	HeaderKey(final String key) {
		this.key = key;
	}
	
	
	@Override
	public String toString() {
		return key;
	}
}