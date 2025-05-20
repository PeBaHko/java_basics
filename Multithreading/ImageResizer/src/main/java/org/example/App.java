package org.example;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class App
{
    public static void main( String[] args ) {
        Debug debug = new Debug();
        int cores = debug.getCountCores();
        String srcFolder = "src/main/resources/src";
        File[] files = new File(srcFolder).listFiles();
        assert files != null;
        List<File[]> list = debug.splitList(files);
        //debug.printListArrayOfFiles(list);
        int number = 0;
        for (File[] fls : list) {
            number++;
            new Thread(new Stream(number,fls)).start();
        }


      //  Stream stream = new Stream(files);
      //  new Thread(stream).start();
      //  Compressor compressor = new Compressor();
      //  compressor.scalrCompression();
      //  compressor.defaultCompression();
    }
}