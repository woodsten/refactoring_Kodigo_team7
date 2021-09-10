package org.kodigo_g7.controllers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kodigo_g7.NotFoundException;
import org.kodigo_g7.Tools;
import org.kodigo_g7.models.Aircraft;
import org.kodigo_g7.models.Airport;
import org.kodigo_g7.models.Flight;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelController {

  private static ExcelController instance;

  public static ExcelController getInstance() {
    if (instance == null) {
      instance = new ExcelController();
    }
    return instance;
  }

  public void createExcel(ArrayList arrayList) {

    try {
      // creating an instance of HSSFWorkbook class
      Workbook xlsxWorkbook = new XSSFWorkbook();
      // invoking creatSheet() method and passing the name of the sheet to be created
      Sheet sheet1 = xlsxWorkbook.createSheet("Vuelos");
      // creating the 0th row using the createRow() method
      Row row = sheet1.createRow(0);

      String[] Header = {
        "Código",
        "Aeronave",
        "Aerolínea",
        "Origen",
        "Fecha/Hora",
        "Destino",
        "Fecha/Hora",
        "Estado",
        "Razón"
      };

      // creating cell by using the createCell() method and setting the values to the cell by using
      // the setCellValue() method

      for (int i = 0; i < Header.length; i++) {
        Cell cell = row.createCell(i);
        cell.setCellValue(Header[i]);
      }

      for (int j = 0; j < arrayList.size(); j++) {

        Flight p = (Flight) arrayList.get(j);
        row = sheet1.createRow(j + 1);

        row.createCell(0).setCellValue(p.getCodFlight());
        row.createCell(1).setCellValue(p.getAircraft().getModel());
        row.createCell(2).setCellValue(p.getAirlines());
        row.createCell(3).setCellValue(p.getOrigin().getCodIATA());
        row.createCell(4)
            .setCellValue(Tools.getInstance().getFormatTimeStamp(p.getDepartureDateTime()));
        row.createCell(5).setCellValue(p.getDestination().getCodIATA());
        row.createCell(6)
            .setCellValue(Tools.getInstance().getFormatTimeStamp(p.getArrivalDateTime()));
        row.createCell(7).setCellValue(p.getState());
        row.createCell(8).setCellValue(p.getReasons());
      }
      xlsxWorkbook.write(new FileOutputStream("Filghts.xlsx"));
      xlsxWorkbook.close();
    } catch (Exception e) {
      System.out.println("Ha ocurrido un error al momento de crear el documento de excel");
    }
  }

  public void readExcel() {
    try {
      FileInputStream file = new FileInputStream("Filghts.xlsx");

      XSSFWorkbook extract = new XSSFWorkbook(file);
      XSSFSheet sheet = extract.getSheet("Vuelos");
      // obtener todas las filas de la hoja excel
      Iterator<Row> rowIterator = sheet.iterator();

      Row row;
      Flight objNew = null;
      // se recorre cada fila hasta el final
      rowIterator.next();
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        // se obtiene las celdas por fila
        Iterator<Cell> cellIterator = row.cellIterator();
        Cell cell;
        // se recorre cada celda
        while (cellIterator.hasNext()) {

          objNew = new Flight();

          objNew.setCodFlight(cellIterator.next().getStringCellValue());
          Aircraft objAircraft =
              (Aircraft)
                  AircraftControllers.getInstance().Read(cellIterator.next().getStringCellValue());
          if (objAircraft != null) {
            objNew.setAircraft(objAircraft);
          } else {
            throw new NotFoundException("Aeronave");
          }

          objNew.setAirlines(cellIterator.next().getStringCellValue());

          Airport objAirport =
              (Airport)
                  AirportControllers.getInstance().Read(cellIterator.next().getStringCellValue());

          if (objAirport != null) {
            objNew.setOrigin(objAirport);
          } else {
            throw new NotFoundException("Aeropuerto");
          }

          objNew.setDepartureDateTime(
              Tools.getInstance().paseStringDate(cellIterator.next().getStringCellValue()));

          Airport objAirport2 =
              (Airport)
                  AirportControllers.getInstance().Read(cellIterator.next().getStringCellValue());

          if (objAirport2 != null) {
            objNew.setDestination(objAirport2);
          } else {
            throw new NotFoundException("Aeropuerto");
          }

          objNew.setArrivalDateTime(
              Tools.getInstance().paseStringDate(cellIterator.next().getStringCellValue()));

          objNew.setState(cellIterator.next().getStringCellValue());
          objNew.setReasons(cellIterator.next().getStringCellValue());
        }
        System.out.println(objNew.getState());
        FlightControllers.getInstance().Add(objNew);
      }

    } catch (Exception e) {
      System.out.println("Ha ocurrido un error al momento de leer el documento de excel");
      System.out.println(e.getMessage());
    }
  }

  public void getExcel() {}
}
