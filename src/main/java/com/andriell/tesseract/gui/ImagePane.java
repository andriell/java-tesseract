package com.andriell.tesseract.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Андрей on 04.02.2017.
 */
public class ImagePane extends JPanel {
    private Image image;

    public ImagePane(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}