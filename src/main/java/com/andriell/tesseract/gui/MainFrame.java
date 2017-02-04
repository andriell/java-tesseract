package com.andriell.tesseract.gui;

import com.andriell.tesseract.figure.Tesseract;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JSlider slider02;
    private JSlider slider12;
    private JSlider slider30;
    private JSlider slider31;
    private JSlider slider32;
    private JButton saveButton;
    private JCheckBox autoCheckBox;
    private JLabel value01;
    private JLabel value02;
    private JLabel value12;
    private JLabel value30;
    private JLabel value32;
    private JLabel value31;
    private JTextArea textAreaPoints;
    private JButton resetButton;
    private JSlider sliderX;
    private JLabel valueX;
    private JLabel valueY;
    private JLabel valueZ;
    private JLabel valueW;
    private JSlider sliderY;
    private JSlider sliderZ;
    private JSlider sliderW;

    Tesseract tesseract;

    public MainFrame() {

    }

    public void show() {
        frame = new JFrame("Real Tesseract");
        tesseract = new Tesseract();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1252, 875);
        frame.setContentPane(rootPanel);
        centerPane.add(new ImagePane(tesseract));

        ChangeListener changeListenerRotation = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                update();
            }
        };
        slider01.addChangeListener(changeListenerRotation);
        slider02.addChangeListener(changeListenerRotation);
        slider12.addChangeListener(changeListenerRotation);
        slider30.addChangeListener(changeListenerRotation);
        slider31.addChangeListener(changeListenerRotation);
        slider32.addChangeListener(changeListenerRotation);

        ChangeListener changeListenerTranslation = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                update();
            }
        };
        sliderX.addChangeListener(changeListenerTranslation);
        sliderY.addChangeListener(changeListenerTranslation);
        sliderZ.addChangeListener(changeListenerTranslation);
        sliderW.addChangeListener(changeListenerTranslation);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                slider01.setValue(0);
                slider02.setValue(0);
                slider12.setValue(0);
                slider30.setValue(0);
                slider31.setValue(0);
                slider32.setValue(0);
                sliderX.setValue(0);
                sliderY.setValue(0);
                sliderZ.setValue(0);
                sliderW.setValue(0);
                update();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tesseract.repaint();
                    BufferedImage bi = tesseract.getImage();
                    String fileName = String.format(
                            "tesseract_%03d_%03d_%03d_%03d_%03d_%03d.png",
                            slider01.getValue(),
                            slider02.getValue(),
                            slider12.getValue(),
                            slider30.getValue(),
                            slider31.getValue(),
                            slider32.getValue()
                    );
                    File outputFile = new File(fileName);
                    ImageIO.write(bi, "png", outputFile);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        update();

        frame.setVisible(true);
    }

    private void update() {
        int value;
        value = slider01.getValue();
        value01.setText(Integer.toString(value));
        tesseract.rotation(0, 1, value);

        value = slider02.getValue();
        value02.setText(Integer.toString(value));
        tesseract.rotation(0, 2, value);

        value = slider12.getValue();
        value12.setText(Integer.toString(value));
        tesseract.rotation(1, 2, value);

        value = slider30.getValue();
        value30.setText(Integer.toString(value));
        tesseract.rotation(3, 0, value);

        value = slider31.getValue();
        value31.setText(Integer.toString(value));
        tesseract.rotation(3, 1, value);

        value = slider32.getValue();
        value32.setText(Integer.toString(value));
        tesseract.rotation(3, 2, value);

        value = sliderX.getValue();
        valueX.setText(Integer.toString(value));
        tesseract.translation(0, ((double) value) / 100);

        value = sliderY.getValue();
        valueY.setText(Integer.toString(value));
        tesseract.translation(1, ((double) value) / 100);

        value = sliderZ.getValue();
        valueZ.setText(Integer.toString(value));
        tesseract.translation(2, ((double) value) / 100);

        value = sliderW.getValue();
        valueW.setText(Integer.toString(value));
        tesseract.translation(3, ((double) value) / 100);

        tesseract.repaint();
        textAreaPoints.setText(tesseract.printVertices());

        centerPane.repaint();
    }
}
