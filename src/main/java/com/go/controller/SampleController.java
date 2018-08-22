package com.go.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.model.PersonDTO;

@RestController
public class SampleController {
	
	private static Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping(value = "/persistPerson", method = RequestMethod.POST)
    public ResponseEntity < String > persistPerson(@RequestBody PersonDTO person) throws JsonProcessingException {

		
		// Converting Object as String
		ObjectMapper mapper = new ObjectMapper();
		
		//Request body 
		String asString  = mapper.writeValueAsString(person);
		logger.debug(asString);
		
		// Making Rest call
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity(asString, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Object> response = restTemplate.exchange("urUrlGoesHere", HttpMethod.POST,
				entity, Object.class);
		
		// The End
		return ResponseEntity.status(HttpStatus.OK).build();
    }

}
