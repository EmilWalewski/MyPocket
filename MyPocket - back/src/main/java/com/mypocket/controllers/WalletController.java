package com.mypocket.controllers;

import com.mypocket.storeManagement.entities.Wallet;
import com.mypocket.storeManagement.storeUtilities.WalletStorage;
import com.mypocket.storeManagement.utilities.res.ApiResponse;
import com.mypocket.storeManagement.validators.ValidationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletStorage walletStorage;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createWallet(@Validated @RequestBody Wallet wallet, Errors errors){


        if (errors.hasErrors()){
            System.out.println("error");
            return ResponseEntity.badRequest().body(ValidationBuilder.buildErrorMessage(errors));
        }
        wallet.setSelected(false);


        return ResponseEntity.ok(new ApiResponse<>(walletStorage.saveWallet(wallet), HttpStatus.CREATED));
    }
}
