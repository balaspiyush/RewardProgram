package com.reward.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for now added only 2 exceptions
 * but can we enhanced to add more as needed
 * @author Piyush Balas
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRunTimeException(RuntimeException re) {
        return new ResponseEntity<>("Runtime exception while processing message:"+re.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Unknown exception thrown:"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
