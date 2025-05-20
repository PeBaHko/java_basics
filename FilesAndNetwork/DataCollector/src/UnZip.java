import java.io.*;
import java.util.*;
import java.util.zip.*;

public class UnZip {
    public UnZip() {
    }
    public List<StringBuilder> getListFiles(String zipName, String key, List<StringBuilder> list) {
        File file = new File(zipName);
        if (file.exists()) {
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file))) {

            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }


            //FileInputStream fis = new FileInputStream(zipName);
        }
        //fis.
        return null;
    }
}
