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
		
		private String id;
		Map<String, Object> pathParamMapper;
		
		PathParams() {
			setPathParamMapper(configUtil.fetchMappedData("params.pathParams"));
			setId(String.valueOf(getPathParamMapper().get(com.epam.api.automation.constants.PathParams.ID.getPathParam())));
		}
		
		public void setPathParamMapper(Map<String, Object> pathParamMapper) {
			this.pathParamMapper = pathParamMapper;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
			this.pathParamMapper.put(com.epam.api.automation.constants.PathParams.ID.getPathParam(), id);
		}
		
		public Map<String, Object> getPathParamMapper() {
			return this.pathParamMapper;
		}
	}
	
	@Component
	public class QueryParams {
		
		private String oauthToken;
		Map<String, Object> queryParamMapper;
		
		QueryParams() {
			setQueryParamMapper(configUtil.fetchMappedData("params.queryParams"));
			setOauthToken(String.valueOf(getQueryParamMapper().get(com.epam.api.automation.constants.QueryParams.OAUTH_TOKEN.getQueryParam())));
		}

		public String getOauthToken() {
			return oauthToken;
		}

		public void setOauthToken(String oauthToken) {
			this.oauthToken = oauthToken;
			this.queryParamMapper.put(com.epam.api.automation.constants.QueryParams.OAUTH_TOKEN.getQueryParam(), oauthToken);
		}
		
		public void setQueryParamMapper(Map<String, Object> queryParamMapper) {
			this.queryParamMapper = queryParamMapper;
		}
		
		public Map<String, Object> getQueryParamMapper() {
			return queryParamMapper;
		}
	}

}
