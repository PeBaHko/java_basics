package org.example;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.out;

public class Debug {
    private final int cores;
    public Debug() {
        this.cores = Runtime.getRuntime().availableProcessors();
    }
    public List<File[]> splitList(File[] files) {
        File[] fls;
        int length = files.length / cores;
        int excess = files.length % cores;
        int beginSrc = 0;
        int lenDst;
        List<File[]> list = new ArrayList<>();
        if (cores >= files.length) {
          for (int i = 0; i < files.length; i++) {
              File[] f = new File[1];
              System.arraycopy(files,i,f,0,1);
              list.add(f);
          }

        } else {
            for (int i = 0; i < cores; i++) {
                if (excess > 0) {
                    lenDst = length+1;
                    excess--;
                } else {
                    lenDst = length;
                }
                fls = new File[lenDst];
                System.arraycopy(files,beginSrc,fls,0,lenDst);
                list.add(fls);
                beginSrc += lenDst;
            }
        }
       // printListArrayOfFiles(list);
        return new ArrayList<>(list);
        }
    public void printListArrayOfFiles(List<File[]> list) {
        int i = 1;
        for (File[] ff : list) {
            out.println(i);i++;
            for (File fff : ff) {
                out.println("\t"+ fff.getName());
            }
        }
    }
    public void print() {
        out.println("число ядер - " + getCountCores());
        out.println("дата и время - " + getLocalDateTime());
        out.println("рабочая папка - " + getCurrentDir());
        String src = "src/main/resources/src";
        File dir = new File(src);
        File file = new File(src + "/01.jpg");
        out.println(dir.exists());
        out.println(file.exists());
        out.println(dir.getAbsolutePath());
        out.println(file.getAbsolutePath());
        String newFileName = file.getName().split("\\.")[0].concat("_")
                                 .concat(getLocalDateTime().concat(".")
                                 .concat(file.getName().split("\\.")[1]));
        out.println(newFileName);
    }
    public String getLocalDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        return (String) (ldt.getYear() + "-" +
                         ldt.getMonthValue() + "-" +
                         ldt.getDayOfMonth() + "_" +
                         ldt.getHour() + "-" +
                         ldt.getMinute() + "-" +
                         ldt.getSecond());
    }
    public int getCountCores() {
        return Runtime.getRuntime().availableProcessors();
    }
    public String getCurrentDir() {
        //Properties properties = System.getProperties();
        return System.getProperty("user.dir");
    }
}