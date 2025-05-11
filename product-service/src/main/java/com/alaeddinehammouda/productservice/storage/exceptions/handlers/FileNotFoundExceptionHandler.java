package com.alaeddinehammouda.productservice.storage.exceptions.handlers;

import com.alaeddinehammouda.productservice.storage.exceptions.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FileNotFoundExceptionHandler {
//    @ExceptionHandler(value = {FileNotFoundException.class,java.io.FileNotFoundException.class})
//    public ResponseEntity<Object> handleFileNotFoundException(Exception e) {
//        HttpStatus notFound = HttpStatus.NOT_FOUND;
////        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), notFound);
////        return new ResponseEntity<>(exceptionResponse, notFound);
//    }
}
