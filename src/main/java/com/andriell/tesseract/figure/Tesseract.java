package com.andriell.tesseract.figure;

import com.andriell.tesseract.gui.ImagePane;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Андрей on 03.02.2017.
 */
public class Tesseract implements ImagePane.Entity {
    private BufferedImage image;
    private Stroke stroke = new BasicStroke(2);

    private double size = 100d;

    private double[][] vertices = new double[][]{
            {+1d, +1d, +1d, +1d},
            {+1d, -1d, +1d, +1d},
            {-1d, -1d, +1d, +1d},
            {-1d, +1d, +1d, +1d},

            {+1d, +1d, -1d, +1d},
            {+1d, -1d, -1d, +1d},
            {-1d, -1d, -1d, +1d},
            {-1d, +1d, -1d, +1d},

            {+1d, +1d, +1d, -1d},
            {+1d, -1d, +1d, -1d},
            {-1d, -1d, +1d, -1d},
            {-1d, +1d, +1d, -1d},

            {+1d, +1d, -1d, -1d},
            {+1d, -1d, -1d, -1d},
            {-1d, -1d, -1d, -1d},
            {-1d, +1d, -1d, -1d},
    };
    double[][] verticesNew;

    private int[][] edges = new int[][]{
            {0, 1, 0},
            {1, 2, 0},
            {2, 3, 0},
            {3, 0, 0},
            {4, 5, 1},
            {5, 6, 1},
            {6, 7, 1},
            {7, 4, 1},
            {0, 4, 2},
            {1, 5, 2},
            {2, 6, 2},
            {3, 7, 2},

            {8,  9,  3},
            {9,  10, 3},
            {10, 11, 3},
            {11, 8,  3},
            {12, 13, 4},
            {13, 14, 4},
            {14, 15, 4},
            {15, 12, 4},
            {8,  12, 5},
            {9,  13, 5},
            {10, 14, 5},
            {11, 15, 5},

            {0, 8,  6},
            {1, 9,  6},
            {2, 10, 6},
            {3, 11, 6},
            {4, 12, 6},
            {5, 13, 6},
            {6, 14, 6},
            {7, 15, 6},
    };

    private Color[] edgesColor = new Color[] {
            new Color(255,0,0),
            new Color(255,0,0),
            new Color(255,0,0),

            new Color(0,255,0),
            new Color(0,255,0),
            new Color(0,255,0),

            new Color(0,0,255),
    };

    private double[][] rotation = new double[][]{
            {0d},
            {0d, 0d},
            {0d, 0d, 0d},
            {0d, 0d, 0d, 0d},
    };

    private int maxSize;

    public Tesseract() {
        setSize(size);
    }

    public void setSize(double size) {
        this.size = size;
        maxSize = (int) (size * 4 + 10d);
        image = new BufferedImage(maxSize, maxSize, BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void resize(int width, int height) {
        setSize(Math.min(width, height) / 4);
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void repaint() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, maxSize, maxSize);

        g.setColor(Color.black);
        g.setStroke(stroke);

        g.drawString("XY " + Math.round(rotation[1][0]), 5, 15);
        g.drawString("XZ " + Math.round(rotation[2][0]), 5, 30);
        g.drawString("YZ " + Math.round(rotation[2][1]), 5, 45);
        g.drawString("WX " + Math.round(rotation[3][0]), 50, 15);
        g.drawString("WY " + Math.round(rotation[3][1]), 50, 30);
        g.drawString("WZ " + Math.round(rotation[3][2]), 50, 45);

        verticesNew = new double[vertices.length][];
        for (int i = 0; i < vertices.length; i++) {
            verticesNew[i] = new double[vertices[i].length];
            for (int j = 0; j < vertices[i].length; j++) {
                verticesNew[i][j] = vertices[i][j];
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (rotation[i][j] == 0) {
                    continue;
                }
                double[][] matrixRotation = matrixRotation(i, j, rotation[i][j] * Math.PI / 180d);
                for (int k = 0; k < verticesNew.length; k++) {
                    verticesNew[k] = multiplication(matrixRotation, verticesNew[k]);
                }
            }
        }

        int[] ints = new int[4];
        for (int i = 0; i < edges.length; i++) {
            ints[0] = (int) (size * verticesNew[edges[i][0]][0]) + maxSize / 2;
            ints[1] = (int) (size * verticesNew[edges[i][0]][1]) + maxSize / 2;
            ints[2] = (int) (size * verticesNew[edges[i][1]][0]) + maxSize / 2;
            ints[3] = (int) (size * verticesNew[edges[i][1]][1]) + maxSize / 2;
            g.setColor(edgesColor[edges[i][2]]);
            g.drawLine(ints[0], ints[1], ints[2], ints[3]);
        }
    }

    public void rotation(int o1, int o2, double a) {
        if (o1 < 0 || o1 > 3 || o2 < 0 || o2 > 3 || o1 == o2) {
            return;
        }
        if (o1 > o2) {
            rotation[o1][o2] = a;
        } else {
            rotation[o2][o1] = a;
        }

    }

    protected double[][] matrixRotation(int o1, int o2, double a) {
        double[][] r = new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
        };
        if (o1 < 0 || o1 > 3 || o2 < 0 || o2 > 3 || o1 == o2) {
            return r;
        }
        r[o1][o1] = Math.cos(a);
        r[o2][o2] = r[o1][o1];
        r[o2][o1] = Math.sin(a);
        r[o1][o2] = -1 * r[o2][o1];
        return r;
    }

    protected  double[] multiplication(double[][] matrix, double[] vector) {
        double[] r = new double[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                r[i] += matrix[i][j] * vector[j];
            }
        }
        return r;
    }

    public String printVertices() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < verticesNew.length; i++) {
            for (int j = 0; j < verticesNew[i].length; j++) {
                builder.append(String.format("%05d ", Math.round((verticesNew[i][j] + 2d) * size)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
