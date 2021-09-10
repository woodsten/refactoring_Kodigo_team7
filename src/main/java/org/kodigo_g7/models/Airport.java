package org.kodigo_g7.models;

import lombok.Data;

public @Data class Airport {
  // * codicion climatica
  public WeatherSimulation weather;
  // * nombre del aeropuerto
  private String nameAirport;
  // * nombre de pais al que pertenece
  private String country;
  // * código de identificación de los aeropuertos de forma global
  private String codIATA;

  public Airport(String nameAirport, String country, String codIATA, WeatherSimulation weather) {
    this.setNameAirport(nameAirport);
    this.setCountry(country);
    this.setCodIATA(codIATA);
    this.setWeather(weather);
  }
}
