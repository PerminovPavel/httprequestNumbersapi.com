package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
  public static void main(String[] args)  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите номер месяца (от 1 до 12):");
    int numOfMonth = sc.nextInt();
    while (numOfMonth < 1 || numOfMonth > 12) {
      System.out.println("Введите корректный номер месяца (от 1 до 12):");
      numOfMonth = sc.nextInt();
    }
    System.out.println("Введите номер дня (от 1 до 31):");
    int numOfDay = sc.nextInt();
    while (numOfDay < 1 || numOfDay > 31) {
      System.out.println("Введите корректный номер дня (от 1 до 31):");
      numOfDay = sc.nextInt();
    }
    String uri = "http://numbersapi.com/"+numOfMonth+"/"+numOfDay+"/date";
    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
    } catch (URISyntaxException e) {
      System.out.println("Нет связи с URL");
    }
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      System.out.println("Ошибка inout");;
    } catch (InterruptedException e) {
      System.out.println("Что-то прервало подключение");;
    }
    System.out.println(getDay(numOfDay)+" "+getMonth(numOfMonth)+" - этот день в истории:");
    System.out.println(response.body());

  }
  public static String getMonth(int month) {
    return new DateFormatSymbols().getMonths()[month-1];
  }
  public static String getDay(int day) {
    DecimalFormat dF = new DecimalFormat( "00" );
    return dF.format(day);
  }
}