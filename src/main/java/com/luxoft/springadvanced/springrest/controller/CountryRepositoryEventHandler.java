package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.exception.CountryNotSupportedException;
import com.luxoft.springadvanced.springrest.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RepositoryEventHandler
public class CountryRepositoryEventHandler {

  @HandleBeforeCreate
  public void handlePersonBeforeCreate(Country country) {
    if (country.getCodeName().equalsIgnoreCase("NK"))
      throw new CountryNotSupportedException("NK is not supported");
  }

//  @HandleAfterCreate
//  public void handlePersonAfterCreate(Person person){
//    log.info("I am so tired to have created {}", person.getName());
//  }
//  @HandleBeforeDelete
//    public void handlePersonBeforeDelete(Person person) {
//        log.warn("This is just to let you know that {} is about to be deleted",
//                 person.getName());
//    }
//
//  @HandleAfterDelete
//    public void handlePersonAfterDelete(Person person) {
//        log.warn("Sad but true: {} has been deleted",
//                 person.getName());
//    }

}
