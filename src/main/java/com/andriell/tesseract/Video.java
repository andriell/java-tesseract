package com.andriell.tesseract;

import com.andriell.tesseract.figure.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Андрей on 03.02.2017.
 */
public class Video {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        tesseract.setSize(100);
        tesseract.rotation(0, 2, 30);
        tesseract.rotation(1, 2, 30);

        int[] angle = new int[]{0, 15, 30, 45, 60, 75};

        // ffmpeg -framerate 2 -i wx\%03d.png wx.mp4
        // ffmpeg -framerate 2 -i wy\%03d.png wy.mp4
        // ffmpeg -framerate 2 -i wxwy\%03d.png wxwy.mp4
        // ffmpeg -framerate 2 -i all\%03d.png all.mp4
        for (int i = 0; i <= 360; i++) {
            try {
                tesseract.rotation(3, 0, i);
                tesseract.repaint();
                BufferedImage bi = tesseract.getImage();
                String fileName = String.format("import/wx/%03d.png", i);;
                System.out.println(fileName);
                File outputFile = new File(fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(bi, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tesseract.rotation(3, 0, 0);
        for (int i = 0; i <= 360; i++) {
            try {

                tesseract.rotation(3, 1, i);
                tesseract.repaint();
                BufferedImage bi = tesseract.getImage();
                String fileName = String.format("import/wy/%03d.png", i);;
                System.out.println(fileName);
                File outputFile = new File(fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(bi, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tesseract.rotation(3, 1, 0);
        for (int i = 0; i <= 360; i++) {
            try {
                tesseract.rotation(3, 0, i);
                tesseract.rotation(3, 1, i);
                tesseract.repaint();
                BufferedImage bi = tesseract.getImage();

                String fileName = String.format("import/wxwy/%03d.png", i);;
                System.out.println(fileName);
                File outputFile = new File(fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(bi, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i <= 360; i++) {
            try {
                tesseract.rotation(0, 1, i);
                tesseract.rotation(0, 2, i);
                tesseract.rotation(1, 2, i);
                tesseract.rotation(3, 0, i);
                tesseract.rotation(3, 1, i);
                tesseract.rotation(3, 2, i);
                tesseract.repaint();
                BufferedImage bi = tesseract.getImage();

                String fileName = String.format("import/all/%03d.png", i);;
                System.out.println(fileName);
                File outputFile = new File(fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(bi, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
