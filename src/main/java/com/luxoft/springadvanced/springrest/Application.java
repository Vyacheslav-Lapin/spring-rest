package com.luxoft.springadvanced.springrest;

import com.luxoft.springadvanced.springrest.beans.FlightBuilder;
import com.luxoft.springadvanced.springrest.dao.CountryRepository;
import com.luxoft.springadvanced.springrest.dao.PassengerRepository;
import com.luxoft.springadvanced.springrest.dto.Flight;
import com.luxoft.springadvanced.springrest.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
@Import(FlightBuilder.class)
public class Application {

  Flight flight;
  Map<String, Country> countriesMap;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner configureRepository(CountryRepository countryRepository,
                                        PassengerRepository passengerRepository) {
    return __ -> {
      countryRepository.saveAll(countriesMap.values());
      passengerRepository.saveAll(flight.getPassengers());
    };
  }
}
