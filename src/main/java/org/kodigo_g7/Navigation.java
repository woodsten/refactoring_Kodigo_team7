package org.kodigo_g7;

import org.kodigo_g7.console_views.*;
import org.kodigo_g7.controllers.*;

public class Navigation {
  private static Navigation instance;

  public static Navigation getInstance() {
    if (instance == null) {
      instance = new Navigation();
    }
    return instance;
  }

  public void index() {
    String loop = "z";
    String close = "";

    while (loop == "z" && close == "") {
      Tools.getInstance().clearScreen();
      System.out.println("Bienvenidos sistemas de administration de vuelos");
      ViewMenu.getInstance().indexMenu();

      switch (Tools.getInstance().Writing()) {
        case "1":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra Menu Vuelos ");
          this.flightNav();
          close = "0";
          break;

        case "2":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra Menu Aeronaves");
          this.aircraftNav();
          close = "0";
          break;

        case "3":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra Menu Aeropuertos");
          this.airportNav();
          close = "0";
          break;

        default:
          close = "close";
          break;
      }
      if (close.equals("")) {
        do {
          if (loop != "z") {
            System.out.println("La tecla Presionada no es valida");
          }
          System.out.println("Presione x para regresar");
          loop = Tools.getInstance().Writing();
        } while (!loop.equals("x"));
        loop = "z";
      } else if (close != "close") {
        close = "";
      }
    }
  }

  public void flightNav() {
    String red = "\033[31m";
    String reset = "\u001B[0m";

    String close = "";
    String loop = "1";

    while (loop == "1" && close == "") {
      Tools.getInstance().clearScreen();
      ViewMenu.getInstance().showMenuFlight();

      switch (Tools.getInstance().Writing()) {
        case "1":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra la lista de Vuelos ");
          ViewFlight.getInstance().printViewList(FlightControllers.getInstance().getListFlight());
          break;

        case "2":
          Tools.getInstance().clearScreen();
          ViewFlight.getInstance().printViewCreate();
          break;

        case "3":
          Tools.getInstance().clearScreen();
          System.out.println("Leyendo excel...");
          ExcelController.getInstance().readExcel();
          System.out.println(red + "Datos Almacenados Correctamente" + reset);
          break;

        case "4":
          Tools.getInstance().clearScreen();
          ViewFlight.getInstance().printViewUpdate();
          break;

        case "5":
          Tools.getInstance().clearScreen();
          System.out.println("Generando excel...");
          ExcelController.getInstance()
              .createExcel(FlightControllers.getInstance().getListFlight());
          System.out.println(red + "Excel Correctamente" + reset);
          break;

        case "6":
          Tools.getInstance().clearScreen();
          System.out.println("Enviando Correo...");
          String dir = ViewMail.getInstance().getAdresses();
          MailControllers.getInstance().sendMail(dir);
          System.out.println(red + "Correo Enviado Correctamente" + reset);
          break;

        default:
          close = "close";
          break;
      }
      if (close.equals("")) {
        do {
          if (loop != "1") {
            System.out.println("La tecla Presionada no es valida");
          }
          System.out.println("Presione el numero 0 para regresar");
          loop = Tools.getInstance().Writing();
        } while (!loop.equals("0"));
        loop = "1";
      }
    }
  }

  public void airportNav() {
    String red = "\033[31m";
    String reset = "\u001B[0m";

    String close = "";
    String loop = "1";

    while (loop == "1" && close == "") {
      Tools.getInstance().clearScreen();
      ViewMenu.getInstance().showMenuAirport();

      switch (Tools.getInstance().Writing()) {
        case "1":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra la lista de Aeropuertos ");
          ViewAirport.getInstance()
              .printViewList(AirportControllers.getInstance().getListAirport());
          break;

        case "2":
          Tools.getInstance().clearScreen();
          ViewAirport.getInstance().getIATAprint();
          break;
        default:
          close = "close";
          break;
      }
      if (close.equals("")) {
        do {
          if (loop != "1") {
            System.out.println("La tecla Presionada no es valida");
          }
          System.out.println("Presione el numero 0 para regresar");
          loop = Tools.getInstance().Writing();
        } while (!loop.equals("0"));
        loop = "1";
      }
    }
  }

  public void aircraftNav() {
    String red = "\033[31m";
    String reset = "\u001B[0m";

    String close = "";
    String loop = "1";

    while (loop == "1" && close == "") {
      Tools.getInstance().clearScreen();
      ViewMenu.getInstance().showMenuAircraft();

      switch (Tools.getInstance().Writing()) {
        case "1":
          Tools.getInstance().clearScreen();
          System.out.println("A continuación se muestra la lista de Aeronaves ");
          ViewAircraft.getInstance()
              .printViewList(AircraftControllers.getInstance().getListAircraft());
          break;
        default:
          close = "close";
          break;
      }
      if (close.equals("")) {
        do {
          if (loop != "1") {
            System.out.println("La tecla Presionada no es valida");
          }
          System.out.println("Presione el numero 0 para regresar");
          loop = Tools.getInstance().Writing();
        } while (!loop.equals("0"));
        loop = "1";
      }
    }
  }
}
