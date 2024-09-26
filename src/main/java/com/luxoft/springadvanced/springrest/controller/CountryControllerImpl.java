package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.dao.CountryRepository;
import com.luxoft.springadvanced.springrest.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {

    CountryRepository repository;

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }
}
