package com.demo.java_recognition_service.advice;

import com.demo.java_recognition_service.exception.CouldNotParseFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CouldNotParseFileException.class)
    public ResponseEntity handleIOException(CouldNotParseFileException ex) {
        // log exception
        log.info("Could not encode/decode file", ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Could not encode/decode file");
    }
}
