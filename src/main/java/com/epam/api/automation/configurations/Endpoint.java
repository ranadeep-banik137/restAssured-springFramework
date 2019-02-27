package com.epam.api.automation.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.utils.ConfigUtil;
import com.typesafe.config.Config;

@Component
public class Endpoint {

	private static final String SEPARATOR = "/";
	private static final String HOST_URL = "host-url";
	private static final String BASE_PATH = "basePath";
	private String baseUri;
	private String basePath;
	
	@Autowired
	Endpoint(ConfigUtil configUtil) {
		Config uriConfig = configUtil.getConfigByPath("uriCreds");
		setBaseUri(uriConfig.getString(HOST_URL));
		setBasePath(uriConfig.getString(BASE_PATH));
	}
	
	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
	public String getURL() {
		return this.getBaseUri() + SEPARATOR + this.getBasePath();
	}
}
