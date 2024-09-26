package com.luxoft.springadvanced.springrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

import static com.luxoft.springadvanced.springrest.common.HibernateUtils.*;
import static lombok.AccessLevel.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SuppressWarnings({"com.intellij.jpb.LombokDataInspection", "com.haulmont.ampjpb.LombokDataInspection"})
public class Passenger {

  @Id @GeneratedValue Long id;
  @NonNull String name;
  @ManyToOne Country country;
  boolean isRegistered;

  @Override
  @Contract(value = "null -> false", pure = true)
  public final boolean equals(Object o) {
    return this == o || o != null
                        && getEffectiveClass(this) == getEffectiveClass(o)
                        && getId() != null
                        && o instanceof Passenger passenger
                        && Objects.equals(getId(), passenger.getId());
  }

  @Override
  public final int hashCode() {
    return getEffectiveClass(this).hashCode();
  }
}
