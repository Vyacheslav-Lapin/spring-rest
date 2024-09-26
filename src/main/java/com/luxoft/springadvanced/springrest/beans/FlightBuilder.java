package com.luxoft.springadvanced.springrest.beans;

import com.luxoft.springadvanced.springrest.dto.Flight;
import com.luxoft.springadvanced.springrest.model.Country;
import com.luxoft.springadvanced.springrest.model.Passenger;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@ExtensionMethod(value = Objects.class,
                 suppressBaseMethods = false)
public class FlightBuilder {

  Map<String, Country> countriesMap;

  @SneakyThrows
  public FlightBuilder() {

    @Cleanup val reader = new BufferedReader(
        new FileReader(
            FlightBuilder.class.getResource("/countries_information.csv")
                               .requireNonNull()
                               .getFile()));

    countriesMap = reader.lines()
                         .map(line -> line.split(";"))
                         .map(countriesString -> new Country(countriesString[0].trim(),
                                                             countriesString[1].trim()))
                         .collect(Collectors.toMap(Country::getName, Function.identity()));
  }

  @Bean
  Map<String, Country> getCountriesMap() {
    return Collections.unmodifiableMap(countriesMap);
  }

  @Bean
  public Flight buildFlightFromCsv() throws IOException {
    @Cleanup val reader = new BufferedReader(
        new FileReader(FlightBuilder.class.getResource("/flights_information.csv")
                                          .requireNonNull()
                                          .getFile()));

    val flight = new Flight("AA1234", 20);

    reader.lines()
        .map(line -> line.split(";"))
        .map(passengerString -> new Passenger(passengerString[0].trim())
            .setCountry(countriesMap.get(passengerString[1].trim()))
            .setRegistered(false))
        .forEach(flight::addPassenger);

    return flight;
  }
}
