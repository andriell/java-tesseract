package com.andriell.tesseract;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Андрей on 03.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        new Main().go();
    }

    public void go() {
        JFrame frame = new JFrame("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(382, 382);
        frame.setContentPane(new Image(382, 382));

        frame.setVisible(true);
    }

    class Image extends JPanel {
        private Tesseract image;

        public Image(int width, int height) {
            super();
            image = new Tesseract(width, height);
            image.rotation(0, 1, 45);
            image.rotation(1, 2, 45);
            image.rotation(2, 3, 45);
            image.rotation(3, 0, 45);
            image.rotation(3, 1, 45);
            image.rotation(3, 2, 45);
            image.print();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        }
    }
}
