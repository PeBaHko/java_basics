import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.out;
public class GetData {
  public GetData() { files = new ArrayList<>(); }
  private final ArrayList<File> files;
    //private ArrayList<StringBuilder> list = null;
    //public ArrayList<StringBuilder> getFile(File file, String key) {

  // создание JSON-файла из карты-дерева метро:
  // Список:
  //   - станции по линиям
  //   - пересадки
  //   - линии
  public void createJsonMap(ArrayList<LineMosMetro> mosMetro) {
    JSONObject map = new JSONObject();
    JSONObject stations = new JSONObject();
    JSONArray connections = new JSONArray();
    JSONArray lines = new JSONArray();
    JSONObject line;
    JSONArray stationsArrayOnLine; // = new JSONArray();
    JSONArray connection;
    JSONObject stationONConnection;
    ArrayList<StationMosMetro> stationList;
    for (LineMosMetro lineMetro : mosMetro) {
      stationList = lineMetro.getStations();
      stationsArrayOnLine = new JSONArray();
      for (StationMosMetro station : stationList) {
        stationsArrayOnLine.add(station.getName());
      }
      stations.put(lineMetro.getName(),stationsArrayOnLine);
    }
    String stationName = "stations";
    map.put(stationName, stations);

    ArrayList<StationMosMetro> stationsMetro;
    ArrayList<ConnectionMosMetro> connectionsMetro;
    String lineName = "station";
    String lineNumber = "line";
    for (LineMosMetro lineMetro : mosMetro) {
      stationsMetro = lineMetro.getStations();
      for (StationMosMetro stationMetro : stationsMetro) {
        if (stationMetro.isHasConnection()) {
          connectionsMetro = stationMetro.getConnections();
          connection = new JSONArray();
          for (ConnectionMosMetro connectionMetro : connectionsMetro) {
            stationONConnection = new JSONObject();
            stationONConnection.put(lineNumber, connectionMetro.getLine());
            stationONConnection.put(lineName, connectionMetro.getName());
            connection.add(stationONConnection);
          }
          connections.add(connection);
        }
      }
    }
    String connectionsName = "connections";
    map.put(connectionsName, connections);

    String numberLine = "number";
    String nameLine = "name";
    for (LineMosMetro lineMetro : mosMetro) {
      line = new JSONObject();
      line.put(numberLine,lineMetro.getNumber());
      line.put(nameLine,lineMetro.getName());
      lines.add(line);
    }
    String linesName = "lines";
    map.put(linesName, lines);

    LocalDateTime l = LocalDateTime.now();
    String curtim = l.getYear() + "-" +
            l.getMonthValue() + "-" +
            l.getDayOfMonth() + "_" +
            l.getHour() + "-" +
            l.getMinute() + "-" +
            l.getSecond();
    File work = new File("work");
    if(!work.exists()) {work.mkdir();}
    String path = "work/map_" + curtim +".json";
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    try { mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), map);
    } catch (Exception e) { e.printStackTrace(); }
  }

  // создание JSON-файла  из карты-дерева метро:
  // Список:
  //   - общий список станций с характеристиками
  public void createJsonStation(ArrayList<LineMosMetro> mosMetro) {
    JSONObject map = new JSONObject();
    JSONArray stations = new JSONArray();
    JSONObject station;// = new JSONObject();
    ArrayList<StationMosMetro> stationList;
    String stationName = "name";
    String lineName = "line";
    String createDate = "date";
    String depth = "depth";
    for (LineMosMetro lineMetro : mosMetro) {
      stationList = lineMetro.getStations();
      for (StationMosMetro stationMetro : stationList) {
        station = new JSONObject();
        station.put(stationName, stationMetro.getName());
        station.put(lineName, stationMetro.getLine());
        if ((stationMetro.getDate() != null)) {
          station.put(createDate,stationMetro.getDate());
        }
        if ((stationMetro.getDepth() != null)&&(!stationMetro.getDepth().equalsIgnoreCase("?"))) {
          station.put(depth, stationMetro.getDepth());
        }
        stations.add(station);
      }
    }
    map.put("stations", stations);

    LocalDateTime l = LocalDateTime.now();
    String curtim = l.getYear() + "-" +
            l.getMonthValue() + "-" +
            l.getDayOfMonth() + "_" +
            l.getHour() + "-" +
            l.getMinute() + "-" +
            l.getSecond();
    File work = new File("work");
    if(!work.exists()) {work.mkdir();}
    String path = "work/stations_" + curtim +".json";
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    try { //mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), map);
      mapper.writeValue(new File(path), map);

    } catch (Exception e) { e.printStackTrace(); }
  }

  public ArrayList<File> getFiles(File file, String type) {
    files.clear();
    if (file.exists()) {
      list(file,type);
      return new ArrayList<>(files);
    } else { out.println("File Not Found");
      return null; }            //return new ArrayList<>();
  }
  public String parseFile(File file) {
    StringBuilder builder  = new StringBuilder();
    List<String> strings;
    try {
      strings = Files.readAllLines(Paths.get(file.getPath()));
      strings.forEach(line -> builder.append(line.concat(System.lineSeparator())));
    } catch (Exception e) { e.printStackTrace(); }
    return builder.toString();
  }

  public ArrayList<LineMosMetro> getTreeFromHtml(Document document, ArrayList<String[]> date, ArrayList<String[]> depth) {
    ArrayList<LineMosMetro> mosMetro = new ArrayList<>();
    LineMosMetro line;
    StationMosMetro station;
    ConnectionMosMetro connection;
    Elements lines = document.select("div.js-toggle-depend");
    Elements stations = document.select("div.js-depend");
    int linesCount = lines.size();
    int stationsCount = stations.size();
    int stationCount;
    int count;
    String lineNumber;
    String lineName;
    String stationNumber = "";
    String stationName = "";
    String stationDate = "";
    String stationDepth = "";
    String connectionNumber = "";
    String connectionName = "";
    String[] lineArray;

    ArrayList<ConnectionMosMetro> connectionList = null;
    boolean hasConnection = false;
    List<Attribute> attributeList;
    String line3 = "Арбатско-Покровская линия";
    String line4 = "Филевская линия";
    String station2 = "Арбатская";
    String station3 = "Смоленская";
    String depth1 = "-41"; // глубина залегания станции "Арбатская" "Арбатско-Покровская линии"
    String depth2 = "-8"; // глубина залегания станции "Арбатская" "Филевская линии"
    String depth3 = "-50"; // глубина залегания станции "Смоленская" "Арбатско-Покровская линии"
    String depth4 = "-8"; // глубина залегания станции "Смоленская" "Филевская линии"
    String date1 = "05.04.1953";  // дата открытия станции "Арбатская" "Арбатско-Покровская линии"
    String date2 = "15.05.1935";  // дата открытия станции "Арбатская" "Филевская линии"
    String date3 = "05.04.1953";  // дата открытия станции "Смоленская" "Арбатско-Покровская линии"
    String date4 = "15.05.1935";  // дата открытия станции "Смоленская" "Филевская линии"

    if (linesCount == stationsCount) {
      for (int i = 0; i < linesCount; i++) {
        // список линий метро:
        //  - номер
        //  - название
        lineNumber = lines.get(i).child(0).attribute("data-line").getValue();
        lineName = lines.get(i).child(0).text();
        line = new LineMosMetro(lineNumber, lineName);
        mosMetro.add(line);
        stationCount = stations.get(i).child(0).children().size();
        for (int j = 0; j < stationCount; j++) {
          // список станций на линии
          count = stations.get(i).child(0).child(j).children().size();
          hasConnection = false;
          connectionList = new ArrayList<>();
          for (int k = 0; k < count; k++) {
            // список параметров у станции:
            //  - порядковый номер
            //  - название
            //  - наличие пересадок:
            //    - номер линии
            //    - название станции
            attributeList = stations.get(i).child(0).child(j).child(k).attributes().asList();
            for (Attribute attribute : attributeList) {
              // список атрибутов для каждого параметра
              switch (attribute.getValue()) {
                case "num" ->  stationNumber = stations.get(i).child(0).child(j).child(k).text();
                case "name" -> stationName = stations.get(i).child(0).child(j).child(k).text();
                default -> {
                  if (attribute.getKey().equalsIgnoreCase("class")) {
                    lineArray = attribute.getValue().split(" ");
                    if(lineArray.length != 2) {
                      out.println("ERROR 1");
                    } else {
                      if (lineArray[0].equalsIgnoreCase("t-icon-metroln")) {
                        connectionNumber = lineArray[1];
                        hasConnection = true;
                      } else {
                        out.println("номер линии - " + i);
                        out.println("номер станции - " + j);
                        out.println("параметр станции - " + k);
                        out.println("ERROR 2"); }
                    }
                  } else {
                    if (attribute.getKey().equalsIgnoreCase("title")) {
                      connectionName = attribute.getValue();
                      hasConnection = true;
                      if (hasConnection) connectionList.add(new ConnectionMosMetro(connectionNumber, connectionName));
                    } else { out.println("ERROR 3"); }
                  }
                }
              }
            }
          }
          stationDate = null;
          stationDepth = null;
          if (lineName.equalsIgnoreCase(line3)) {
            for (String[] csv : date) {
              if (csv[0].equalsIgnoreCase(stationName)) {
                if (csv[0].equalsIgnoreCase(station2)) {
                  stationDate = date1;
                } else if (csv[0].equalsIgnoreCase(station3)) {
                  stationDate = date3;
                } else {
                  stationDate = csv[1];
                }
              }
            }
            for (String[] json : depth) {
              if (json[0].equalsIgnoreCase(stationName)) {
                if (json[0].equalsIgnoreCase(station2)) {
                  stationDepth = depth1;
                } else if (json[0].equalsIgnoreCase(station3)) {
                  stationDepth = depth3;
                } else {
                  stationDepth = json[1];
                }
              }
            }
          } else if (lineName.equalsIgnoreCase(line4)) {
            for (String[] csv : date) {
              if (csv[0].equalsIgnoreCase(stationName)) {
                if (csv[0].equalsIgnoreCase(station2)) {
                  stationDate = date2;
                } else if (csv[0].equalsIgnoreCase(station3)) {
                  stationDate = date4;
                } else {
                  stationDate = csv[1];
                }
              }
            }
            for (String[] json : depth) {
              if (json[0].equalsIgnoreCase(stationName)) {
                if (json[0].equalsIgnoreCase(station2)) {
                  stationDepth = depth2;
                } else if (json[0].equalsIgnoreCase(station3)) {
                  stationDepth = depth4;
                } else {
                  stationDepth = json[1];
                  // out.println("глубина станции л4 ст3 - " + json[1]);
                }
              }
            }
          } else {
            for (String[] csv : date) {
              if (csv[0].equalsIgnoreCase(stationName)) {
                stationDate = csv[1];
              }
            }
            for(String[] json : depth) {
              if (json[0].equalsIgnoreCase(stationName)) {
                stationDepth = json[1];
              }
            }
          }
          if (!hasConnection) { connectionList = null; }
          station = new StationMosMetro(stationNumber, stationName, lineName, stationDate, stationDepth, hasConnection, connectionList);
          line.addStation(station);
        }
      }
    } else { out.println("ERROR 4"); }
    return mosMetro;
  }



  public ArrayList<String[]> getArrayFromJson(File file) {
    ArrayList<String[]> stations = new ArrayList<>();
    ArrayList<ArrayList<String>> stationsString = new ArrayList<>();
    JSONParser parser = new JSONParser();
    JSONObject stationJSON;
    ArrayList<String> stationList = new ArrayList<>();
    String[] station;
    String station_name;
    String depth;
    if (file.exists()&&file.isFile()) {
      //out.println("файл существует");
      try {
        JSONArray root = (JSONArray) parser.parse(parseFile(file));
        for (Object obj1 : root) {
          stationJSON = (JSONObject) obj1;
          for(Object obj2 : stationJSON.keySet()) {
            //if (obj2.equals("station_name")) {
            //} else if (obj2.equals("depth")) {
            //}
            stationList.add((String) stationJSON.get(obj2));
          }
          station = new String[stationList.size()];
          for (int i = 0; i < stationList.size(); i++) {
            station[i] = stationList.get(i);
          }
          //station =  (String[]) stationList.toArray();
          stations.add(station);
          stationList.clear();
        }
        return new ArrayList<>(stations);
      } catch (Exception e) {
        out.println("ИСКЛЮЧЕНИЕ");
        e.printStackTrace();
        return null;
      }
    } else { out.println("файла НЕТ"); return null; }
  }
  public ArrayList<String[]> getArrayFromJson(ArrayList<File> files) {
    ArrayList<ArrayList<String[]>> arrayJsonFiles = new ArrayList<>();
    ArrayList<String[]> stations = new ArrayList<>();
    boolean stationExist;
    for(File file : files) {
      arrayJsonFiles.add(getArrayFromJson(file));
    }
    for (ArrayList<String[]> jsonFile : arrayJsonFiles) {
      for (String[] sourseStation : jsonFile) {
        if (stations.isEmpty()) {
          stations.addAll(jsonFile);
        } else {
          stationExist = false;
          for(String[] station : stations) {
            if (sourseStation[0].equalsIgnoreCase(station[0])) {
              if (station[1].equalsIgnoreCase(sourseStation[1])) {
                stationExist = true;
              }
            }
          }
          if(!stationExist) stations.add(sourseStation);
        }
      }
    }
    return new ArrayList<>(stations);
  }
  //парсинг списка CSV-файлов в список станций
  public ArrayList<String[]> getArrayFromCsv(ArrayList<File> files) {
    ArrayList<ArrayList<String[]>> arrayCsvFiles = new ArrayList<>();
    ArrayList<String[]> stations = new ArrayList<>();
    Iterator<String[]> iterator;
    boolean stationExist;
    for (File file : files) {
      arrayCsvFiles.add(getArrayFromCsv(file));
    }
    for (ArrayList<String[]> csvFile : arrayCsvFiles) {
      for (String[] sourseStation : csvFile) {
        if (stations.isEmpty()) {
          stations.addAll(csvFile);
        } else {
        //  iterator = stations.iterator();
          //while (iterator.hasNext()) {
            //out.println("вход в цикл");
          //  station = iterator.next();
          stationExist = false;
          for(String[] station : stations) {
           // out.println(sourseStation[0]);
            if (sourseStation[0].equalsIgnoreCase(station[0])) {
          //if (station[0].equalsIgnoreCase(sourseStation[0])) {
            //out.println(station[0]);
              if (station[1].equalsIgnoreCase(sourseStation[1])) {
                stationExist = true;
              }
            }
          }
          if(!stationExist) stations.add(sourseStation);
        }
      }
    }
   // out.println("длина списка файлов - " + files.size());
 //   out.println("длина исходного массива - " + arrayCsvFiles.size());
  //  out.println("длина массива №"+ 0 + " - " + arrayCsvFiles.get(0).size());
 //   out.println("длина массива №"+ 1 + " - " + arrayCsvFiles.get(1).size());
 //   out.println("длина массива №"+ 2 + " - " + arrayCsvFiles.get(2).size());
 //   out.println("длина конечного массива - " + stations.size());
    iterator = stations.iterator();

    while (iterator.hasNext()) {
      if(iterator.next()[0].equalsIgnoreCase("name")) {
        iterator.remove();
      }
    }
    return new ArrayList<>(stations);
  }
  public ArrayList<String[]> getArrayFromCsv(File file) {
    if (file.exists()&&file.isFile()) {
      try {
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        ArrayList<String[]> stations = new ArrayList<>();
        for (String line : list) {
          stations.add(line.split(","));
        }
        return new ArrayList<>(stations);
      } catch (Exception e) {
        return null;
        //e.printStackTrace();
      }
    }
      return null;
  }
  public ArrayList<String[]> csvArray(ArrayList<ArrayList<String[]>> array) {
    return null;
  }
  private void list(File file,  String key) {
    if (file.isDirectory()) {
        //     System.out.println(" dir: " + file.getName());
      String[] list = file.list();
      if (list != null) {
        for (String line : list) {
                //File newFile = new File(file, line);
          list(new File(file, line), key);
        }
      } else { System.out.println("КАТАЛОГ ПУСТ"); }
    } else if (file.isFile()) {
      if (file.getName().lastIndexOf(key) > 0) {
        files.add(file);
            //System.out.println(" file: " + file.getPath() + " size: " + file.length());
      }
    } else { System.out.println("неведома зверушка"); }
  }
}