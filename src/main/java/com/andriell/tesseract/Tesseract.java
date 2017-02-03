package com.andriell.tesseract;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Created by Андрей on 03.02.2017.
 */
public class Tesseract extends BufferedImage {
    private double a = 100d;
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

    private int[][] edges = new int[][]{
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0},
            {4, 5},
            {5, 6},
            {6, 7},
            {7, 4},
            {0, 4},
            {1, 5},
            {2, 6},
            {3, 7},

            {8, 9},
            {9, 10},
            {10, 11},
            {11, 8},
            {12, 13},
            {13, 14},
            {14, 15},
            {15, 12},
            {8, 12},
            {9, 13},
            {10, 14},
            {11, 15},

            {0, 8},
            {1, 9},
            {2, 10},
            {3, 11},
            {4, 12},
            {5, 13},
            {6, 14},
            {7, 15},
    };

    private double[][] rotation = new double[][]{
            {0d, 0d, 0d, 0d},
            {0d, 0d, 0d, 0d},
            {0d, 0d, 0d, 0d},
            {0d, 0d, 0d, 0d},
    };

    private int width;
    private int height;

    public Tesseract(int width, int height) {
        super(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        this.width = width;
        this.height = height;
    }

    public void print() {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);

        double[][] verticesNew = new double[vertices.length][];
        for (int i = 0; i < vertices.length; i++) {
            verticesNew[i] = new double[vertices[i].length];
            for (int j = 0; j < vertices[i].length; j++) {
                verticesNew[i][j] = vertices[i][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (rotation[i][j] == 0d) {
                    continue;
                }
                double[][] matrixRotation = matrixRotation(i, j, rotation[i][j]);
                for (int k = 0; k < verticesNew.length; k++) {
                    verticesNew[k] = multiplication(matrixRotation, verticesNew[k]);
                }
            }
        }

        int[] ints = new int[4];
        for (int i = 0; i < edges.length; i++) {
            ints[0] = (int) (a * verticesNew[edges[i][0]][0]) + width / 2;
            ints[1] = (int) (a * verticesNew[edges[i][0]][1]) + height / 2;
            ints[2] = (int) (a * verticesNew[edges[i][1]][0]) + width / 2;
            ints[3] = (int) (a * verticesNew[edges[i][1]][1]) + height / 2;
            System.out.println(Arrays.toString(ints));
            g.drawLine(ints[0], ints[1], ints[2], ints[3]);
        }
    }

    public void rotation(int o1, int o2, double a) {
        if (o1 < 0 || o1 > 3 || o2 < 0 || o2 > 3 || o1 == o2) {
            return;
        }
        rotation[o1][o2] = a;
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
                r[i] += matrix[i][j] * vector[i];
            }
        }
        return r;
    }
}
