package com.epam.api.automation.configurations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.api.automation.utils.ConfigUtil;

@Component
public class URIParameters {
	
	@Autowired
	private ConfigUtil configUtil;
	@Autowired
	private PathParams pathParams;
	@Autowired
	private QueryParams queryParams;
	
	public PathParams getPathParams() {
		return pathParams;
	}

	public void setPathParams(PathParams pathParams) {
		this.pathParams = pathParams;
	}

	public QueryParams getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(QueryParams queryParams) {
		this.queryParams = queryParams;
	}

	@Component
	public class PathParams {
		
		Map<String, Object> pathParamMapper;
		
		PathParams() {
			setPathParamMapper(configUtil.getConfig().hasPath("params.pathParams") ? configUtil.fetchMappedData("params.pathParams") : null);
		}
		
		public void setPathParamMapper(Map<String, Object> pathParamMapper) {
			this.pathParamMapper = pathParamMapper;
		}
		
		public Map<String, Object> getPathParamMapper() {
			return this.pathParamMapper;
		}
		
		public Object getValueOf(final String pathParam) {
			return this.pathParamMapper.get(pathParam);
		}
		
		public Object setValueOf(final String paramKey, final Object value) {
			return this.pathParamMapper.put(paramKey, value);
		}
	}
	
	@Component
	public class QueryParams {
		
		Map<String, Object> queryParamMapper;
		
		QueryParams() {
			setQueryParamMapper(configUtil.getConfig().hasPath("params.queryParams") ? configUtil.fetchMappedData("params.queryParams") : null);
		}
		
		public void setQueryParamMapper(Map<String, Object> queryParamMapper) {
			this.queryParamMapper = queryParamMapper;
		}
		
		public Map<String, Object> getQueryParamMapper() {
			return queryParamMapper;
		}
		
		public Object getValueOf(final String queryParam) {
			return this.queryParamMapper.get(queryParam);
		}
		
		public Object setValueOf(final String paramKey, final Object value) {
			return this.queryParamMapper.put(paramKey, value);
		}
	}
	
}
