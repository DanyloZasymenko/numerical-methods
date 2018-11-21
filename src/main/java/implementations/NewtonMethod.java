package implementations;

import exceptions.NewtonMethodException;
import interfaces.Function;

public class NewtonMethod {

    public double calculate(double a, double b, double e, Function function) throws NewtonMethodException {
        if (function.calculate(a) * function.calculate(b) > 0) {
            throw new NewtonMethodException("Enter input data again!");
        }
        int k = 0;
        double x = .0;
        if (function.firstDerivative(a) * function.secondDerivative(a) > 0) {
            double z = a;
            a = b;
            b = z;
        }
        do {
            x = b;
            b = b - (function.calculate(b) / function.firstDerivative(x));
            k++;
        } while (Math.abs(b - x) > e);
        System.err.println("Number of iterations: " + k);
        return x;
    }

}
