package com.luxoft.springadvanced.springrest.controller;

import com.luxoft.springadvanced.springrest.dto.PersonModel;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class PersonModelController {

  @Value("${server.port}")
  int port;

  @GetMapping("{id}")
  public PersonModel personModel(@PathVariable Long id) {
    val model = PersonModel.builder()
                           .firstname("Dave")
                           .lastname("Matthews").build();

    model.add(Link.of("https://localhost:8080/model/%d".formatted(id)));
    return model;
  }
}

