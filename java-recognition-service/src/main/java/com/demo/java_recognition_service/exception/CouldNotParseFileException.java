package com.demo.java_recognition_service.exception;

public class CouldNotParseFileException extends Exception {

    public CouldNotParseFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouldNotParseFileException(Throwable cause) {
        super(cause);
    }
}
