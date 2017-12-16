package dev.code.test.levenshtein.rest.web;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dev.code.test.levenshtein.rest.web.config.AppConfiguration;
import dev.code.test.levenshtein.rest.web.config.MvcConfiguration;
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
				AppConfiguration.class,
				};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { 
				MvcConfiguration.class
				};

	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	

}