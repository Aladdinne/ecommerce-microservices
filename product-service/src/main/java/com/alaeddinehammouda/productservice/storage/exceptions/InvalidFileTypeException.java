package com.alaeddinehammouda.productservice.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.yellowit.jobgate.core.storage.exceptions.utils.MessageSourceUtils;


public class InvalidFileTypeException extends CustomException {
    public InvalidFileTypeException() {
        super(1902, MessageSourceUtils.fetchMessage("exception.InvalidFileType"), HttpStatus.BAD_REQUEST);
    }
}
