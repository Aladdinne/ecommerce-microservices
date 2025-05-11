package com.alaeddinehammouda.productservice.storage.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author lotfi
 * Created on 04/08/2023.
 */
public class UserNotFoundException extends CustomException{
    public UserNotFoundException() {
        super(600, "User Not Found", HttpStatus.NOT_FOUND);
    }
}
