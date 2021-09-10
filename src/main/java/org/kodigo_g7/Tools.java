package org.kodigo_g7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Tools {

  private static Tools instance;
  private final Scanner write;

  public Tools() {
    write = new Scanner(System.in);
  }

  public static Tools getInstance() {
    if (instance == null) {
      instance = new Tools();
    }
    return instance;
  }

  public String Writing() {
    return write.nextLine();
  }

  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public int RandomValue(int Max, int Min) {
    return (int) (Math.random() * (Max - Min)) + Min;
  }

  public String getFormatTimeStamp(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
  }

  public String getFormatTimeFilter(Date date) {
    return new SimpleDateFormat("dd-MM-yyyy").format(date);
  }


  public Date paseStringDate(String dates){
    try {
      return new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(dates);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
}
