package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.model.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

public interface PassengerController {
  @GetMapping("/passengers")
  List<Passenger> findAll();

  @PostMapping("/passengers")
  @ResponseStatus(HttpStatus.CREATED)
  Passenger createPassenger(@RequestBody Passenger passenger);

  @GetMapping("/passengers/{id}")
  Passenger findPassenger(@PathVariable Long id);

  @PatchMapping("/passengers/{id}")
  Passenger patchPassenger(@RequestBody Map<String, String> updates,
                           @PathVariable Long id);
}
