package org.kodigo_g7.models;

import lombok.Getter;
import lombok.Setter;

public class Aircraft {
  // * modelo de aeroplano
  @Getter @Setter private String model;
  // * cantidad de pasajeros
  @Getter @Setter private Integer amount;
  // * rango de tanque de gasolina
  @Getter @Setter private String rangeFullTanks;

  public Aircraft(String model, Integer amount, String rangeFullTanks) {
    this.setModel(model);
    this.setAmount(amount);
    this.setRangeFullTanks(rangeFullTanks);
  }
}
