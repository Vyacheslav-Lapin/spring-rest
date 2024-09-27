package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.exception.CountryNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CountryControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CountryNotSupportedException.class)
  public ResponseEntity<?> countryNotSupported(CountryNotSupportedException ex) {
    log.error(ex.getMessage());
    return ResponseEntity.unprocessableEntity()
                         .body(ex.getMessage());
  }
}
