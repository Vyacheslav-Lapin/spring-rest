package com.luxoft.springadvanced.springrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

import static com.luxoft.springadvanced.springrest.common.HibernateUtils.*;
import static lombok.AccessLevel.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SuppressWarnings({"com.intellij.jpb.LombokDataInspection", "com.haulmont.ampjpb.LombokDataInspection"})
public class Country {

  @Id String codeName;
  String name;

  @Override
  @Contract(value = "null -> false", pure = true)
  public final boolean equals(Object o) {
    return this == o || o != null
                        && getEffectiveClass(this) == getEffectiveClass(o)
                        && getCodeName() != null
                        && o instanceof Country country
                        && Objects.equals(getCodeName(), country.getCodeName());
  }

  @Override
  public final int hashCode() {
    return getEffectiveClass(this).hashCode();
  }
}
