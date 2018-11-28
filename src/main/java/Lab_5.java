import implementations.NewtonMethod;
import implementations.functions.Function_6;
import interfaces.Function;
import utils.DrawSine;

import javax.swing.*;
import java.awt.*;

import static utils.InputOutput.DOUBLE_SCANNER;

public class Lab_5 {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;

    private static final int GRID_BOUND = 7;

    private static final Function FUNCTION = new Function_6();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Newton's Method");
        frame.setSize(WIDTH + 50, HEIGHT + 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new DrawSine(WIDTH, HEIGHT, FUNCTION).setGridBounds(-GRID_BOUND, GRID_BOUND, -GRID_BOUND, GRID_BOUND)
                .setPlotBounds(-GRID_BOUND, GRID_BOUND), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            NewtonMethod method = new NewtonMethod();

            System.err.print("Enter a: ");
            double a = DOUBLE_SCANNER.nextDouble();
            System.err.print("Enter b: ");
            double b = DOUBLE_SCANNER.nextDouble();
            System.err.print("Enter e: ");
            double e = DOUBLE_SCANNER.nextDouble();
            System.err.println("Result: " + method.calculate(a, b, e, FUNCTION));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
