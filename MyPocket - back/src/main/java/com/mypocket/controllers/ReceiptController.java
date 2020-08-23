package com.mypocket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.storeManagement.entities.Photo;
import com.mypocket.storeManagement.entities.Receipt;
import com.mypocket.storeManagement.storeUtilities.PhotoStorage;
import com.mypocket.storeManagement.utilities.res.ApiResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/receipt")
public class ReceiptController {

    @Autowired
    PhotoStorage photoStorage;

    @RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.PATCH }, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadReceipt(@RequestParam(value = "file", required = false) MultipartFile photo, @RequestParam("receipt_data") String receipt) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        Receipt receiptObject = objectMapper.readValue(receipt, Receipt.class);


        if (photo != null)
        receiptObject.setReceipt_photo(new Photo(photo, photo.getBytes()));

        /**
         *
         *
         *  save receipt to database and return return it's id
         *
         * */
//        photoStorage.saveReceipt(receiptObject);

//        UriComponents uriBuilder = MvcUriComponentsBuilder.fromMethodCall(
//                MvcUriComponentsBuilder.on(ReceiptController.class).getReceipt(photoStorage.saveReceipt(receiptObject))
//        ).build();

        return ResponseEntity.ok(new ApiResponse(photoStorage.saveReceipt(receiptObject), HttpStatus.OK));

    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReceipts(){

        return ResponseEntity.ok(photoStorage.getReceipts());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReceipt(@PathVariable("id") int id){

        String receipt = photoStorage.getReceipt(id);

        return ResponseEntity.ok(receipt);
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



