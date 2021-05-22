package com.oracle.interview.round4.service;

import com.oracle.interview.round4.request.AuthenticationRequest;

public interface AuthenticationService {

	String addUser(AuthenticationRequest user);

	String loginUser(String username, String password);


}
