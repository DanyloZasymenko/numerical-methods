package implementations.integrals;

import interfaces.Integral;

public class Integral_6_2 implements Integral {

    private double upperBound = 1;
    private double lowerBound = .2;

    public double calculate(double x) {
        return (x * Math.exp(1.2 * x)) / Math.pow(1 + 1.2 * x, 2);
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }
}
