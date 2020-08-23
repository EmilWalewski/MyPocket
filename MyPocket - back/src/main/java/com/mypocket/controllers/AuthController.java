package com.mypocket.controllers;

import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtResponse;
import com.mypocket.security.userConfiguration.LoginModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unchecked")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginModel loginModel){

        return new ResponseEntity(new JwtResponse(HttpStatus.OK,
                jwtProvider.generateToken(
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(), loginModel.getPassword()))
        )), HttpStatus.OK
        );

    }
}
