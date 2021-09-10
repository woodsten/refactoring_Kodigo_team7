package org.kodigo_g7.console_views;

import org.kodigo_g7.interfaces.ViewList;
import org.kodigo_g7.models.Aircraft;

import java.util.ArrayList;

public class ViewAircraft implements ViewList {

  private static ViewAircraft instance;

  String formatStringTitulo = "|%25s%-31s|\n";

  String formatStringHeader = "|%-14s|%-26s|%-14s|\n";
  String separator = "+--------------+--------------------------+--------------+";
  String red = "\033[31m";
  String reset = "\u001B[0m";
  String yellow = "\033[33m";

  public static ViewAircraft getInstance() {
    if (instance == null) {
      instance = new ViewAircraft();
    }
    return instance;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void printViewList(ArrayList arrayList) {
    this.titlePrintList();
    this.headerPrintList();

    ((ArrayList<Aircraft>) arrayList)
        .forEach(
            p -> {
              System.out.printf(
                  formatStringHeader,
                  " " + p.getModel(),
                  "          " + p.getAmount(),
                  " " + p.getRangeFullTanks());
              System.out.println(separator);
            });
  }

  @Override
  public void titlePrintList() {
    System.out.println(separator);
    System.out.printf(formatStringTitulo, " ", "Aeronaves");
    System.out.println(separator);
  }

  @Override
  public void headerPrintList() {
    System.out.printf(formatStringHeader, "   Modelo", "   Cantidad de Pasajero", "   Rango");
    System.out.println(separator);
  }
}
