package com.ninjarmm.rmmservicesserverapp.config;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> enumConversionFailed(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> nothingFoundOnDeletion(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
