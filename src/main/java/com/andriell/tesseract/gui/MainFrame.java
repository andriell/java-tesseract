package com.andriell.tesseract.gui;

import com.andriell.tesseract.figure.Tesseract;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private JButton repaintButton;
    private JButton saveButton;
    private JCheckBox autoCheckBox;
    private JLabel value01;
    private JLabel value02;
    private JLabel value12;
    private JLabel value30;
    private JLabel value32;
    private JLabel value31;

    Tesseract tesseract;

    public MainFrame() {

    }

    public void show() {
        frame = new JFrame("Gui");
        tesseract = new Tesseract(100);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 460);
        frame.setContentPane(rootPanel);
        centerPane.add(new ImagePane(tesseract.getImage()));

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (autoCheckBox.isEnabled()) {
                    update();
                }
            }
        };
        slider01.addChangeListener(changeListener);
        slider02.addChangeListener(changeListener);
        slider12.addChangeListener(changeListener);
        slider30.addChangeListener(changeListener);
        slider31.addChangeListener(changeListener);
        slider32.addChangeListener(changeListener);

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

        tesseract.repaint();
        centerPane.repaint();
    }
}
