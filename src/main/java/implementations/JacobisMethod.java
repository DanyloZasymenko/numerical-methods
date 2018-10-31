package implementations;

import exceptions.JacobisMethodException;
import interfaces.Method;

import static utils.Actions.copyMatrix;
import static utils.Actions.copyVector;

public class JacobisMethod implements Method {

    private double e;

    private double[] x0;

    public double[] calculate(double[][] matrix, double[] vector) throws JacobisMethodException {
        double[][] a = copyMatrix(matrix);
        double[] b = copyVector(vector);
        int n = a.length;
        int iter = 0;
        double[] x = new double[n];
        double[] temp;
        double sum = 0.0, max, q = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum = 0.0;
            for (int j = 0; j < a[0].length; j++) {
                if (i != j) {
                    sum += Math.abs(a[i][j] / a[i][i]);
                }
            }
            if (sum >= q) {
                q = sum;
            }
        }
        for (int i = 0; i < x0.length; i++) {
            x[i] = x0[i];
        }
        if (q == 1) {
            q = 0.99;
        }
        do {
            iter++;
            temp = copyVector(x);
            for (int i = 0; i < a.length; i++) {
                sum = .0;
                for (int j = 0; j < a[0].length; j++) {
                    if (j != i) {
                        sum += a[i][j] * x[j];
                    }
                }
                x[i] = (-(sum - b[i])) / a[i][i];
            }
            max = Math.abs(x[0] - temp[0]);
            for (int i = 1; i < temp.length; i++) {
                if (Math.abs(x[i] - temp[i]) > max) {
                    max = Math.abs(x[i] - temp[i]);
                }
            }
        } while (max >= ((1 - q) / q) * e);
        System.err.println("Number of iterations: " + iter);
        return x;
    }

    public double getE() {
        return e;
    }

    public JacobisMethod setE(double e) {
        this.e = e;
        return this;
    }

    public double[] getX0() {
        return x0;
    }

    public JacobisMethod setX0(double[] x0) {
        this.x0 = x0;
        return this;
    }
}
