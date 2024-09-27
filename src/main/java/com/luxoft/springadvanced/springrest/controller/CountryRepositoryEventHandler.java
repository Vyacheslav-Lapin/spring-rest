package com.luxoft.springadvanced.springrest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@Slf4j
@RepositoryEventHandler
public class CountryRepositoryEventHandler {

//  @HandleBeforeCreate
//  public void handlePersonBeforeCreate(Country country) {
//    if ((country.getCodeName()Name().toUpperCase().charAt(0) >= 'A')
//        && (country.getName().toUpperCase().charAt(0) <= 'M')) {
//      log.info("Person {} is to be created, goes to the first part of the alphabet",
//               person.getName());
//    } else {
//      log.info("Person {} is to be created, goes to the second part of the alphabet",
//               person.getName());
//        }
//    }
//
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
