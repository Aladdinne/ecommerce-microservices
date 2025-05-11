package com.alaeddinehammouda.productservice.storage.exceptions;

import com.alaeddinehammouda.productservice.storage.exceptions.utils.MessageSourceUtils;
import org.springframework.http.HttpStatus;



public class FileNotFoundException extends CustomException {
    public FileNotFoundException() {
        super(1903, MessageSourceUtils.fetchMessage("exception.FileNotFound"), HttpStatus.NOT_FOUND);
    }
}
