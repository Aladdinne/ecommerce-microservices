package com.alaeddinehammouda.productservice.storage.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomException extends RuntimeException{
    /**
     * Error code
     **/
    private final int code;
    /**
     * Custom error message
     **/
    private final String message;
    /**
     * Custom error status , Bad request (400) by default
     **/
    private final HttpStatus status;
    /**
     * Errors rendered by field validation
     */

    private List<FieldErrorVM> fieldErrors = new ArrayList<>(); // NOSONAR
}
