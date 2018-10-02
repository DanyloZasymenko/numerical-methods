package implementations;

import interfaces.Method;

import static utils.Actions.*;
import static utils.InputOutput.printVector;

public class GaussMethod implements Method {

    public double[] calculate(double[][] matrix, double[] vector) {
        double[][] a = copyMatrix(matrix);
        double[] b = copyVector(vector);
        int n = a.length;
        double[][] m = new double[n][n];
        double[] x = new double[n];
        for (int k = 0; k < n - 1; k++) {
            a = transformMatrix(a, k);
            b = transformVector(a, b, k);
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
                System.err.println("No results");
                return new double[0];
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
                    System.err.println("No results");
                    return new double[0];
                }
            } else {
                x[k] = (b[k] - sum) / a[k][k];
            }
            sum = 0;
        }
        return x;
    }

    public double[][] invert(double[][] a) {
        int n = a.length;
        double[][] e = generateIdentityMatrix(n, n);
        double[][] x = new double[n][n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            System.err.println("e[" + i + "]:");
            printVector(e[i]);
            b = calculate(a, e[i]);
            for (int j = 0; j < n; j++) {
                x[j][i] = b[j];
            }
        }
        return x;
    }

//    public static double[][] invert2(double a[][])
//    {
//        int n = a.length;
//        double x[][] = new double[n][n];
//        double b[][] = new double[n][n];
//        int index[] = new int[n];
//        for (int i=0; i<n; ++i)
//            b[i][i] = 1;
//
//        // Transform the matrix into an upper triangle
//        gaussian(a, index);
//
//        // Update the matrix b[i][j] with the ratios stored
//        for (int i=0; i<n-1; ++i)
//            for (int j=i+1; j<n; ++j)
//                for (int k=0; k<n; ++k)
//                    b[index[j]][k]
//                            -= a[index[j]][i]*b[index[i]][k];
//
//        System.err.println("============b===========");
//        printMatrix(b);
//        System.err.println("=======================");
//
//        // Perform backward substitutions
//        for (int i=0; i<n; ++i)
//        {
//            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
//            for (int j=n-2; j>=0; --j)
//            {
//                x[j][i] = b[index[j]][i];
//                for (int k=j+1; k<n; ++k)
//                {
//                    x[j][i] -= a[index[j]][k]*x[k][i];
//                }
//                x[j][i] /= a[index[j]][j];
//            }
//        }
//        return x;
//    }
//
//// Method to carry out the partial-pivoting Gaussian
//// elimination.  Here index[] stores pivoting order.
//
//    public static void gaussian(double a[][], int index[])
//    {
//        int n = index.length;
//        double c[] = new double[n];
//
//        // Initialize the index
//        for (int i=0; i<n; ++i)
//            index[i] = i;
//
//        // Find the rescaling factors, one from each row
//        for (int i=0; i<n; ++i)
//        {
//            double c1 = 0;
//            for (int j=0; j<n; ++j)
//            {
//                double c0 = Math.abs(a[i][j]);
//                if (c0 > c1) c1 = c0;
//            }
//            c[i] = c1;
//        }
//
//        // Search the pivoting element from each column
//        int k = 0;
//        for (int j=0; j<n-1; ++j)
//        {
//            double pi1 = 0;
//            for (int i=j; i<n; ++i)
//            {
//                double pi0 = Math.abs(a[index[i]][j]);
//                pi0 /= c[index[i]];
//                if (pi0 > pi1)
//                {
//                    pi1 = pi0;
//                    k = i;
//                }
//            }
//
//            // Interchange rows according to the pivoting order
//            int itmp = index[j];
//            index[j] = index[k];
//            index[k] = itmp;
//            for (int i=j+1; i<n; ++i)
//            {
//                double pj = a[index[i]][j]/a[index[j]][j];
//
//                // Record pivoting ratios below the diagonal
//                a[index[i]][j] = pj;
//
//                // Modify other elements accordingly
//                for (int l=j+1; l<n; ++l)
//                    a[index[i]][l] -= pj*a[index[j]][l];
//            }
//        }
//        printMatrix(a);
//        for (int i = 0; i < index.length; i++) {
//            System.err.println(index[i]);
//        }
//    }
}
