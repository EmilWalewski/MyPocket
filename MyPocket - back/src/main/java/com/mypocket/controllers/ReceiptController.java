package com.mypocket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.security.userConfiguration.PrincipalDetailsService;
import com.mypocket.storeManagement.entities.Photo;
import com.mypocket.storeManagement.entities.Receipt;
import com.mypocket.storeManagement.storeUtilities.PhotoStorage;
import com.mypocket.storeManagement.storeUtilities.UserStore;
import com.mypocket.storeManagement.utilities.res.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {

    @Autowired
    PhotoStorage photoStorage;

    @Autowired
    UserStore userStore;

    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadReceipt(@RequestParam(value = "file", required = false) MultipartFile photo,
                                                @RequestParam(value = "receipt_data", required = false) String receipt,
                                                HttpServletRequest request) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        Receipt receiptObject = objectMapper.readValue(receipt, Receipt.class);

        receiptObject.setUser(userStore.findUserByUserName(request.getHeader("E-SEL-MAR-XX")).orElseThrow(() -> new UsernameNotFoundException("Internal Error: User not found")));


        if (photo != null)
        receiptObject.setReceipt_photo(new Photo(photo, photo.getBytes()));

        /**
         *
         *
         *  save receipt to database and return it's id
         *
         * */
//        photoStorage.saveReceipt(receiptObject);

//        UriComponents uriBuilder = MvcUriComponentsBuilder.fromMethodCall(
//                MvcUriComponentsBuilder.on(ReceiptController.class).getReceipt(photoStorage.saveReceipt(receiptObject))
//        ).build();

        return ResponseEntity.ok(new ApiResponse<>(photoStorage.saveReceipt(receiptObject), HttpStatus.OK));

    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReceipts(HttpServletRequest request, HttpServletResponse response){


        return ResponseEntity.ok(photoStorage.getReceipts(request.getHeader("E-SEL-MAR-XX")));
    }
}

/**
 *
 *
 *          to download img
 *
 *
 * */

//ResponseEntity.ok()
//        .contentType(MediaType.parseMediaType(entity.getFileType()))
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + entity.getFileName())
//        .body(new ByteArrayResource(entity.getData()));



