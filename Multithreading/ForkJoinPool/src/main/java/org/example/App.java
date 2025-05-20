package org.example;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ForkJoinPool;

import static java.lang.System.out;

public class App {
    public static void main( String[] args ) throws IOException {
        String url = "http://lenta.ru";
        String tabulator = "";
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(System.lineSeparator());
        String fileName = "src/main/resources/tree_" + getLocalDateTime() + ".txt";
        LocalTime begin = LocalTime.now();
        out.println("начало работы - " + begin.getHour() + ":" + begin.getMinute() + ":" + begin.getSecond());
        String line = new ForkJoinPool().invoke(new ListLinks(url,tabulator));
        //List list = new ForkJoinPool().invoke(new ListLinks(url,tabulator));
        sb.append(line);
        try (FileWriter file = new FileWriter(fileName)) {
      //try (BufferedWriter file = new BufferedWriter (new FileWriter(fileName, true))) {
            //file.append(sb.toString());
           // for (String line : list) {
         //       file.append(string);
           // }
            file.write(sb.toString());
        } catch (IOException e) { e.printStackTrace(); }
        LocalTime end = LocalTime.now();
        out.println("окончание работы - "+ end.getHour() +":" + end.getMinute() +":" + end.getSecond());
        Duration duration = Duration.between(begin, end);
        out.println("продолжительность работы - " + duration.toHoursPart() + " часов " + duration.toMinutesPart() + " минут");
    }
    private static String getLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        return (ldt.getYear() + "-" +
                ldt.getMonthValue() + "-" +
                ldt.getDayOfMonth() + "_" +
                ldt.getHour() + "-" +
                ldt.getMinute() + "-" +
                ldt.getSecond());
    }
}