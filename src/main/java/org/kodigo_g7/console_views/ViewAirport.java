package org.kodigo_g7.console_views;

import org.kodigo_g7.NotFoundException;
import org.kodigo_g7.Tools;
import org.kodigo_g7.controllers.AirportControllers;
import org.kodigo_g7.controllers.WeatherSimulationControllers;
import org.kodigo_g7.interfaces.ViewList;
import org.kodigo_g7.interfaces.ViewObject;
import org.kodigo_g7.models.Airport;

import java.util.ArrayList;

public class ViewAirport implements ViewList, ViewObject {

  private static ViewAirport instance;

  String formatStringTitulo = "|%36s%-47s|\n";

  String formatStringHeader = "|%-26s|%-14s|%-14s|%-26s|\n";
  String separator =
      "+--------------------------+--------------+--------------+--------------------------+";
  String red = "\033[31m";
  String reset = "\u001B[0m";
  String yellow = "\033[33m";

  public static ViewAirport getInstance() {
    if (instance == null) {
      instance = new ViewAirport();
    }
    return instance;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void printViewList(ArrayList arrayList) {
    this.titlePrintList();
    this.headerPrintList();

    ((ArrayList<Airport>) arrayList)
        .forEach(
            p -> {
              System.out.printf(
                  formatStringHeader,
                  " " + p.getNameAirport(),
                  " " + p.getCountry(),
                  " " + p.getCodIATA(),
                  " " + p.getWeather().getWeather());
              System.out.println(separator);
            });
  }

  @Override
  public void titlePrintList() {
    System.out.println(separator);
    System.out.printf(formatStringTitulo, " ", "Aeropuertos");
    System.out.println(separator);
  }

  @Override
  public void headerPrintList() {
    System.out.printf(formatStringHeader, "   Nombre", "   pais", "   IATA", " Clima Actual");
    System.out.println(separator);
  }

  @Override
  public void printViewObject(Object obj) {
    Airport p = (Airport) obj;

    this.titlePrintList();
    this.headerPrintList();
    System.out.printf(
        formatStringHeader,
        " " + p.getNameAirport(),
        " " + p.getCountry(),
        " " + p.getCodIATA(),
        " " + p.getWeather().getWeather());
    System.out.println(separator);
  }

  public void getIATAprint() {
    try {
      String formatString = "|%-20s| ";
      System.out.println(red + "Ingrese el Código IATA del Aeropuerto que desea buscar" + reset);
      System.out.printf(formatString, "  Código");
      Airport objAirport2 =
          (Airport) AirportControllers.getInstance().Read(Tools.getInstance().Writing());

      if (objAirport2 != null) {

        objAirport2.setWeather(
            WeatherSimulationControllers.getInstance()
                .getListWeatherSimulations()
                .get(
                    Tools.getInstance()
                        .RandomValue(
                            WeatherSimulationControllers.getInstance()
                                .getListWeatherSimulations()
                                .size(),
                            1)));

        Tools.getInstance().clearScreen();
        System.out.println(red + "A continuación puede ver el Clima actual" + reset);
        this.printViewObject(objAirport2);

      } else {
        throw new NotFoundException("Aeropuerto");
      }
    } catch (NotFoundException e) {
      System.out.println(
          yellow
              + "\n"
              + e.getMessage()
              + " no fue encontrado con la información ingresada \n"
              + e.getMessage()
              + reset);
    }
  }
}
