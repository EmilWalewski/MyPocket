package com.mypocket.storeManagement.validators;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mypocket.errors.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Validation extends ErrorMessage {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errorsList;


    public Validation() {
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.errorsList = new ArrayList<>();
    }

    public void addMessage(String message){
        this.errorsList.add(message);
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}
