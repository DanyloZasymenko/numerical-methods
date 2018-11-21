package implementations;

import static utils.Actions.copyMatrix;
import static utils.Actions.multiplyMatrix;
import static utils.InputOutput.printMatrix;

public class JacobiRotationMethod {

    public double[] calculate(double[][] matrix, double[][] t1) {
        int n = matrix.length;
        double[][] a = copyMatrix(matrix), b = new double[n][n];
        double[][] t2 = new double[n][n];
        double temp = Math.abs(a[0][0]), p, q, d, r, c, s;
        int tempI = 0;
        int tempJ = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    t2[i][j] = 0;
                }
                else{
                    t2[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != i && Math.abs(a[i][j]) > temp) {
                    temp = Math.abs(a[i][j]);
                    tempI = i;
                    tempJ = j;
                }
            }
        }
        p = 2 * temp;
        q = a[tempI][tempI] - a[tempJ][tempJ];
        d = Math.abs(p * p + q * q);
        if (q != 0) {
            r = Math.abs(q) / (2 * d);
            c = Math.sqrt(0.5 + r);
            if (Math.abs(p) < Math.abs(q)) {
                s = Math.abs(p) * Math.signum(p * q) / (2 * c * d);
            } else {
                s = Math.sqrt(0.5 - r) * Math.signum(p * q);
            }
        } else {
            c = s = Math.sqrt(2) / 2;
        }
        b[tempI][tempI] = c * c * a[tempI][tempI] + s * s * a[tempJ][tempJ] + 2 * c * s * a[tempI][tempJ];
        b[tempJ][tempJ] = s * s * a[tempI][tempI] + c * c * a[tempJ][tempJ] - 2 * c * s * a[tempI][tempJ];
        b[tempI][tempJ] = b[tempJ][tempI] = 0;
        for (int m = 0; m < n; m++) {
            if (m != tempI && m != tempJ) {
                b[tempI][m] = b[m][tempI] = c * a[m][tempI] + s * a[m][tempJ];
                b[tempJ][m] = b[m][tempJ] = -s * a[m][tempI] + c * a[m][tempJ];
                for (int l = 0; l < n; l++) {
                    if (l != tempI && l != tempJ) {
                        b[m][l] = a[m][l];
                    }
                }
            }
        }

        t2[tempI][tempI] = t2[tempJ][tempJ] = c;
        if (tempI > tempJ) {
            t2[tempI][tempJ] = -1 * s;
            t2[tempJ][tempI] = s;
        } else {
            t2[tempI][tempJ] = s;
            t2[tempJ][tempI] = -1 * s;
        }
        t1 = multiplyMatrix(t1, t2);
        double sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sum += b[i][j];
                }
            }
        }
        if (sum != 0) {
            return calculate(b, t1);
        }
        printMatrix(b);
        printMatrix(t1);
        System.err.println("Own values of the matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.err.println("Lambda " + (i + 1) + " = " + b[i][j]);
                }
            }
        }
        System.err.println("Own vectors of matrix:");
        for (int i = 0; i < n; i++) {
            System.err.print("x " + (i + 1) + " : (");
            for (int j = 0; j < n; j++) {
                System.err.print(t1[j][i] + " ");
            }
            System.err.print(")");
        }
        System.err.println("B:");
        printMatrix(b);
        return null;
    }
}
