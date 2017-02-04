package com.andriell.tesseract.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Андрей on 04.02.2017.
 */
public class ImagePane extends JPanel implements ComponentListener {
    private Entity entity;

    public ImagePane(Entity entity) {
        this.entity = entity;
        this.addComponentListener(this);
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(entity.getImage(), 0, 0, this); // see javadoc for more info on the parameters
    }

    @Override
    public void componentResized(ComponentEvent e) {
        entity.resize(e.getComponent().getWidth(), e.getComponent().getHeight());
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public interface Entity {
        void resize(int width, int height);
        Image getImage();
    }
}