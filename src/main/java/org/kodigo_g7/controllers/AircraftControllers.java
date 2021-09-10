package org.kodigo_g7.controllers;

import lombok.Data;
import org.kodigo_g7.interfaces.InitializeList;
import org.kodigo_g7.interfaces.ReadAction;
import org.kodigo_g7.models.Aircraft;

import java.util.ArrayList;

public @Data class AircraftControllers implements InitializeList, ReadAction {

  private static AircraftControllers instance;
  // * lista de vuelos
  private ArrayList<Aircraft> listAircraft;

  public AircraftControllers() {
    this.initializeList();
  }

  public static AircraftControllers getInstance() {
    if (instance == null) {
      instance = new AircraftControllers();
    }
    return instance;
  }

  public Aircraft createAircraft(String model, Integer amount, String rangeFullTanks) {
    return null;
  }

  @Override
  public void initializeList() {
      listAircraft = new ArrayList<>();
      listAircraft.add(new Aircraft("Airbus 319", 156, "7200 Km"));
      listAircraft.add(new Aircraft("Airbus 320", 220, "3100-1200 km"));
      listAircraft.add(new Aircraft("Boeing 777", 550, "17370 km"));
  }

  @Override
  public Object Read(String code) {
    return this.getListAircraft().stream()
            .filter(p -> p.getModel().equals(code))
            .findFirst()
            .get();
  }
}
