package com.mypocket.controllers;

import com.mypocket.security.jwtConfiguration.jwtProvider.JwtProviderImp;
import com.mypocket.storeManagement.models.ShoppingModel;
import com.mypocket.storeManagement.storeUtilities.MySqlStoreUtilities;
import com.mypocket.storeManagement.validators.ValidationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shopping")
public class testcontroller {

    @Autowired
    MySqlStoreUtilities mySqlStoreUtilities;

    @Autowired
    JwtProviderImp provider;

    @GetMapping(value = "/init")
    public void init(){

//        mySqlStoreUtilities.createUser();

//        return mySqlStoreUtilities.getUser();

//        return provider.generateToken();
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShopping(@Valid @RequestBody ShoppingModel shoppingModel, Errors errors){


        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationBuilder.buildErrorMessage(errors));
        }

        return ResponseEntity.ok(mySqlStoreUtilities.createShopping(shoppingModel));
    }
}
