package com.epam.api.automation.configurations;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;

@Component(value = "jsonParser")
public class JSONParser {
	
	private static final Logger LOGGER = Logger.getLogger(JsonParser.class.getName()); 
	private static final String SEPARATOR = "/";
	private DocumentContext context;
	private ParseContext parseContext;
	@Autowired
	private Api api;
	
	public DocumentContext getContext() {
		return context;
	}

	public void setContext(DocumentContext context) {
		this.context = context;
	}

	public JSONParser() {
		this.parseContext = JsonPath.using(Configuration.defaultConfiguration());
	}
	
	public DocumentContext parseWithFile(final String filePath) {
		try {
			this.context = this.parseContext.parse(new File(filePath));
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
		return this.context;
	}
	
	public DocumentContext parseWithFileNameInClassPath(final String fileName) {
		this.context = this.parseContext.parse(JSONParser.class.getResourceAsStream(SEPARATOR + this.api.getApiName() + SEPARATOR + "payload" + SEPARATOR +fileName));
		return this.context;
	}
	
	public DocumentContext parseWithString(final String json) {
		this.context = this.parseContext.parse(json);
		return this.context;
	}

}
