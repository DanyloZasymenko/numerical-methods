package implementations;

import exceptions.GaussMethodException;
import interfaces.MatrixMethod;

import static utils.Actions.*;

public class GaussMethod implements MatrixMethod {

    public double[] calculate(double[][] matrix, double[] vector) throws GaussMethodException {
        double[][] a = copyMatrix(matrix);
        double[] b = copyVector(vector);
        int n = a.length;
        double[][] m = new double[n][n];
        double[] x = new double[n];
        for (int k = 0; k < n - 1; k++) {
            transform(a, b, k);
            for (int i = k + 1; i < n; i++) {
                m[i][k] = -(a[i][k] / a[k][k]);
                b[i] = b[i] + m[i][k] * b[k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] = a[i][j] + m[i][k] * a[k][j];
                }
                a[i][k] = 0;
            }
        }
        if (a[n - 1][n - 1] == 0) {
            if (b[n - 1] == 0) {
                x[n - 1] = 0;
            } else {
                throw new GaussMethodException("Max item is zero");
            }
        } else {
            x[n - 1] = b[n - 1] / a[n - 1][n - 1];
        }
        double sum = 0;
        for (int k = n - 2; k >= 0; k--) {
            for (int j = n - 1; j > k; j--) {
                sum += (a[k][j] * x[j]);
            }
            if (a[k][k] == 0) {
                if ((b[k] - sum) == 0) {
                    x[k] = 0;
                } else {
                    throw new GaussMethodException("Max item is zero");
                }
            } else {
                x[k] = (b[k] - sum) / a[k][k];
            }
            sum = 0;
        }
        return x;
    }

    public double[][] invert(double[][] a) throws GaussMethodException {
        int n = a.length;
        double[][] e = generateIdentityMatrix(n, n);
        double[][] x = new double[n][n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b = calculate(a, e[i]);
            for (int j = 0; j < n; j++) {
                x[j][i] = b[j];
            }
        }
        return x;
    }

    public double findDeterminant(double[][] matrix) {
        double[][] a = copyMatrix(matrix);
        int n = a.length;
        double determinant = 1;
        double[][] m = new double[n][n];
        double[] x = new double[n];
        for (int k = 0; k < n - 1; k++) {
//            transform(a, k);
            for (int i = k + 1; i < n; i++) {
                m[i][k] = -(a[i][k] / a[k][k]);
                for (int j = k + 1; j < n; j++) {
                    a[i][j] = a[i][j] + m[i][k] * a[k][j];
                }
                a[i][k] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            determinant *= a[i][i];
        }
        return determinant;
    }

}
