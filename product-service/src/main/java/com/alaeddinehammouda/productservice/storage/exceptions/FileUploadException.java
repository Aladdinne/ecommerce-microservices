package com.alaeddinehammouda.productservice.storage.exceptions;

import com.alaeddinehammouda.productservice.storage.exceptions.utils.MessageSourceUtils;
import org.springframework.http.HttpStatus;

public class FileUploadException extends CustomException {
    public FileUploadException() {
        super(1901, MessageSourceUtils.fetchMessage("exception.FileUpload"), HttpStatus.BAD_REQUEST);
    }
}
