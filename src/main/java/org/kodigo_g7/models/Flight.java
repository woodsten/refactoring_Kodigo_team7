package org.kodigo_g7.models;

import lombok.Data;

import java.util.Date;

public @Data class Flight {
  // * código de vuelo
  private String CodFlight;
  // * aeronave
  private Aircraft aircraft;
  // * nombre de aerolínea
  private String airlines;
  // * origen o lugar de salida
  private Airport origin;
  // * destino o lugar de llegada
  private Airport destination;
  // * fecha y hora de llegada
  private Date arrivalDateTime;
  // * fecha y hora de salida
  private Date departureDateTime;
  // * estado del vuelo [retrasado, a tiempo, en tierra, en espera, cancelado]
  private String state;
  // * razones (porque o motivo del estado actual)
  private String reasons;

  // * constructor
  public Flight(
      String codFlight,
      Aircraft aircraft,
      String airlines,
      Airport origin,
      Airport destination,
      Date departureDateTime,
      Date arrivalDateTime,
      String state,
      String reasons) {
    this.setCodFlight(codFlight);
    this.setAircraft(aircraft);
    this.setAirlines(airlines);
    this.setOrigin(origin);
    this.setDestination(destination);
    this.setDepartureDateTime(departureDateTime);
    this.setArrivalDateTime(arrivalDateTime);
    this.setState(state);
    this.setReasons(reasons);
  }

    public Flight() {

    }
}
