package com.oracle.interview.round4.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.interview.round4.request.AuthenticationRequest;
import com.oracle.interview.round4.service.AuthenticationService;


@RequestMapping("/oracle/lab/auth")
@RestController
@CrossOrigin(maxAge = 3600)
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authService;
	Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


	@GetMapping(value = "/addUser")
	public Map<String, Object> addUser(@ModelAttribute AuthenticationRequest user) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = authService.addUser(user);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Successfully created");
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed to create");
		}
		logger.info("AddUser response:{}", response);
		return response;
	}
	
	@GetMapping(value = "/loginUser/{username}/{password}")
	public Map<String, Object> loginUser(@PathVariable String username, @PathVariable String password) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = authService.loginUser(username, password);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Successfully Login");
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed to Login");
		}
		logger.info("LoginUser response:{}", response);

		return response;
	}

}
