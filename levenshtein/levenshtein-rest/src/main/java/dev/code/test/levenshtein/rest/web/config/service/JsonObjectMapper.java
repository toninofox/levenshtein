package dev.code.test.levenshtein.rest.web.config.service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.stereotype.Service;

/**
 * This is a custom JsonObjectMapper to be used in the application and will configure jackson.
 *
 */
@Service
public class JsonObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -737568620475871581L;

	public JsonObjectMapper() {
		super();
		setSerializationInclusion(Include.NON_NULL);
		disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
}
