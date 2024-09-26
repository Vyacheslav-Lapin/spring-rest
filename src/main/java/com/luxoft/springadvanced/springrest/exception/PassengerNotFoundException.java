package com.luxoft.springadvanced.springrest.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

@StandardException
public class PassengerNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 36386186441847579L;

  public PassengerNotFoundException(Long id) {
        super("Passenger id not found : " + id);
    }
}
