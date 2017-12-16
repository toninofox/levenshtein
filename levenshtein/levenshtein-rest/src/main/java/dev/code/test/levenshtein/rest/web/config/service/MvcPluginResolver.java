package dev.code.test.levenshtein.rest.web.config.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import dev.code.test.levenshtein.rest.core.ICoreAPI;

@Service
public class MvcPluginResolver implements HandlerMethodArgumentResolver {
	private static Logger log = LoggerFactory.getLogger(MvcPluginResolver.class);

	@Autowired PluginService service;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> parameterType = parameter.getParameterType();
		log.debug("Resolving Parameter {}", parameterType);
		return parameterType.equals(ICoreAPI.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.debug("PluginResolver asked to resolve an argument");
			return service.createPlugin();
		
	}
}