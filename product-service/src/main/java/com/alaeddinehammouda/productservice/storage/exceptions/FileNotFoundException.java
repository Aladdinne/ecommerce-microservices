package com.alaeddinehammouda.productservice.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.yellowit.jobgate.core.storage.exceptions.utils.MessageSourceUtils;


public class FileNotFoundException extends CustomException {
    public FileNotFoundException() {
        super(1903, MessageSourceUtils.fetchMessage("exception.FileNotFound"), HttpStatus.NOT_FOUND);
    }
}
