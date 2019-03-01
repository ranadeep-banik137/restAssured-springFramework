package com.epam.api.automation.configurations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.utils.ConfigUtil;

@Component
public class Headers {
	
	private Map<String, Object> headerMapper;
	
	@Autowired
	Headers(ConfigUtil configUtil) {
		setHeaderMapper(configUtil.fetchMappedData("headers"));
	}
	
	public void setHeaderMapper(Map<String, Object> headerMapper) {
		this.headerMapper = headerMapper;
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
