package com.epam.api.automation.context;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.api.automation.RestJob;


public class ContextInitiator {
	
	private static final Logger LOGGER = Logger.getLogger(ContextInitiator.class.getName());
	private ApplicationContext context;
	
	private RestJob restJob;
	
	private ContextInitiator() {
		context = new AnnotationConfigApplicationContext(ContextConfigInitializer.class);
		LOGGER.info("Context created with the config class as : \"" + ContextConfigInitializer.class.getCanonicalName() + "\"");
	}

	public static ContextInitiator initiateContext() {
		return new ContextInitiator();
	}
	
	public ApplicationContext getContext() {
		return context;
	}
	
	public RestJob getRestJob() {
		this.restJob = (RestJob) this.context.getBean("restJob");
		return this.restJob;
	}

	public void setRestJob(RestJob restJob) {
		this.restJob = restJob;
	}

}
