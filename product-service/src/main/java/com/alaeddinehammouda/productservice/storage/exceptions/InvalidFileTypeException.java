package com.alaeddinehammouda.productservice.storage.exceptions;

import com.alaeddinehammouda.productservice.storage.exceptions.utils.MessageSourceUtils;
import org.springframework.http.HttpStatus;

public class InvalidFileTypeException extends CustomException {
    public InvalidFileTypeException() {
        super(1902, MessageSourceUtils.fetchMessage("exception.InvalidFileType"), HttpStatus.BAD_REQUEST);
    }
}
