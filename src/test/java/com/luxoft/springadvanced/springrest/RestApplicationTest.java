package com.luxoft.springadvanced.springrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.springadvanced.springrest.beans.FlightBuilder;
import com.luxoft.springadvanced.springrest.controller.CountryController;
import com.luxoft.springadvanced.springrest.dao.CountryRepository;
import com.luxoft.springadvanced.springrest.dao.PassengerRepository;
import com.luxoft.springadvanced.springrest.dto.Flight;
import com.luxoft.springadvanced.springrest.exception.PassengerNotFoundException;
import com.luxoft.springadvanced.springrest.model.Country;
import com.luxoft.springadvanced.springrest.model.Passenger;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class RestApplicationTest {

    MockMvc mvc;

    Flight flight;

    Map<String, Country> countriesMap;

    @MockBean
    PassengerRepository passengerRepository;

    @MockBean
    CountryRepository countryRepository;

    @Test
    @SneakyThrows
    void testGetAllCountries() {
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));
        mvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));

        mvc.perform(get("/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(20)));

        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    void testPassengerNotFound() {
        val throwable = assertThrows(ServletException.class, () ->
            mvc.perform(get("/passengers/30"))
               .andExpect(status().isNotFound()));
        assertEquals(PassengerNotFoundException.class,
                     throwable.getCause().getClass());
    }

    @Test
    void testPostPassenger() throws Exception {

        val passenger = new Passenger("Peter Michelsen")
            .setCountry(countriesMap.get("US"))
            .setId(Long.MAX_VALUE)
            .setRegistered(false);

        when(passengerRepository.save(passenger)).thenReturn(passenger);

        mvc.perform(post("/passengers")
                        .content(new ObjectMapper().writeValueAsString(passenger))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Peter Michelsen")))
                .andExpect(jsonPath("$.country.codeName", is("US")))
                .andExpect(jsonPath("$.country.name", is("USA")))
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));

        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testPatchPassenger() throws Exception {
        val passenger = new Passenger("Sophia Graham")
            .setCountry(countriesMap.get("UK"))
            .setRegistered(false);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        val updates = """
                      {
                        "name": "Sophia Jones",
                        "country": "AU",
                        "isRegistered": "true"
                      }""";

        mvc.perform(patch("/passengers/1")
                .content(updates)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);
    }

  @Test
  @SneakyThrows
  @DisplayName("NK country creation fails")
  void nKCountryCreationFailsTest() {
    // given
    val country = new Country("NK", "North Korea");

    //when
    mvc.perform(post(CountryController.BASE_URI)
                    .content(new ObjectMapper().writeValueAsString(country))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
       .andExpect(status().isUnprocessableEntity());
  }
}
