import com.fasterxml.jackson.databind.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.zip.*;

import static java.lang.System.out;

public class Debug {
    public Debug() { }

    public void printHtmlTree(ArrayList<LineMosMetro> mosMetro) {
        ArrayList<StationMosMetro> stations;
        for (LineMosMetro line : mosMetro) {
            out.println(line.getNumber() + " - " + line.getName());
            stations = line.getStations();
            for(StationMosMetro station : stations) {
                out.println("\tназвание линии    - " + station.getLine());
                out.println("\tномер станции     - " + station.getNumber());
                out.println("\tназвание станции  - " + station.getName());
                out.print("\tдата создания     - ");
                if(station.getDate() == null) {
                    out.println("неизвестна");
                } else {
                    out.println(station.getDate());
                }
                out.print("\tглубина залегания - ");
                if (station.getDepth() == null) {
                    out.println("неизвестна");
                } else {
                    out.println(station.getDepth());
                }
                out.println("\tналичие пересадок - " + station.isHasConnection());
                if (station.isHasConnection()) {
                    ArrayList<ConnectionMosMetro> connections = station.getConnections();
                    for (ConnectionMosMetro connection : connections) {
                        out.println("\t\tномер линии      - " + connection.getLine());
                        out.println("\t\tназвание станции - " + connection.getName());
                    }
                } else {
                    out.println("\tсписок пересадок  - " + station.getConnections());
                }
                out.println();
            }
        }
    }
    public void printWithIterator() {
        ArrayList<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        out.println("первичное наполнение - " + list.isEmpty());
        out.println("первое наличие следующего - " + iterator.hasNext());
        list.add("добавление 1");
        iterator = list.iterator();
        out.println("второе наполнение - " + list.isEmpty());
        out.println("первое содержимое - " + iterator.next());
        out.println("второе наличие следующего - " + iterator.hasNext());
        list.add("2");
        iterator = list.iterator();

         while (iterator.hasNext()) {
            out.println( "содержимое - " + iterator.next());
        }

    }
    public void printCsvArray(ArrayList<String[]> csvfile) {
        csvfile.forEach(st -> {
            if(st.length != 2) {out.println("Incorrect Station format");
            } else {
                out.println("Название: " + st[0] + System.lineSeparator() +
                            "\tдата создания : " + st[1]);
            }
        });
        out.println("число строк - " + csvfile.size());
    }
    public void printFilesList(ArrayList<File> files) {
        if (files.isEmpty()) {
            out.println("файл не найден");
        } else {
            for (File file :files) {
                out.println("файл - " + file.getPath());
            }
        }
    }
    private static void getFile(File file) {
        Document doc  = Jsoup.parse(parseFile(file));
        out.println(doc.toString());
    }
    public void writeJsonFile () {
        JSONArray rootArray = new JSONArray();
        JSONObject child1 = new JSONObject();
        JSONObject child2 = new JSONObject();
        JSONObject child3 = new JSONObject();
        JSONObject child4 = new JSONObject();
        int[] arr1 = {1, 2, 3, 4, 5};
        JSONArray array1 = new JSONArray();
        //for (int i=0;i<arr1.length;i++) { array1.add(arr1[i]); }
        for(int i : arr1) array1.add(i);
        int[] arr2 = {6, 7, 8, 9, 0};
        JSONArray array2 = new JSONArray();
        //for (int i=0;i<arr2.length;i++) { array2.add(arr2[i]); }
        for (int i : arr2) array2.add(i);
        JSONArray array3 = new JSONArray();
        int[] arr3 = {9, 7, 5, 3, 1};
        //for (int i=0;i<arr3.length;i++) { array3.add(arr3[i]); }
        for (int i : arr3) array3.add(i);
        int[] arr4 = {8, 6, 4, 2, 0};
        JSONArray array4 = new JSONArray();
        //for (int i=0;i<arr4.length;i++) { array4.add(arr4[i]); }
        for (int i : arr4) array4.add(i);
        String string1 = "русский текст";
        String string2 = "english text";
        String string3 = "latin letter";
        String string4 = "кириллица";
        child1.put("array",array1);
        child1.put("string",string1);
        child2.put("array",array2);
        child2.put("string",string2);
        child3.put("array",array3);
        child3.put("string",string3);
        child4.put("array",array4);
        child4.put("string",string4);
        rootArray.add(child1);
        rootArray.add(child2);
        rootArray.add(child3);
        rootArray.add(child4);
        LocalDateTime l = LocalDateTime.now();
        String curtim = l.getYear() + "-" +
                l.getMonthValue() + "-" +
                l.getDayOfMonth() + "_" +
                l.getHour() + "-" +
                l.getMinute() + "-" +
                l.getSecond();
        String path = "work/json_" + curtim +".json";
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
          //try (FileWriter file = new FileWriter(path)) {
        try {// mapper.defaultP
          //  Jsoner
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), rootArray);
            //mapper.writeValue(new File(path), rootArray);
            //          file.write(rootArray.toJSONString());
            //file.write(mapper.writeValueAsString(rootArray.toJSONString()));
            //        System.out.println(rootArray.toJSONString());
            // System.out.println(mapper.writeValueAsString(rootArray.toJSONString()));
            //    file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void parseJsonFile (File file)  {
        JSONParser parser = new JSONParser();
        //JSONArray root;
        try {
            JSONArray root = (JSONArray) parser.parse(parseFile(file));
            //  JSONObject root1 = (JSONObject) parser.parse(parseFile(file));
            //System.out.println(parseFile(file));
            out.println(root.toString());
            out.println("число станций - " + root.size());
            JSONObject station;
            //Set
            for (Object object : root) {
                station = (JSONObject) object;
                //station.keySet().
                //System.out.println(station.keySet());
                for (Object object1 : station.keySet()) {
                    out.print(station.get(object1)+ "  ");
                }
                out.println();
                //System.out.println(s);
            }
            out.println(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JSONObject root = new JSONObject((Map) file);

    }
    private static String parseFile (File file) {
        StringBuilder builder  = new StringBuilder();
        List<String> strings;// = new ArrayList<>();
        //strings.clear();
        try {
            strings = Files.readAllLines(Paths.get(file.getPath()));
            strings.forEach(line -> builder.append(line.concat(System.lineSeparator())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    public void getUrl(Document doc) {
        try { //Jsoup.parse("");
            //Document doc = Jsoup.connect(url).get();
            //Document doc = Jsoup.parse(url);

            //out.println(doc.toString());

            Elements station = doc.select("p.single-station");
            Elements elements1 = doc.select("div.js-toggle-depend");
            Elements elements2 = doc.select("div.js-metro-stations");
            Elements elements3 = doc.select("div.t-text-simple");
            Elements elements4 = doc.select("div.js-depend");
            Elements body = doc.select("body");
            Elements metro = doc.select("div.t-text-simple");
            Elements lines = doc.select("div.js-toggle-depend");
            Elements stations = doc.select("div.js-depend");

           // out.println(lines.size());
            // получение номера и названия линии
            //out.println(lines.get(11).children().get(0));
            //out.println(lines.get(11).children().get(0).attribute("data-line").getValue());
            //out.println(lines.get(11).children().get(0).text());

            out.println(stations.get(16).child(0).child(16).children().size());
            out.println(stations.get(16).child(0).child(16).child(0));
            out.println(stations.get(16).child(0).child(16).child(2).attributes().size());
            List<Attribute> attributeList = stations.get(16).child(0).child(16).child(3).attributes().asList();
            for (Attribute atr : attributeList) {
                if (atr.getKey().equalsIgnoreCase("class")) {
                    //out.println(atr.getValue());
                    String[] lineArray = atr.getValue().split(" ");
                    if(lineArray.length != 2) {
                        out.println("ERROR 1");
                    } else {
                        if (lineArray[0].equalsIgnoreCase ("t-icon-metroln")) {
                            String line = lineArray[1];
                            out.println(line);
                          //  hasConnection = true;
                        } else { out.println("ERROR 2"); }
                    }
                } else if(atr.getKey().equalsIgnoreCase("title")) {
                    out.println(atr.getKey() + "=\"" + atr.getValue() + "\"");
                } else { out.println("ERROR 3"); }
            }
            out.println(stations.get(16).child(0).child(16).child(1));
            out.println(stations.get(16).child(0).child(16).child(2));
            out.println(stations.get(16).child(0).child(16).child(3));
            out.println(stations.get(16).child(0).child(16).child(4));


            //out.println(stations.size());
     //       out.println(stations.get(11).child(0).children());
    //        out.println(stations.get(11).child(0).children().size());
    //        out.println(stations.get(11).child(0).child(0));
    //        out.println(stations.get(11).child(0).child(2).children().size());
     //       out.println(stations.get(11).child(0).child(2).child(2));
    //        out.println(stations.get(11).child(0).child(2).child(2).attributes().asList());
                     //   stations.get(11).child(0).child(2).child(2).attributes().asList().forEach(atr -> out.println(atr.getValue()));
     //       out.println(stations.get(11).child(0).child(2).child(2).attribute("title"). getValue());

   //         out.println(lines.get(3).child(0).text());

        //    out.println(stations.size());
           // out.println(stations.get(15).children().size());
//            out.println(stations.get(15).children().get(0).children().get(9).children().get(2).attributes().asList().get(0).getValue().split(" ")[1]);

            //out.println(lines.get(0));
         //   out.println(stations.get(0));
            //out.println(lines);

      //      out.println(station);
            //out.println(body.get(0).children().get(0));
            //metro.get(0).children().forEach(tag-> out.println(tag.attributes().asList()));
            //out.println(metro.get(0).children().forEach(tag-> tag.attributes().asList()
                //out.println(tag.attributes().forEach(out::println););
           // ));


            //Element element = elements.get(0);
            //out.println(elements1.get(16).child(0).attributes());
      //      out.println("t-text-simple - " + elements3.size());
    //        out.println("его дети - " + elements3.get(0).children().size());
            // System.out.println("размер divov - " + elements1.size());
            // System.out.println(elements1.get(3).toString());
            //out.println("размер  div.js-toggle-depend - " + elements1.size());


         //   out.println("размер div.js-depend - " + elements4.size());
        //    out.println("дети div.js-depend - " + elements4.get(16).children().size());
       //     Element element41 = elements4.get(16).child(0);
        //    out.println(element41.children().size());
        //    out.println(element41);
            //element41.
        //    out.println("data- " + element41.data());
         //   Elements elements5 = doc.select("div.data-line");
         //   out.println(elements5.);
        //    out.println("размер тега div.js-metro-stations - " + elements2.size());


           // out.println(elements2);
           // out.println("полностью линия 15:" + System.lineSeparator() + elements2.get(15));

          //  out.println("полностью линия 16:" + System.lineSeparator() + elements2.get(16));
         //   out.println();
           // out.println("число атрибутов - " + elements2.get(16).attributesSize());
            //out.println("атрибуты - " + elements2.get(16).attributes().asList());
          //  List<Attribute> atr = elements2.get(16).attributes().asList();
          //  atr.forEach(atrib -> out.println(atrib.getKey()));
         //   out.println("значения атрибутов:");
          //  atr.forEach(atrib -> out.println(atrib.getValue()));
          //  out.println("линия 16 - " + elements2.get(16).childrenSize());
            //out.println("станция 21 - " + elements2.get(16).child(20).child(2).attr("title"));


           // out.println(elements2.get(16).attribute("data-line").getValue());
            //for (Attribute atrib : atr) { out.println(atrib.getKey()); }
            //out.println(element41.children());

         //   out.println(elements4.get(16));
            //     Elements elements = doc.select("div.js-depend");

            // System.out.println(element.toString());
            //System.out.println(elements.get(11).toString());
            // System.out.println("размер - " + elements.size());

            //elements2.forEach(System.out::println);
            // System.out.println("hfpvth - " + elements2.size());
            //System.out.println(elements2.get(16).toString());
            // System.out.println(elements2.get(16).children().get(7).toString());

            //elements.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printFiles(File file) {
        List<String> strings = new ArrayList<>();
        //strings.clear();
        try {
            strings = Files.readAllLines(Paths.get(file.getPath()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        for (String string : strings) {
            out.println(string);
        }
        out.println("ФАЙЛ - " + file.getName());
    }
    private static void printFile(File file) {
        //Path p = new P
        // file.getPath().;
        //Files f = new Files()
        StringBuilder strFile = new StringBuilder();
        //strFile.
        out.println("Этот файл - " + file.getPath());
        try (FileInputStream fis = new FileInputStream(file)) {
            // for (int i = 0; i < file.length(); i++) {
            int c;
            while((c = fis.read()) != -1) {
                strFile.append((char) c);
                //System.out.print((char) c);
                //sets.
                //                  Charset dcs = Charset.forName()
            }
            SortedMap<String, Charset> sm =
                    Charset.availableCharsets();
            Set<String> sets = sm.keySet();
            //for (String st : sets) { System.out.println(st); }//Charset.forName().de
            Charset cs = Charset.forName("windows-1251");
            //   Charset.forName(cs.dete)
            out.println("число кодировок - " + sets.size());
            out.println(strFile);
            //fis.close();
        } catch (IOException e) { e.printStackTrace(); }


    }
    private static void readzip () {
        out.println(System.getProperty("user.dir"));
        File file = new File("stations-data");
        if (file.exists()) {
            if(file.isFile()) {
                out.println(file.getAbsolutePath());
            } else {
                out.println("Dir: " +file.getName());
            }
        }
        //  search(file,".json","");
        //search(file, ".csv", "");
        //System.out.println(LocalDate.now());

        //   try (ZipInputStream zin = new ZipInputStream(new FileInputStream("stations-data.zip"))) {
        //     list(zin, ".json");
        //  } catch (IOException ioe) { System.out.println(ioe.getMessage()); }
        /*try (ZipInputStream zin = new ZipInputStream(new FileInputStream("stations-data.zip"))) {
            list(zin, ".csv");
        } catch (IOException ioe) { System.out.println(ioe.getMessage()); }*/
    }
    private static void newfile() {
        LocalDateTime l = LocalDateTime.now();
        String curtim = l.getYear() + "-" +
                l.getMonthValue() + "-" +
                l.getDayOfMonth() + "_" +
                l.getHour() + "-" +
                l.getMinute() + "-" +
                l.getSecond();
        out.println(curtim);
        File file1 = new File("map_" + curtim + ".json");
        if (file1.exists()) {
            out.println("файл уже существует");
        } else {
            try {
                if (file1.createNewFile()) {
                    out.println("новый файл создан");
                    try  (FileOutputStream fos = new FileOutputStream(file1)) {
                        fos.write(curtim.getBytes());
                        fos.write("\nэто мой новый файл".getBytes());
                        fos.flush();
                    } catch (FileNotFoundException fnfe) {
                        out.println(fnfe.getMessage());
                    }
                    //   fos.flush();
                } else {
                    out.println("не удалось создать файл");
                }// (FileOutputStream fos = new FileOutputStream(file1))
            } catch (IOException ioe) {
                out.println(ioe.getMessage());
            } // catch (IOException e) { throw new RuntimeException(e); }

        }
    }
    private static void list(ZipInputStream zin, String tab) throws IOException {
        ZipEntry entry;
        //  String name;
        FileOutputStream newfile;
        while ((entry = zin.getNextEntry()) != null) {
            // entry = zin.getNextEntry();
            //
            /*if (entry.isDirectory()) {
                System.out.print("Dir: ");
            } else {
                System.out.print("file: ");
            }*/
            //   switch (entry.getName().lastIndexOf(tab)) {
            // case -1 -> System.out.println("не тот файл");
            // case 0 -> System.out.println("файл без имени");
            if(entry.getName().lastIndexOf(tab) > 0) {
            /*     newfile = new FileOutputStream(entry.getName());
                 for (int i = 0; i < entry.getSize(); i++) {
                     newfile.write(zin.read());
                 }
            */     out.println(entry.getName());
                //   newfile.flush();
                // newfile.close();
            }
            // }
            zin.closeEntry();
        }
    }
    private static void search(File file, String type, String tab) {
        if(file.exists()) {
            if (file.isDirectory()) {
                //System.out.println(file.getAbsolutePath());
                //  System.out.println(tab + " dir: " + file.getName());
                String[] list = file.list();
                if (list != null) {
                    for (String line : list) {
                        File newFile = new File(file, line);
                        search(newFile, type, tab.concat("-"));
                    }
                } else { out.println("КАТАЛОГ ПУСТ"); }
            } else if (file.isFile()) {
                if (file.getName().lastIndexOf(type) > 0) {
                    out.println("найденный файл - " + file.getPath());
                    //                    System.out.println(tab + " file: " + file.getName() + " size: " + file.length());
                }//System.out.println(tab + " file: " + file.getName() + " size: " + file.length());
                //
            } else { out.println("неведома зверушка");
            }
        } else { out.println("Путь не найден"); }
    }

    private byte isSpecialStation(String name, String line, String[] csv, String[] json) {
        // 0  - обычная станция
        // 1  - станция "Арбатская"  "Арбатско-Покровской линии"
        // 2  - станция "Арбатская"  "Филёвской линии"
        // 3  - станция "Смоленская" "Арбатско-Покровской линии"
        // 4  - станция "Смоленская" "Филёвской линии"
        // -1 - ошибка обработки
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
        if (json.length != 2 ) return -1;
        if (csv.length != 2) return  -1;
        if (line.equalsIgnoreCase(line3)||line.equalsIgnoreCase(line4)) {
            if (name.equalsIgnoreCase(station2)) {
                if(csv[0].equalsIgnoreCase(station2)) {
                    if (json[0].equalsIgnoreCase(station2)) {
                        if (csv[1].equalsIgnoreCase(date1)) {
                            //if () {} //***** *****
                            //work
                            out.println();
                        } else if (csv[1].equalsIgnoreCase(date2)) {
                            out.println();
                            //empty
                        } else { return -1; }
                    } else { return 0; }
                } else { return 0; }
            } else if (name.equalsIgnoreCase(station3)) {
                if (csv[0].equalsIgnoreCase(station3)) {
                    if (json[0].equalsIgnoreCase(station3)) {
                        out.println();
                        //empty
                    } else { return 0; }
                } else { return 0; }
            } else { return 0; }
        } else { return 0; }
        return 0;
    }

}