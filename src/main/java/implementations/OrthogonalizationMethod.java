package implementations;

import exceptions.OrthogonalizationMethodException;
import interfaces.Method;

import static utils.Actions.*;

public class OrthogonalizationMethod implements Method {

    public double[] calculate(double[][] matrix, double[] vector) throws OrthogonalizationMethodException {
        int n = matrix.length + 1;
        double[][] a = copyMatrix(matrix, n, n);
        double[] b = copyVector(vector, n);
        if (!isSymmetric(a)) {
            throw new OrthogonalizationMethodException("Matrix is not symmetric!");
        }
        double[][] rr = new double[n][n];
        double[][] ss = new double[n][n];
        double[][] as = new double[n][n];
        double[] x = new double[n - 1];
        for (int i = 0; i < n; i++) {
            a[i][n - 1] = -b[i];
        }
        a[n - 1][n - 1] = 1;
        for (int j = 0; j < n; j++) {
            rr[0][j] = a[0][j];
        }
        for (int i = 1; i < n; i++) {
            ss[i - 1] = getS(rr[i - 1]);
            for (int j = 0; j < i; j++) {
                as[j] = getASS(ss[j], getAS(a[i], ss[j]));
                for (int k = 0; k < n; k++) {
                    if (j == 0) {
                        rr[i][k] = a[i][k] - as[j][k];
                    } else {
                        rr[i][k] -= as[j][k];
                    }
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            x[i] = rr[n - 1][i] / rr[n - 1][n - 1];
        }
        return x;
    }

    private double[] getS(double[] r) {
        double[] copy = copyVector(r);
        double euclideanNorm = 0;
        for (double i : copy) {
            euclideanNorm += i * i;
        }
        euclideanNorm = Math.sqrt(euclideanNorm);
        for (int i = 0; i < copy.length; i++) {
            copy[i] /= euclideanNorm;
        }
        return copy;
    }

    private double getAS(double[] a, double[] s) {
        double[] copyA = copyVector(a);
        double[] copyS = copyVector(s);
        double result = 0;
        for (int i = 0; i < copyA.length; i++) {
            result += copyA[i] * copyS[i];
        }
        return result;
    }

    private double[] getASS(double[] a, double s) {
        double[] result = new double[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = a[i] * s;
        }
        return result;
    }
}