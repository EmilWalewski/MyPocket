package com.mypocket.controllers;

import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtRefreshToken;
import com.mypocket.security.jwtConfiguration.jwtUtilities.JwtResponse;
import com.mypocket.security.userConfiguration.LoginModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginModel loginModel, HttpServletResponse response){



        Cookie cookie = new Cookie("X-US-CRED", loginModel.getUsername());

        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setDomain("localhost");

        response.addCookie(cookie);

        return new ResponseEntity(new JwtResponse(HttpStatus.OK,
                jwtProvider.generateToken(
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(), loginModel.getPassword()))
        )), HttpStatus.OK
        );
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unchecked")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody JwtRefreshToken refreshToken){

        try {
            return new ResponseEntity(new JwtResponse(HttpStatus.OK,
                    jwtProvider.generateToken(
                            jwtProvider.getAuthenticationByRefreshToken(UUID.fromString(refreshToken.getRefreshToken()))
                    )), HttpStatus.OK
            );
        }catch (NullPointerException e){
            return ResponseEntity.status(401).build();
        }
    }
}
