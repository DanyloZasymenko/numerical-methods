package implementations.functions;

import interfaces.Function;

public class Function_6 implements Function {

    public double calculate(double x) {
        return Math.pow(2, x) - 4 * x;
    }

    public double firstDerivative(double x) {
        return Math.pow(2, x) * Math.log(2) - 4;
    }

    public double secondDerivative(double x) {
        return Math.pow(2, x) * Math.pow(Math.log(2), 2);
    }
}
