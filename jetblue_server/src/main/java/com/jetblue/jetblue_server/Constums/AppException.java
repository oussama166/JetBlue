package com.jetblue.jetblue_server.Constums;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private final HttpStatus httpStatus;
    public AppException(String Message , HttpStatus status) {
        super(Message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

}
