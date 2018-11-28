package implementations.integrals;

import interfaces.Integral;

public class Integral_6_1 implements Integral {

    private double upperBound = 4.5;
    private double lowerBound = .5;

    public double calculate(double x) {
        return Math.sqrt((2 * Math.pow(x, 3)) + 3) / (Math.pow(x, 2) * (1 + x));
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }
}
