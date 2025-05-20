package org.example;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class Compressor {
    private final String srcFolder = "src/main/resources/src";
    private final String dstFolder = "src/main/resources/dst";
    private final Debug debug = new Debug();
    public Compressor() {
        this.number = 0;
    }
    private final int number;
    public Compressor(int number){
        this.number = number;
    }


    public void scalrCompression() {
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        try {
            assert files != null;
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newWidth = image.getWidth() / 8;
                int newHeight = image.getHeight() / 8;
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, newWidth, newHeight);
            String newFileName = file.getName().split("\\.")[0].concat("_")
                                .concat(debug.getLocalDateTime().concat(".")
                                .concat(file.getName().split("\\.")[1]));
                File newFile = new File(dstFolder + "/" + newFileName);
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    public void defaultCompression() {
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        try {
            assert files != null;
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) { continue; }
                int newWidth = image.getWidth() / 8;
                int newHeight = image.getHeight() / 8;
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;
                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }
                String newFileName = file.getName().split("\\.")[0].concat("_")
                                    .concat(debug.getLocalDateTime().concat(".")
                                    .concat(file.getName().split("\\.")[1]));
                File newFile = new File(dstFolder + "/" + newFileName);
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}