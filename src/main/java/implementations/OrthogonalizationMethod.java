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
                as[j] = multiplyVectorOnNumber(ss[j], getScalar(a[i], ss[j]));
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

}