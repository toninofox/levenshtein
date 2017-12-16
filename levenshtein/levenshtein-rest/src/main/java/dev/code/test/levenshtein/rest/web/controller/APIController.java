package dev.code.test.levenshtein.rest.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.code.test.levenshtein.rest.core.ICoreAPI;
import dev.code.test.levenshtein.rest.web.dto.InputDto;


@RestController
@RequestMapping(value = "/api")
public class APIController {
		private static final Logger log = LoggerFactory.getLogger(APIController.class);

		@RequestMapping(value = "/distance/calculate", method = RequestMethod.POST)
		public ResponseEntity<?> calculate(ICoreAPI api, @RequestBody InputDto request ) 
		{	
			try {
				log.debug("Requested Input: {}", request);
				Integer output = api.calculate(request.firstWord, request.secondWord,request.caseSensitive);
				log.debug("Calculated Output: {}", output);
				return new ResponseEntity<Integer>(output, HttpStatus.OK);
			} catch (NullPointerException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

			}catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}
		
		
		
}
