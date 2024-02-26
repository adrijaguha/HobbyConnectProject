package com.project.authentication.service;

import com.project.authentication.dao.request.SignInRequest;
import com.project.authentication.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signIn(SignInRequest request);
}
