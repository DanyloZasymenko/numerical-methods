import implementations.TrapezoidalMethod;
import implementations.integrals.Integral_6_1;
import implementations.integrals.Integral_6_2;
import interfaces.Integral;
import utils.DrawSine;

import javax.swing.*;
import java.awt.*;

import static utils.InputOutput.*;

public class Lab_7 {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;

    private static final int GRID_BOUND = 7;

    private static final Integral INTEGRAL = new Integral_6_1();
//    private static final Integral INTEGRAL = new Integral_6_2();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Trapezoidal Method");
        frame.setSize(WIDTH + 50, HEIGHT + 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new DrawSine(WIDTH, HEIGHT, INTEGRAL)
                .setGridBounds(-GRID_BOUND, GRID_BOUND, -GRID_BOUND, GRID_BOUND), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            TrapezoidalMethod method = new TrapezoidalMethod();
            double a, b;
            System.err.print("Enter n: ");
            int n = INTEGER_SCANNER.nextInt();
            System.err.print("Do you want to enter bounds? (y - yes, n - no): ");
            String want = STRING_SCANNER.nextLine();
            if (want.equals("y") || want.equals("yes")) {
                System.err.print("Enter a: ");
                a = DOUBLE_SCANNER.nextDouble();
                System.err.print("Enter b: ");
                b = DOUBLE_SCANNER.nextDouble();
            } else if (want.equals("n") || want.equals("no")) {
                a = INTEGRAL.getLowerBound();
                b = INTEGRAL.getUpperBound();
            } else {
                throw new Exception("Wrong input!");
            }
            System.err.println("A : " + a + "  B : " + b + "  N : " + n);
            System.err.format("Result: %3.6f", method.calculate(a, b, n, INTEGRAL));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
