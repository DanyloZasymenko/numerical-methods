package implementations;

import exceptions.GaussMethodException;

import static utils.Actions.*;

public class SPAlgorithm {

    public double[] calculate(double[][] matrix, double[] vector, double e) throws GaussMethodException {
        double[][] a = copyMatrix(matrix);
        double[] y = copyVector(vector);
        double s, norma, t, lambda = e, temp;
        int count = 0;
        double[] x;
        GaussMethod gaussMethod = new GaussMethod();
        if (!isSymmetric(a)) {
            System.err.println("Matrix is not symmetric!");
        }
        s = getScalar(y, y);
        norma = Math.sqrt(s);
        x = divideVectorOnNumber(y, norma);
        count++;
        do {
            temp = lambda;
            y = multiplyMatrix(gaussMethod.invert(a), x);
            s = getScalar(y, y);
            t = getScalar(y, x);
            norma = Math.sqrt(s);
            x = divideVectorOnNumber(y, norma);
            lambda = s / t;
            count++;
        } while (Math.abs(lambda - temp) > e);
        System.err.println("Number of iterations : "+count);
        System.err.println("Lambda : "+lambda);
        return x;
    }

}
