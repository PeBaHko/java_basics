import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.nio.charset.*;
import java.time.*;
import java.util.*;
import java.util.zip.*;
import java.nio.file.*;

import static java.lang.System.out;

public class Main {
  private static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
  private static final String CURRENT_DIR = System.getProperty("user.dir");
  private static final String WORK_DIR = "data".concat(FILE_SEPARATOR);
  private static final String DIR = "stations-data";
  public static void main( String[] args ) {
      Debug debug = new Debug();
      String url = "https://skillbox-java.github.io/";
      String html = "data/stations-data.html";
      //System.out.println("[" + FILE_SEPARATOR + "]");
      //System.out.println("["+ WORK_DIR +"]");
      //System.out.println("["+ System.lineSeparator() +"]");
      GetData gd = new GetData();
     // out.println("current dir - " + System.getProperty("user.dir"));
     // out.println("home dir - " + System.getProperty("user.home"));
    //  out.println("Путь до дерева каталогов:");
    //  out.println("\t" + new File(WORK_DIR.concat(DIR)).getAbsolutePath());
    //  out.println("\t" + new File(WORK_DIR.concat(DIR)).getPath());
    //  File f = new File("data");
    //  out.println("f path - " + f.getPath());
    //  out.println("f path - " + f.getAbsolutePath());
    //  out.println("f type - " + f.exists());
      // поиск JSON-файлов
      ArrayList<File> jsonFiles = gd.getFiles(new File(CURRENT_DIR),".json");
      out.println("поиск файлов типа .JSON");

      // поиск CSV-файлов
      ArrayList<File> csvFiles = gd.getFiles(new File(CURRENT_DIR),".csv");
      out.println("поиск файлов типа .CSV");
      // вывод на печать списка JSON-файлов
      //debug.printFilesList(jsonFiles);

      // вывод на печать списка CSV-файлов
     // debug.printFilesList(csvFiles);

    //  debug.printFiles(jsonFiles.get(0));
      //debug.printFiles(jsonFiles.get(1));
      //debug.printFiles(jsonFiles.get(2));
      //debug.printFiles(csvFiles.get(0));
     // printFiles(csvFiles.get(1));
    //  printFiles(csvFiles.get(2));
     //ArrayList<String[]> f = gd.getArrayFromCsv(csvFiles.get(0));
      //debug.printCsvArray(gd.getArrayFromJson(jsonFiles.get(0)));
      //debug.printCsvArray(gd.getArrayFromCsv(csvFiles.get(0)));

      //общий слитый список из JSON файлов
     //  debug.printCsvArray(gd.getArrayFromJson(jsonFiles));

      // общий слитый список из CSV файлов
      //  debug.printCsvArray(gd.getArrayFromCsv(csvFiles));

      // парсинг списка CSV-файлов в список пар строк:
      //     название станции - дата открытия
      ArrayList<String[]> date = gd.getArrayFromCsv(csvFiles);
      out.println("Парсинг CSV-файлов в список пар:");
      out.println("\tназвание станции - дата создания");

      // парсинг списка JSON-файлов в список пар строк:
      //     название станции - глубина залегания станции
      ArrayList<String[]> depth = gd.getArrayFromJson(jsonFiles);
      out.println("Парсинг JSON-файлов в список пар:");
      out.println("\tназвание станции - глубина залегания");

      // парсинг HTML-страницы в список-дерево объектов:
      //  Список линий:
      //    - линия - объект:
      //      - список станций:
      //        - станция - объект:
      //          - список пересадок
      //Document document = Jsoup.parse(gd.parseFile(new File(html)));
      try {
          Document document = Jsoup.connect(url).get();
          ArrayList<LineMosMetro> mosMetro = gd.getTreeFromHtml(document, date, depth);
          gd.createJsonMap(mosMetro);
          gd.createJsonStation(mosMetro);
      } catch (Exception e) { e.printStackTrace(); }

              out.println("чтение HTML-файла");
    //  ArrayList<LineMosMetro> mosMetro = gd.getTreeFromHtml(document, date, depth);
      out.println("создание схемы метро в виде дерева объектов");
    //  debug.printHtmlTree(mosMetro);

      // создание карты JSON-файла

      out.println("создание JSON-файла со:");
      out.println("\t- списком станций по линиям;");
      out.println("\t- списком линий;");
      out.println("\t- списком пересадок");

      // создание станций JSON-файла

      out.println("создание JSON-файла с общим списком станций");

      //debug.printWithIterator();
      //jsonFiles.forEach(file -> out.println(file.getAbsolutePath()));
      //csvFiles.forEach(file -> out.println(file.getAbsolutePath()) );
         //   debug.writeJsonFile();

      //  debug.parseJsonFile(jsonFiles.get(1));
      //out.println("число файлов - " + jsonFiles.size());

   //   debug.getUrl(document);
      //debug.getUrl(gd.parseFile(new File(html)));
     // printFiles(new File(WORK_DIR.concat(DIR).concat(".html")));
      //getFile(new File(WORK_DIR.concat(DIR).concat(".html")));
  }
}