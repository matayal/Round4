package com.oracle.interview.round4.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.interview.round4.dao.beans.UserAuthentication;
import com.oracle.interview.round4.dao.repo.AuthenticationRepository;
import com.oracle.interview.round4.request.AuthenticationRequest;
import com.oracle.interview.round4.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationRepository authRepo;
	Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Override
	public String addUser(AuthenticationRequest user) {
		String status = "";
		boolean flag = false;

		// check for existing
		List<UserAuthentication> existingData = authRepo.findAll();
		for (UserAuthentication data : existingData) {

			if (user.getUsername().equalsIgnoreCase(data.getUsername())) {
				flag = true;
			} else {
				flag = false;
			}
		}
		if (!flag) {
			try {
				String emailRegx = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

				UserAuthentication newUser = new UserAuthentication();

				Pattern pat = Pattern.compile(emailRegx);
				if (pat.matcher(user.getEmail()).matches() && user.getPassword().contentEquals(user.getConfirmPassword())) {
					
					newUser.setUsername(user.getUsername());
					String encodedPassword = Base64.getEncoder().encodeToString(user.getConfirmPassword().getBytes());
					//byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
					//String decodedPassword = new String(decodedBytes);
					newUser.setPassword(encodedPassword);
					
					newUser.setEmail(user.getEmail());
					newUser.setRole(user.getRole());
					newUser.setCity(user.getCity());
					newUser.setCountry(user.getCountry());
					newUser.setZipcode(user.getZipCode());
					
					authRepo.saveAndFlush(newUser);
					status = "Success";
				}				

			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			
		} else {
			status = "Failed";
		}
		return status;
	}

	@Override
	public String loginUser(String username, String password) {

		String status = "";
		try {
			String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
			UserAuthentication tblData = authRepo.findByUsernameIgnoreCaseAndPassword(username, encodedPassword);
			if (tblData != null) {
				status = "Success";
			} else {
				status = "Failed";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

}
