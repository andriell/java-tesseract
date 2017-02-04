package com.andriell.tesseract.gui;

import com.andriell.tesseract.figure.Tesseract;

import javax.swing.*;

/**
 * Created by Андрей on 04.02.2017.
 */
public class MainFrame {
    JFrame frame;
    private JPanel rootPanel;
    private JPanel centerPane;
    private JPanel rightPane;
    private JPanel topPane;
    private JSlider slider01;
    private JButton repaintButton;
    private JButton saveButton;
    private JSlider slider02;
    private JSlider slider12;
    private JSlider slider30;
    private JSlider slider31;
    private JSlider slider32;

    public MainFrame() {

    }

    public void show() {
        frame = new JFrame("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(740, 460);

        frame.setContentPane(rootPanel);

        Tesseract tesseract;
        tesseract = new Tesseract(100);
        tesseract.rotation(0, 1, 45);
        tesseract.rotation(1, 2, 45);
        tesseract.rotation(2, 3, 45);
        tesseract.rotation(3, 0, 45);
        tesseract.rotation(3, 1, 45);
        tesseract.rotation(3, 2, 45);
        tesseract.repaint();

        centerPane.add(new ImagePane(tesseract.getImage()));

        frame.setVisible(true);
    }
}
