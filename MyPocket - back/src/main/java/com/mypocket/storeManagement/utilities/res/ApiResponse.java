package com.mypocket.storeManagement.utilities.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

public class ApiResponse {

    private int id;
    private HttpStatus httpStatus;

    public ApiResponse(int id, HttpStatus httpStatus) {
        this.id = id;
        this.httpStatus = httpStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
