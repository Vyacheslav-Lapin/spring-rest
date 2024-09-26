package com.luxoft.springadvanced.springrest.dto;

import com.luxoft.springadvanced.springrest.model.Passenger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true,
          includeFieldNames = false)
public class Flight {

  @Getter
  @ToString.Include
  String flightNumber;

  int seats;

  Set<Passenger> passengers = new HashSet<>();

  public Set<Passenger> getPassengers() {
    return Collections.unmodifiableSet(passengers);
  }

  public void addPassenger(Passenger passenger) {
    if (passengers.size() >= seats)
      throw new RuntimeException("Cannot add more passengers than the capacity of the flight!");
    passengers.add(passenger);
  }

  public boolean removePassenger(Passenger passenger) {
    return passengers.remove(passenger);
  }
}
