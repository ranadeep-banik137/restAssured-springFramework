package com.epam.api.automation.configurations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.constants.HeaderKey;
import com.epam.api.automation.utils.ConfigUtil;

@Component
public class Headers {
	
	private Object rapidApiKey;
	private Object contentType;
	private Object samlToken;
	private Map<String, Object> headerMapper;
	
	@Autowired
	Headers(ConfigUtil configUtil) {
		setHeaderMapper(configUtil.fetchMappedData("headers"));
		setContentType(String.valueOf(getValueOf(HeaderKey.CONTENT_TYPE.toString())));
		setRapidApiKey(String.valueOf(getValueOf(HeaderKey.RAPID_API_KEY.toString())));
		setSamlToken(String.valueOf(getValueOf(HeaderKey.SAML_TOKEN.toString())));
	}
	
	public void setHeaderMapper(Map<String, Object> headerMapper) {
		this.headerMapper = headerMapper;
	}
	
	public Object getRapidApiKey() {
		return rapidApiKey;
	}
	public void setRapidApiKey(Object rapidApiKey) {
		this.rapidApiKey = rapidApiKey;
		this.setValueOf(HeaderKey.RAPID_API_KEY.toString(), rapidApiKey);
	}
	public Object getContentType() {
		return contentType;
	}
	public void setContentType(Object contentType) {
		this.contentType = contentType;
		this.setValueOf(HeaderKey.CONTENT_TYPE.toString(), contentType);
	}
	
	public Object getSamlToken() {
		return samlToken;
	}
	public void setSamlToken(Object samlToken) {
		this.samlToken = samlToken;
		this.setValueOf(HeaderKey.SAML_TOKEN.toString(), samlToken);
	}
	
	public Object getValueOf(final String header) {
		return this.headerMapper.get(header);
	}
	
	public Object setValueOf(final String header, final Object value) {
		return this.headerMapper.put(header, value);
	}
	
	public Map<String, Object> getHeaderMapper() {
		return this.headerMapper;
	}

}
