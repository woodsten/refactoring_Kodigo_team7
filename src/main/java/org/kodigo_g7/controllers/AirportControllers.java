package org.kodigo_g7.controllers;

import lombok.Data;
import org.kodigo_g7.Tools;
import org.kodigo_g7.interfaces.InitializeList;
import org.kodigo_g7.interfaces.ReadAction;
import org.kodigo_g7.models.Airport;

import java.util.ArrayList;

public @Data class AirportControllers implements InitializeList, ReadAction {

  private static AirportControllers instance;
  private ArrayList<Airport> listAirport;

  public AirportControllers() {
    this.initializeList();
  }

  public static AirportControllers getInstance() {
    if (instance == null) {
      instance = new AirportControllers();
    }
    return instance;
  }

  @Override
  public void initializeList() {
    // * lista de aeropuertos
    listAirport = new ArrayList<>();
    // * valor random para seleccionar el clima actual

    listAirport.add(
        new Airport(
            "Aeroparque Jorge Newbery",
            "Argentina",
            "AEP",
            WeatherSimulationControllers.getInstance()
                .getListWeatherSimulations()
                .get(
                    Tools.getInstance()
                        .RandomValue(
                            WeatherSimulationControllers.getInstance()
                                .getListWeatherSimulations()
                                .size(),
                            1))));

    listAirport.add(
        new Airport(
            "Aeropuerto de Albacete",
            "España",
            "ABC",
            WeatherSimulationControllers.getInstance()
                .getListWeatherSimulations()
                .get(
                    Tools.getInstance()
                        .RandomValue(
                            WeatherSimulationControllers.getInstance()
                                .getListWeatherSimulations()
                                .size(),
                            1))));

    listAirport.add(
        new Airport(
            "Aeropuerto Cerro Moreno",
            "Chile",
            "ANF",
            WeatherSimulationControllers.getInstance()
                .getListWeatherSimulations()
                .get(
                    Tools.getInstance()
                        .RandomValue(
                            WeatherSimulationControllers.getInstance()
                                .getListWeatherSimulations()
                                .size(),
                            1))));
  }

  @Override
  public Object Read(String code) {
    return this.getListAirport().stream()
        .filter(p -> p.getCodIATA().equals(code))
        .findFirst()
        .get();
  }
}
