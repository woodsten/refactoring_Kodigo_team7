package org.kodigo_g7.console_views;

import org.kodigo_g7.NotFoundException;
import org.kodigo_g7.Tools;
import org.kodigo_g7.controllers.AircraftControllers;
import org.kodigo_g7.controllers.AirportControllers;
import org.kodigo_g7.controllers.FlightControllers;
import org.kodigo_g7.interfaces.ViewCreate;
import org.kodigo_g7.interfaces.ViewList;
import org.kodigo_g7.interfaces.ViewObject;
import org.kodigo_g7.interfaces.ViewUpdate;
import org.kodigo_g7.models.Aircraft;
import org.kodigo_g7.models.Airport;
import org.kodigo_g7.models.Flight;

import java.util.ArrayList;

public class ViewFlight implements ViewObject, ViewList, ViewCreate, ViewUpdate {

  private static ViewFlight instance;

  String formatStringTitulo = "|%77s%-92s|\n";

  String formatStringHeader = "|%-12s|%-15s|%-15s|%-15s|%-19s|%-12s|%-19s|%-25s|%-29s|\n";
  String separator =
      "+------------+---------------+---------------+---------------+-------------------+------------+-------------------+-------------------------+-----------------------------+";
  String red = "\033[31m";
  String reset = "\u001B[0m";
  String yellow = "\033[33m";

  public static ViewFlight getInstance() {
    if (instance == null) {
      instance = new ViewFlight();
    }
    return instance;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void printViewList(ArrayList arrayList) {

    this.titlePrintList();
    this.headerPrintList();

    ((ArrayList<Flight>) arrayList)
        .forEach(
            p -> {
              System.out.printf(
                  formatStringHeader,
                  " " + p.getCodFlight(),
                  " " + p.getAircraft().getModel(),
                  " " + p.getAirlines(),
                  " " + p.getOrigin().getCodIATA(),
                  " " + Tools.getInstance().getFormatTimeStamp(p.getDepartureDateTime()),
                  " " + p.getDestination().getCodIATA(),
                  " " + Tools.getInstance().getFormatTimeStamp(p.getArrivalDateTime()),
                  " " + p.getState(),
                  " " + p.getReasons());
              System.out.println(separator);
            });
  }

  @Override
  public void printViewObject(Object obj) {
    Flight p = (Flight) obj;

    this.titlePrintList();
    this.headerPrintList();
    System.out.printf(
        formatStringHeader,
        " " + p.getCodFlight(),
        " " + p.getAircraft().getModel(),
        " " + p.getAirlines(),
        " " + p.getOrigin().getCodIATA(),
        " " + Tools.getInstance().getFormatTimeStamp(p.getDepartureDateTime()),
        " " + p.getDestination().getCodIATA(),
        " " + Tools.getInstance().getFormatTimeStamp(p.getArrivalDateTime()),
        " " + p.getState(),
        " " + p.getReasons());
    System.out.println(separator);
  }

  // * Titulo a mostar cuando se imprime la lista en pantalla y valores de configuración
  @Override
  public void titlePrintList() {
    System.out.println(separator);
    System.out.printf(formatStringTitulo, " ", "Vuelos");
    System.out.println(separator);
  }

  // * encabezado a mostar cuando se imprime la lista en pantalla y valores de configuración
  @Override
  public void headerPrintList() {
    System.out.printf(
        formatStringHeader,
        "   Código",
        "   Aeronave",
        "   Aerolínea",
        "    Salida",
        "    Fecha/Hora",
        "  LLegada",
        "   Fecha/Hora",
        "         Estado",
        "           Razón");
    System.out.println(separator);
  }

  @Override
  public void printViewCreate() {
    String formatString = "|%-25s| ";
    System.out.println(
        red + "Ingrese la información necesaria para generar un nuevo vuelo" + reset);
    Flight objNew = new Flight();

    try {
      System.out.printf(formatString, "  Código de Vuelo");
      objNew.setCodFlight(Tools.getInstance().Writing());

      System.out.printf(formatString, "  Modelo de Aeronave");
      Aircraft objAircraft =
          (Aircraft) AircraftControllers.getInstance().Read(Tools.getInstance().Writing());

      if (objAircraft != null) {
        objNew.setAircraft(objAircraft);
      } else {
        throw new NotFoundException("Aeronave");
      }

      System.out.printf(formatString, "  Aerolínea");
      objNew.setAirlines(Tools.getInstance().Writing());

      System.out.printf(formatString, "  Código IATA de origen");
      Airport objAirport =
          (Airport) AirportControllers.getInstance().Read(Tools.getInstance().Writing());

      if (objAirport != null) {
        objNew.setOrigin(objAirport);
      } else {
        throw new NotFoundException("Aeropuerto");
      }

      System.out.println("Formato fecha: dd-mm-yyyy hh:mm ");
      System.out.printf(formatString, "  Fecha y Hora de Salida");
      objNew.setDepartureDateTime(
          Tools.getInstance().paseStringDate(Tools.getInstance().Writing()));

      System.out.printf(formatString, "  Código IATA de destino");
      Airport objAirport2 =
          (Airport) AirportControllers.getInstance().Read(Tools.getInstance().Writing());

      if (objAirport2 != null) {
        objNew.setDestination(objAirport2);
      } else {
        throw new NotFoundException("Aeropuerto");
      }

      System.out.println("Formato fecha: dd-mm-yyyy hh:mm ");
      System.out.printf(formatString, "  Fecha y Hora de Llegada");
      objNew.setArrivalDateTime(Tools.getInstance().paseStringDate(Tools.getInstance().Writing()));

      objNew.setState("preparado para salir");
      objNew.setReasons("Pendiente de Confirmación");

      FlightControllers.getInstance().Add(objNew);

      System.out.println(red + "El Vuelo a sido generado y guardado con exito\n" + reset);

    } catch (NotFoundException e) {
      System.out.println(
          yellow
              + "\n"
              + e.getMessage()
              + " no fue encontrado con la información ingresada \nrevise la lista de "
              + e.getMessage()
              + reset);
    }
  }

  @Override
  public void printViewUpdate() {
    String formatString = "|%-20s| ";
    System.out.println(
        red + "Ingrese el Código del vuelo el cual desea Actualizar su información" + reset);
    Flight objNew = null;
    try {
      System.out.printf(formatString, "  Código de Vuelo");
      objNew = (Flight) FlightControllers.getInstance().Read(Tools.getInstance().Writing());

      if (objNew == null) {
        throw new NotFoundException("Vuelo");
      }
      this.printViewObject(objNew);

      System.out.println(
          yellow
              + "La información que es posible modifica es El Estado y el motivo del cambio\n"
              + reset);

      System.out.printf(formatString, "  Estado");
      objNew.setState(Tools.getInstance().Writing());

      System.out.printf(formatString, "  Razón");
      objNew.setReasons(Tools.getInstance().Writing());

      Tools.getInstance().clearScreen();
      System.out.println(red + "La información se a actualizado\n" + reset);

      String code = objNew.getCodFlight();

      FlightControllers.getInstance().Update(objNew);

      objNew = (Flight) FlightControllers.getInstance().Read(code);

      if (objNew == null) {
        throw new NotFoundException("Vuelo");
      }
      this.printViewObject(objNew);

    } catch (NotFoundException e) {
      System.out.println(
          yellow
              + "\nEl"
              + e.getMessage()
              + " no fue encontrado con la información ingresada \nrevise la lista de "
              + e.getMessage()
              + "s"
              + reset);
    }
  }
}
