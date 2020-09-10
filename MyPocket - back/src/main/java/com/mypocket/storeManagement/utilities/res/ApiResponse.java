package com.mypocket.storeManagement.utilities.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

<<<<<<< HEAD
public class ApiResponse<T> {

    private T content;
    private HttpStatus httpStatus;

    public ApiResponse(T content, HttpStatus httpStatus) {
        this.content = content;
        this.httpStatus = httpStatus;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
=======
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
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
