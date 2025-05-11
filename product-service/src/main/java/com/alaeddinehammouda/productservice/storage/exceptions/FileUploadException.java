package com.alaeddinehammouda.productservice.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.yellowit.jobgate.core.storage.exceptions.utils.MessageSourceUtils;


public class FileUploadException extends CustomException {
    public FileUploadException() {
        super(1901, MessageSourceUtils.fetchMessage("exception.FileUpload"), HttpStatus.BAD_REQUEST);
    }
}
