package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.dao.PassengerRepository;
import com.luxoft.springadvanced.springrest.exception.PassengerNotFoundException;
import com.luxoft.springadvanced.springrest.model.Country;
import com.luxoft.springadvanced.springrest.model.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PassengerControllerImpl implements PassengerController {

  PassengerRepository repository;
  Map<String, Country> countriesMap;

  @Override
  public List<Passenger> findAll() {
    return repository.findAll();
  }

  @Override
  public Passenger createPassenger(Passenger passenger) {
    return repository.save(passenger);
  }

  @Override
  public Passenger findPassenger(Long id) {
    return repository.findById(id)
                     .orElseThrow(() -> new PassengerNotFoundException(id));
  }

  @Override
  public Passenger patchPassenger(Map<String, String> updates, Long id) {
    return repository.findById(id)
                     .map(passenger -> {

                       Optional.ofNullable(updates.get("name"))
                               .ifPresent(passenger::setName);

                       Optional.ofNullable(updates.get("country"))
                               .map(countriesMap::get)
                               .ifPresent(passenger::setCountry);

                       Optional.ofNullable(updates.get("isRegistered"))
                               .map(isRegistered -> isRegistered.equalsIgnoreCase("true"))
                               .ifPresent(passenger::setRegistered);

                       return repository.save(passenger);
                     })
                     .orElseThrow(() -> new PassengerNotFoundException(id));
  }
}
