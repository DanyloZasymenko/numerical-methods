package utils;

import interfaces.Function;

import javax.swing.*;
import java.awt.*;

public class DrawSine extends JPanel {

    int width;
    int height;
    double x_begin = -7;
    double x_end = 7;
    double x_min = -7;
    double x_max = 7;
    double y_min = -7;
    double y_max = 7;

    Function function;

    public DrawSine(int width, int height, Function function) {
        this.width = width;
        this.height = height;
        this.function = function;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x_step = (int) (width / (Math.abs(x_min) + Math.abs(x_max)));
        int y_step = (int) (height / (Math.abs(y_min) + Math.abs(y_max)));
        int x_zero = (y_min <= 0 && y_max >= 0) ? (int) (Math.abs(y_max) * y_step) : ((y_max < 0) ? 0 : height);
        int y_zero = (x_min <= 0 && x_max >= 0) ? (int) (Math.abs(x_min) * x_step) : ((x_max < 0) ? width : 0);

        g.drawLine(0, x_zero, width, x_zero);
        g.drawLine(y_zero, 0, y_zero, height);

        g.drawLine(y_zero - 12, 20, y_zero, 0);
        g.drawLine(y_zero, 0, y_zero + 12, 20);
        g.drawString("Y", y_zero - 15, 15);

        g.drawLine(width - 20, x_zero - 12, width, x_zero);
        g.drawLine(width, x_zero, width - 20, x_zero + 12);
        g.drawString("X", width - 10, x_zero + 20);

        for (int i = (int) ((Math.abs(x_min % 1)+1) * x_step), j = (int) x_min +1;
             j < x_max;
             i += x_step, j++) {
            g.drawLine(i-(x_step/2), x_zero - 7, i-(x_step/2), x_zero + 7);
            if (j != 0) {
                g.drawLine(i, x_zero - 7, i, x_zero + 7);
            }
            g.drawString(j + "", i - 7, x_zero + 20);
        }
        g.drawLine(width-(x_step/2), x_zero - 7, width-(x_step/2), x_zero + 7);

        for (int i = (int) ((Math.abs(y_max % 1) + 1) * y_step), j = (int) y_max - 1;
             j > y_min;
             i += y_step, j--) {
            if (j == 0)
                continue;
            g.drawLine(y_zero - 7, i, y_zero + 7, i);
            g.drawString(j + "", y_zero - 12, i-1);
        }

        g.setColor(Color.RED);
        Polygon p = new Polygon();
        drawPlot(x_zero, y_zero, p, function);
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }

    private void drawPlot(int x_zero, int y_zero, Polygon p, Function function) {
        for (int x = (int) (x_begin * width); x <= (int) (Math.abs(x_end) * width); x++) {
            p.addPoint((int)Math.ceil(x / 9.35) + y_zero, x_zero - (int) (70 * function.calculate((x / (double) (height)))));
        }
    }

}
