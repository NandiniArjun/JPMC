package com.example.morgan.controller;

import com.example.morgan.exception.StockNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class StockExceptionController
{
    @ExceptionHandler(value = StockNotfoundException.class)
    public ResponseEntity<Object> exception(StockNotfoundException exception)
    {
        return new ResponseEntity<>("Stock not found", HttpStatus.NOT_FOUND);
    }
}
