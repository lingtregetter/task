package com.example.petmanagement.ErrorHandlers;

import com.example.petmanagement.domain.ErrorResponse;
import com.example.petmanagement.exceptions.CustomNullPointerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(CustomNullPointerException.class)
    private ResponseEntity<ErrorResponse> handleCustomNullPointerException(Exception e) {
        return responseEntityJson(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldKey = ((FieldError) error).getField();
            String fieldValue = error.getDefaultMessage();
            errors.put(fieldKey, fieldValue);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> responseEntityJson(HttpStatus status, Exception e) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(new Date(), status.value(), status.name(), e.getMessage()));
    }
}
