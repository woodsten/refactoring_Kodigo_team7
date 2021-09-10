package org.kodigo_g7.controllers;

import lombok.Data;
import org.kodigo_g7.interfaces.InitializeList;
import org.kodigo_g7.models.WeatherSimulation;

import java.util.ArrayList;

public @Data class WeatherSimulationControllers implements InitializeList {

  private static WeatherSimulationControllers instance;

  private ArrayList<WeatherSimulation> listWeatherSimulations;

  public WeatherSimulationControllers() {
    this.initializeList();
  }

  public static WeatherSimulationControllers getInstance() {
    if (instance == null) {
      instance = new WeatherSimulationControllers();
    }
    return instance;
  }

  @Override
  public void initializeList() {
    // * Lista de Clima
    listWeatherSimulations = new ArrayList<>();
    listWeatherSimulations.add(
        new WeatherSimulation("Parcialmente Nublado", "Viento del norte 25°", "30 °C"));
    listWeatherSimulations.add(new WeatherSimulation("despejado", "Viento del sur 20°", "28 °C"));
    listWeatherSimulations.add(
        new WeatherSimulation("precipitación intensa", "Viento del este 20°", "26 °C"));
    listWeatherSimulations.add(new WeatherSimulation("Nevado", "Viento del nor-este 20°", "10 °C"));
  }
}
