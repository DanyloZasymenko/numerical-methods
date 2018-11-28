import implementations.LagrangePolynomial;
import interfaces.Polynomial;
import utils.DrawSine;

import javax.swing.*;
import java.awt.*;

import static utils.InputOutput.DOUBLE_SCANNER;

public class Lab_6 {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;

    private static final int GRID_BOUND = 30;

    private static final double[] X = {15, 16, 18, 20, 23};
    private static final double[] Y = {7, 9, 4, 12, 1};

    public static void main(String[] args) {

        Polynomial polynomial = new LagrangePolynomial();

        JFrame frame = new JFrame("Lagrange polynomial");
        frame.setSize(WIDTH + 50, HEIGHT + 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new DrawSine(WIDTH, HEIGHT, polynomial, X, Y)
                .setGridBounds(-GRID_BOUND, GRID_BOUND, -GRID_BOUND, GRID_BOUND), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.err.print("Enter x: ");
        double x = DOUBLE_SCANNER.nextDouble();
        System.err.println("Result: " + polynomial.calculate(X, Y, x));
    }
}
