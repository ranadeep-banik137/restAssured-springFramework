package com.epam.api.automation.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.configurations.Api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Component
public class ConfigUtil {

	private static final String SEPARATOR = "/";
	private Config config;
	private Config tempConfig;
	
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	public void setConfig(final String path) {
		setConfig(ConfigFactory.load(path));
	}

	@Autowired
	public ConfigUtil(Api api) {
		setConfig(ConfigFactory.load(api.getApiName() + SEPARATOR + "reference.conf"));
	}
	
	public Config loadConfig(final String path) {
		return ConfigFactory.load(path);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> fetchMappedData(final String... sequentialPaths) {
		Iterator<String> iteratorObject = Arrays.asList(sequentialPaths).iterator();
		Object resultantObject = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		this.tempConfig = this.config;
		while (iteratorObject.hasNext()) {
			String currentPath = iteratorObject.next();
			if (!iteratorObject.hasNext()) {
				resultantObject = this.tempConfig.getAnyRef(currentPath);
			} else {
				this.tempConfig = this.tempConfig.getConfig(currentPath);
			}
		}
		if (resultantObject != null) {
			map = mapper.convertValue(resultantObject, Map.class);
		}
		return map;
 	}
	
	public Map<String, Object> fetchMappedData(final String path) {
		return fetchMappedData(path.split("\\."));
	}
	
	public Config getConfig(final String path) {
		return this.config.getConfig(path);
	}
	
	public Config getConfigByPath(final String path) {
		Config temp = this.config;
		for (String p : path.split("\\.")) {
			temp = temp.getConfig(p);
		}
		return temp;
	}
	
	public Config mergeConfig(final String path) {
		return this.config.withFallback(loadConfig(path));
	}
	
	public Config mergeConfig(final Config config) {
		return this.config.withFallback(config);
	}
}
