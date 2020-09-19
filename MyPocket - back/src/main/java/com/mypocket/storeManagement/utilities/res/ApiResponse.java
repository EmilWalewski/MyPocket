package com.mypocket.storeManagement.utilities.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

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
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
