package com.example.NotificationByEmail.handler;

import com.example.NotificationByEmail.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final String ERROR_VALIDATION_CODE = "999";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleExceptiom(BindingResult bindingResult) {
        StringBuilder validateError = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error : errors) {
            validateError.append(error.getDefaultMessage() + ". ");
        }
        return new ResponseEntity<>(new Response(ERROR_VALIDATION_CODE, validateError.toString()), HttpStatus.BAD_REQUEST
        );
    }
}