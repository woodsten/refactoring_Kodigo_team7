package org.kodigo_g7.models;

import lombok.Data;

public @Data class WeatherSimulation {

  // * Estado del clima
  private String weather;
  // * Estado del Viento
  private String wind;
  // * Temperatura
  private String temperature;

  public WeatherSimulation(String weather, String wind, String temperature) {
    this.setWeather(weather);
    this.setWind(wind);
    this.setTemperature(temperature);
  }
}
