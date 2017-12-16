package dev.code.test.levenshtein.rest.web.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import dev.code.test.levenshtein.rest.web.config.service.JsonObjectMapper;
import dev.code.test.levenshtein.rest.web.config.service.MvcPluginResolver;
import dev.code.test.levenshtein.rest.web.controller.SpringControllerPlaceholder;

@ComponentScan(basePackageClasses = {SpringControllerPlaceholder.class})
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	MvcPluginResolver mvcPluginResolver;
	
	
	private static final Logger log = LoggerFactory.getLogger(MvcConfiguration.class);
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(mvcPluginResolver);
	}
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.debug("Configuring Message Converters");
		converters.add(jacksonConverter());
	}
	
	MappingJackson2HttpMessageConverter jacksonConverter() {
		log.debug("Configuring Jackson Converters");
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(new MediaType("application", "json"));
		converter.setSupportedMediaTypes(supportedMediaTypes);
		converter.setObjectMapper(new JsonObjectMapper());
		return converter;
	}
	
}