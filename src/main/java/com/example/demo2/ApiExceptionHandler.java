package com.example.demo2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleApiException(NotFoundException ex)
    {
        ErrorMessage message=new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(message,ex.getStatusCode());
    }
}
