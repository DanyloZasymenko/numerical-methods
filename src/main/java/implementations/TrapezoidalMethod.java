package implementations;

import interfaces.Integral;

public class TrapezoidalMethod {

    public double calculate(double a, double b, int n, Integral integral) {
        double h, s, x;
        h = (b - a) / n;
        s = (integral.calculate(a) + integral.calculate(b)) / 2;
        x = a + h;
        for (int i = 0; i < n; i++) {
            s += integral.calculate(x);
            x += h;
        }
        s *= h;
        return s;
    }
}
