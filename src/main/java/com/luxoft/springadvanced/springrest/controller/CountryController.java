package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.model.Country;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CountryController {
  @GetMapping("/countries")
  List<Country> findAll();
}
