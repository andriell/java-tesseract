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
        int[] ints = new int[4];
        for (int i = 0; i < edges.length; i++) {
            ints[0] = (int) (a * vertices[edges[i][0]][0]) + width / 2;
            ints[1] = (int) (a * vertices[edges[i][0]][1]) + height / 2;
            ints[2] = (int) (a * vertices[edges[i][1]][0]) + width / 2;
            ints[3] = (int) (a * vertices[edges[i][1]][1]) + height / 2;
            System.out.println(Arrays.toString(ints));
            g.drawLine(ints[0], ints[1], ints[2], ints[3]);
        }
    }
}
