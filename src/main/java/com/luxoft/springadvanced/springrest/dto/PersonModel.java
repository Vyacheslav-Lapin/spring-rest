package com.luxoft.springadvanced.springrest.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.hateoas.RepresentationModel;

@Value
@Jacksonized
@Builder(toBuilder = true)
public class PersonModel extends RepresentationModel<PersonModel> {
  String firstname;
  String lastname;
}

