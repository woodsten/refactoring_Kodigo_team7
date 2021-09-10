package org.kodigo_g7.controllers;

import lombok.Data;
import org.kodigo_g7.interfaces.*;
import org.kodigo_g7.models.Flight;
import org.kodigo_g7.Tools;

import java.util.ArrayList;
import java.util.Date;

public @Data class FlightControllers
    implements InitializeList, CreateAction, ReadAction, UpdateAction {

  private static FlightControllers instance;

  private ArrayList<Flight> listFlight;

  public FlightControllers() {
    this.initializeList();
  }

  public static FlightControllers getInstance() {
    if (instance == null) {
      instance = new FlightControllers();
    }
    return instance;
  }

  // * Busca un objeto en la lista a partir del código de vuelo
  @Override
  public Object Read(String code) {
    return this.getListFlight().stream()
            .filter(p -> p.getCodFlight().equals(code))
            .findFirst()
            .get();
  }

  // * Agrega un objeto a la lista
  @Override
  public void Add(Object obj) {
    this.getListFlight().add((Flight) obj);
  }


  // * Actualiza información un objeto contenido en la lista
  @Override
  public void Update(Object obj) {

    Flight mod = (Flight) obj;

   int index = this.getListFlight().indexOf(this.Read(mod.getCodFlight()));

   this.getListFlight().get(index).setState(mod.getState());

   this.getListFlight().get(index).setReasons(mod.getReasons());

  }

  // * generando lista de vuelo para la simulación de base de datos
  @Override
  public void initializeList() {
    listFlight = new ArrayList<>();

    /** 86 400 000 ms = 1 Day : 24*60*60*1000 3 600 000 ms = 1 Hour : 60*60*1000 */

    // * valor random para simular la seleccion de fecha para salida y llegada

    int plusDay = Tools.getInstance().RandomValue(30, 1);
    int plusHour = Tools.getInstance().RandomValue(24, 1);

    int hour = 60 * 60 * 1000;
    int day = 24 * 60 * 60 * 1000;

    Date date1 = new Date(new Date().getTime() + (plusHour * hour));

    Date date2 = new Date(new Date().getTime() + (plusDay * day));

    listFlight.add(
        new Flight(
            "ffee782",
            AircraftControllers.getInstance().getListAircraft().get(1),
            "Avianca",
            AirportControllers.getInstance().getListAirport().get(1),
            AirportControllers.getInstance().getListAirport().get(2),
            date1,
            date2,
            "preparado para salir",
            "Pendiente de Confirmacion"));
  }

}
