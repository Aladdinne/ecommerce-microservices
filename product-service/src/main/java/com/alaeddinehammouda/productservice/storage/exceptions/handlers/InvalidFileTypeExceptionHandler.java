package com.alaeddinehammouda.productservice.storage.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import tn.yellowit.jobgate.core.storage.exceptions.InvalidFileTypeException;
import tn.yellowit.jobgate.domain.exceptions.handlers.ExceptionResponse;

@Slf4j
@ControllerAdvice
public class InvalidFileTypeExceptionHandler {

    @ExceptionHandler(value = {InvalidFileTypeException.class, MultipartException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidFileTypeException(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(exceptionResponse, badRequest);
    }
}
