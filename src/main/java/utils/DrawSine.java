package utils;

import interfaces.Function;
import interfaces.Integral;
import interfaces.Polynomial;

import javax.swing.*;
import java.awt.*;

public class DrawSine extends JPanel {

    private int width;
    private int height;
    private double x_begin;
    private double x_end;
    private double x_min;
    private double x_max;
    private double y_min;
    private double y_max;

    private double[] listX;
    private double[] listY;

    private Function function;
    private Polynomial polynomial;
    private Integral integral;

    public DrawSine(int width, int height, Function function) {
        this.width = width;
        this.height = height;
        this.function = function;
        x_begin = x_min;
        x_end = x_max;
    }

    public DrawSine(int width, int height, Polynomial polynomial, double[] listX, double[] listY) {
        this.width = width;
        this.height = height;
        this.polynomial = polynomial;
        this.listX = listX;
        this.listY = listY;
        x_begin = listX[0];
        x_end = listX[listX.length - 1];
    }

    public DrawSine(int width, int height, Integral integral) {
        this.width = width;
        this.height = height;
        this.integral = integral;
        x_begin = integral.getLowerBound();
        x_end = integral.getUpperBound();
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

        for (int i = (int) ((Math.abs(x_min % 1) + 1) * x_step), j = (int) x_min + 1;
             j < x_max;
             i += x_step, j++) {
            g.drawLine(i - (x_step / 2), x_zero - 5, i - (x_step / 2), x_zero + 5);
            if (j != 0) {
                g.drawLine(i, x_zero - 7, i, x_zero + 7);
                g.drawString(j + "", i - 7, x_zero + 20);
            }
        }
        g.drawLine(width - (x_step / 2), x_zero - 7, width - (x_step / 2), x_zero + 7);

        for (int i = (int) ((Math.abs(y_max % 1) + 1) * y_step), j = (int) y_max - 1;
             j > y_min;
             i += y_step, j--) {
            g.drawLine(y_zero - 7, i, y_zero + 7, i);
            String string = j + "";
            g.drawString(string, y_zero - string.length() * 8, i + 12);
        }
        g.setColor(Color.RED);
        Polygon p = new Polygon();
        if (function != null) {
            drawPlot(x_zero, y_zero, p, function, x_step, y_step);
        } else if (polynomial != null) {
            drawPlot(x_zero, y_zero, p, polynomial, x_step, y_step);
        } else if (integral != null) {
            drawPlot(x_zero, y_zero, p, integral, x_step, y_step);
        }
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }

    private void drawPlot(int x_zero, int y_zero, Polygon p, Function function, int x_step, int y_step) {
        for (int x = (int) x_begin * x_step + y_zero; x <= (int) (x_end * x_step + y_zero); x++) {
            p.addPoint(x, (int) (x_zero - (y_step * function.calculate((x - y_zero) / (double) x_step))));
        }
    }

    private void drawPlot(int x_zero, int y_zero, Polygon p, Polynomial polynomial, int x_step, int y_step) {
        for (int x = (int) x_begin * x_step + y_zero; x <= (int) (x_end * x_step + y_zero); x++) {
            p.addPoint(x, (int) (x_zero - (y_step * polynomial.calculate(listX, listY, (x - y_zero) / (double) x_step))));
        }
    }

    private void drawPlot(int x_zero, int y_zero, Polygon p, Integral integral, int x_step, int y_step) {
        for (int x = (int) x_begin * x_step + y_zero; x <= (int) (x_end * x_step + y_zero); x++) {
            p.addPoint(x, (int) (x_zero - (y_step * integral.calculate((x - y_zero) / (double) x_step))));
        }
    }

    public DrawSine setGridBounds(double x_min, double x_max, double y_min, double y_max) {
        this.x_min = x_min;
        this.x_max = x_max;
        this.y_min = y_min;
        this.y_max = y_max;
        return this;
    }

    public DrawSine setPlotBounds(double x_begin, double x_end) {
        this.x_begin = x_begin;
        this.x_end = x_end;
        return this;
    }

}
