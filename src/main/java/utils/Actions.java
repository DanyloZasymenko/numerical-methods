package utils;

public class Actions {

    public static double[][] generateIdentityMatrix(int m, int n) {
        double[][] matrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static double[][] transformMatrix(double[][] a, int k) {
        int maxIndex = getMaxIndex(a, k);
        double temp;
        for (int i = 0; i < a.length; i++) {
            temp = a[k][i];
            a[k][i] = a[maxIndex][i];
            a[maxIndex][i] = temp;
        }
        return a;
    }

    public static double[] transformVector(double[][] a, double[] b, int k) {
        int maxIndex = getMaxIndex(a, k);
        double temp;
        temp = b[k];
        b[k] = b[maxIndex];
        b[maxIndex] = temp;
        return b;
    }

    private static int getMaxIndex(double[][] a, int k) {
        int maxIndex = k;
        for (int i = k; i < a.length; i++) {
            maxIndex = Math.abs(a[i][k]) >= Math.abs(a[maxIndex][k]) ? i : maxIndex;
        }
        return maxIndex;
    }


}
