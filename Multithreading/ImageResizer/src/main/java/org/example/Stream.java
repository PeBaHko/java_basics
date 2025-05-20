package org.example;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Stream implements Runnable {
    private final String srcFolder = "src/main/resources/src";
    private final String dstFolder = "src/main/resources/dst";
    private final Debug debug = new Debug();
    private final File[] files;
    private final int number;
    public Stream(File[] files) {
        this.number = 1;
        this.files = files;
    }
    public Stream(int number, File[] files) {
        this.number = number;
        this.files = files;
    }

    @Override
    public void run() {
       // File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
      //  File[] files = srcDir.listFiles();
        try {
            assert files != null;
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newWidth;
                int newHeight;
                if (image.getHeight()>image.getWidth()) {
                    newWidth = image.getWidth() > 2400 ? image.getWidth() / 8 : 300;
                    newHeight = (int) Math.round(image.getHeight() /
                                                (image.getWidth() / (double) newWidth));
                } else {
                    newHeight = image.getHeight() > 2400 ? image.getHeight() / 8 : 300;
                    newWidth = (int) Math.round(image.getWidth() /
                                               (image.getHeight() / (double) newHeight));
                }
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, newWidth, newHeight);
                String newFileName = file.getName().split("\\.")[0].concat("_")
                                         .concat(debug.getLocalDateTime().concat(".")
                                         .concat(file.getName().split("\\.")[1]));
                File newFile = new File(dstFolder + "/" + newFileName);
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        System.out.println(number + " - Duration: " + (System.currentTimeMillis() - start));
    }
}