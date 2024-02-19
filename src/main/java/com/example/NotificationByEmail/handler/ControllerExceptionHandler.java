package com.example.NotificationByEmail.handler;

import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.handler.exception.*;
import com.example.NotificationByEmail.service.interfaces.GroupService;
import com.example.NotificationByEmail.service.interfaces.TemplateService;
import lombok.RequiredArgsConstructor;
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

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleExceptiom(BindingResult bindingResult) {
        StringBuilder validateError = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError error : errors) {
            validateError.append(error.getDefaultMessage() + ". ");
        }
        return new ResponseEntity<>(validateError, HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UniqueMessageExistException.class)
    public ResponseEntity<?> handleExceptionUniqueMessage(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailNotFoundException.class)
    public ResponseEntity<?> handleExceptionMailNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TemplateNotFoundException.class)
    public ResponseEntity<?> handleExceptionTemplateNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<?> handleExceptionGroupNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( MailListIsEmptyException.class)
    public ResponseEntity<?> handleExceptionMailListIsEmpty() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}