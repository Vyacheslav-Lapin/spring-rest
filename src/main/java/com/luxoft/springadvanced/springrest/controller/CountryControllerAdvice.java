package com.luxoft.springadvanced.springrest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CountryControllerAdvice {
  @ExceptionHandler(CloneNotSupportedException.class)
  public ResponseEntity<?> countryNotSupported(CloneNotSupportedException e) {
    log.error(e.getMessage());
    return ResponseEntity.unprocessableEntity()
                         .body(e.getMessage());
  }
}
