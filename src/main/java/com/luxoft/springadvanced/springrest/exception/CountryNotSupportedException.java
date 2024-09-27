package com.luxoft.springadvanced.springrest.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

@StandardException
public class CountryNotSupportedException extends RuntimeException {
  @Serial private static final long serialVersionUID = -6493443103704837064L;
}
