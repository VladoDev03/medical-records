package org.example.prescriptionservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentNotFoundException extends RuntimeException{
    private String message;
    private Integer statusCode;

    public DocumentNotFoundException(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
