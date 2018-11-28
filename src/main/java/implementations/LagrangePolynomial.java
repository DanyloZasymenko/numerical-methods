package implementations;

import interfaces.Polynomial;

public class LagrangePolynomial implements Polynomial {

    public double calculate(double[] x, double[] y, double number) {
        double upperPart = 1.0;
        double lowerPart = 1.0;
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (j == i) {
                    upperPart *= 1;
                    lowerPart *= 1;
                } else {
                    upperPart *= number - x[j];
                    lowerPart *= x[i] - x[j];
                }
            }
            result += (upperPart / lowerPart) * y[i];
            upperPart = 1.0;
            lowerPart = 1.0;
        }
        return result;
    }
}
