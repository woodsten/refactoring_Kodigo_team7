package org.kodigo_g7.console_views;

public class ViewMenu {

  private static ViewMenu instance;
  // * mostrar en pantalla el menu de acciones
  String red = "\033[31m";
  String green = "\033[32m";
  String reset = "\u001B[0m";

  public static ViewMenu getInstance() {
    if (instance == null) {
      instance = new ViewMenu();
    }
    return instance;
  }

  public void indexMenu() {
    String separator = "+--------------------+--------------------+";
    String formatString = "|%3s|%-37s|\n";
    String formatStringMenu = "|%17s%-24s|\n";

    System.out.println(red + "Ingrese un numero para seleccionar una de las opciones del menu");

    // Un encabezado
    System.out.println(green + separator);
    System.out.printf(formatStringMenu, " ", " Menu");
    System.out.println(separator);
    System.out.printf(formatString, "1 ", " Gestionar de Vuelos");
    System.out.println(separator);
    System.out.printf(formatString, "2 ", " Gestionar Aeronaves");
    System.out.println(separator);
    System.out.printf(formatString, "3 ", " Gestionar Aeropuertos o Destinos");
    System.out.println(separator);
    System.out.printf(formatString, "4 ", " Salir");
    System.out.println(separator + reset);
  }

  // * imprimir menu
  public void showMenuFlight() {
    String separator = "+--------------------+--------------------+";
    String formatString = "|%3s|%-37s|\n";
    String formatStringMenu = "|%17s%-24s|\n";

    System.out.println(red + "Ingrese un numero para seleccionar una de las opciones del menu");

    // Un encabezado
    System.out.println(green + separator);
    System.out.printf(formatStringMenu, " ", " Menu");
    System.out.println(separator);
    System.out.printf(formatString, "1 ", " Ver Lista de Vuelos");
    System.out.println(separator);
    System.out.printf(formatString, "2 ", " Agregar Vuelos");
    System.out.println(separator);
    System.out.printf(formatString, "3 ", " Agregar Vuelos Por Lotes");
    System.out.println(separator);
    System.out.printf(formatString, "4 ", " Actualizar Información de un Vuelo");
    System.out.println(separator);
    System.out.printf(formatString, "5 ", " Generar Exel");
    System.out.println(separator);
    System.out.printf(formatString, "6 ", " Enviar lista de vuelos por email");
    System.out.println(separator);
    System.out.printf(formatString, "7 ", " Regresar");
    System.out.println(separator + reset);
  }

  public void showMenuAirport() {
    String separator = "+--------------------+--------------------+";
    String formatString = "|%3s|%-37s|\n";
    String formatStringMenu = "|%17s%-24s|\n";

    System.out.println(red + "Ingrese un numero para seleccionar una de las opciones del menu");

    // Un encabezado
    System.out.println(green + separator);
    System.out.printf(formatStringMenu, " ", " Menu");
    System.out.println(separator);
    System.out.printf(formatString, "1 ", " Ver Lista de Aeropuertos");
    System.out.println(separator);
    System.out.printf(formatString, "2 ", " Ver Clima Actual del Aeropuerto");
    System.out.println(separator);
    System.out.printf(formatString, "3 ", " Regresar");
    System.out.println(separator + reset);
  }

  public void showMenuAircraft() {
    String separator = "+--------------------+--------------------+";
    String formatString = "|%3s|%-37s|\n";
    String formatStringMenu = "|%17s%-24s|\n";

    System.out.println(red + "Ingrese un numero para seleccionar una de las opciones del menu");

    // Un encabezado
    System.out.println(green + separator);
    System.out.printf(formatStringMenu, " ", " Menu");
    System.out.println(separator);
    System.out.printf(formatString, "1 ", " Ver Lista de Aeronaves");
    System.out.println(separator);
    System.out.printf(formatString, "2 ", " Regresar");
    System.out.println(separator + reset);
  }
}
