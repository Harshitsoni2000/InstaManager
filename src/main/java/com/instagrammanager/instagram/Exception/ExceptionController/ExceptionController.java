package com.instagrammanager.instagram.Exception.ExceptionController;

import com.instagrammanager.instagram.Exception.Exceptions.RecordNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> recordNotFound(RecordNotFoundException recordNotFoundException) {
        return new ResponseEntity<>("Record Not Found!", HttpStatus.BAD_REQUEST);
    }
}
