package com.epam.api.automation;

import com.epam.api.automation.context.ContextInitiator;
import com.typesafe.config.Config;


public class AppTest {
	
	public static void main(String[] args) {
		RestJob rest = ContextInitiator.initiateContext().getRestJob();
		System.out.println(rest.getApi().getApiName());
		System.out.println(rest.getConfigUtil().fetchMappedData("params.pathParams"));
		
		String request = rest.getJsonParser().parseWithFileNameInClassPath("request.json").jsonString();
		System.out.println(request);
		System.out.println(rest.getJsonParser().parseWithString(request).read("$.files[0].profileName").toString());
		Config invalid = rest.getConfigUtil().getConfigByPath("test.invalid");
		System.out.println(rest.getConfigUtil().getConfig("test").getConfig("invalid").getString("name"));
		System.out.println(invalid.getString("id"));
		System.out.println();
		/*System.out.println(rest.getEndpoint().getBasePath());
		System.out.println(rest.getEndpoint().getBaseUri());*/
	}
   
}
