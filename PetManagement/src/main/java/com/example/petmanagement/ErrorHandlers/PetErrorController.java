package com.example.petmanagement.ErrorHandlers;

import com.example.petmanagement.domain.ErrorResponse;
import com.example.petmanagement.exceptions.CustomNullPointerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class PetErrorController {

    @ExceptionHandler(CustomNullPointerException.class)
    private ResponseEntity<ErrorResponse> handleCustomNullPointerException(Exception e) {
        return responseEntityJson(HttpStatus.NOT_FOUND, e);
    }

    private ResponseEntity<ErrorResponse> responseEntityJson(HttpStatus status, Exception e) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(new Date(), status.value(), status.name(), e.getMessage()));
    }
}
