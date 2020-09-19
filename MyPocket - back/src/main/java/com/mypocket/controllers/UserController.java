package com.mypocket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProvider;
import com.mypocket.storeManagement.entities.User;
import com.mypocket.storeManagement.storeUtilities.MySqlStoreUtilities;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private JwtProvider provider;

    @Autowired
    public UserController(JwtProvider provider) {
        this.provider = provider;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity extractUserFromToken(@RequestBody String token){

        try{

            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(provider.getUsername(token)));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
