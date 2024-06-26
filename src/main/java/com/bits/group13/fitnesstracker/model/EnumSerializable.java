package com.bits.group13.fitnesstracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface EnumSerializable {
  @JsonProperty("type")
  public Type<?> getType();

  interface Type<T extends Enum<T>> {
    Class<? extends EnumSerializable> getTypeClass();
  }
}
