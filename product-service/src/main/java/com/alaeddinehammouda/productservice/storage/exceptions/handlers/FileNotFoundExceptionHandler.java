package com.alaeddinehammouda.productservice.storage.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tn.yellowit.jobgate.core.storage.exceptions.FileNotFoundException;
import tn.yellowit.jobgate.domain.exceptions.handlers.ExceptionResponse;

@Slf4j
@ControllerAdvice
public class FileNotFoundExceptionHandler {
    @ExceptionHandler(value = {FileNotFoundException.class,java.io.FileNotFoundException.class})
    public ResponseEntity<Object> handleFileNotFoundException(Exception e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), notFound);
        return new ResponseEntity<>(exceptionResponse, notFound);
    }
}
