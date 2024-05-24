package com.jetblue.jetblue_server.Configuration;

import com.jetblue.jetblue_server.Constums.AppException;
import com.jetblue.jetblue_server.DTOS.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class RestExceptionHandler {
    @ExceptionHandler(value = { AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDto(ex.getMessage()));
    }
}
