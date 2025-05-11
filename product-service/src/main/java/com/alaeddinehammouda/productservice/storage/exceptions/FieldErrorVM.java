package com.alaeddinehammouda.productservice.storage.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FieldErrorVM implements Serializable {
    private final String objectName;

    private final String field;

    private final String message;
}
