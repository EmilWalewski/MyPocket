package com.mypocket.security.jwtConfiguration.jwtUtilities;

import org.springframework.http.HttpStatus;

public class JwtResponse {

    private HttpStatus status;
    private String token;

    public JwtResponse(HttpStatus status, String token) {
        this.status = status;
        this.token = token;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
