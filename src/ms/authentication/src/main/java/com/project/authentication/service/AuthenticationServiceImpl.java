package com.project.authentication.service;

import com.project.authentication.dao.request.SignInRequest;
import com.project.authentication.dao.response.JwtAuthenticationResponse;
import com.project.authentication.entity.User;
import com.project.authentication.repository.UserRepository;
import com.project.authentication.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtUtils.GenerateToken(user.getUsername());
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
