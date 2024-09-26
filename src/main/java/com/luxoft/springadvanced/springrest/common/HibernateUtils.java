package com.luxoft.springadvanced.springrest.common;

import lombok.experimental.UtilityClass;
import org.hibernate.proxy.HibernateProxy;

@UtilityClass
public class HibernateUtils {

  public Class<?> getEffectiveClass(Object o) {
      return o instanceof HibernateProxy hibernateProxy ?
             hibernateProxy.getHibernateLazyInitializer()
                           .getPersistentClass() :
             o.getClass();
  }
}
