package org.kodigo_g7.console_views;

import org.kodigo_g7.Tools;

public class ViewMail {

    private static ViewMail instance;

    public static ViewMail getInstance() {
        if (instance == null) {
            instance = new ViewMail();
        }
        return instance;
    }

  public String getAdresses() {
    String red = "\033[31m";
    String reset = "\u001B[0m";
    String formatString = "|%-20s| ";
    System.out.println(red + "Ingrese Correo Electronico de destino" + reset);

    System.out.printf(formatString, "  Correo");
    return Tools.getInstance().Writing();
  }
}
