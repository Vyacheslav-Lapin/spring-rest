package com.luxoft.springadvanced.springrest.dao;

import com.luxoft.springadvanced.springrest.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CountryRepository extends JpaRepository<Country, Long> {
}
