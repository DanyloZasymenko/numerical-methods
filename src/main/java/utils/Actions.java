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
        double[][] copy = copyMatrix(a);
        int maxIndex = getMaxIndex(a, k);
        double temp;
        for (int i = 0; i < a.length; i++) {
            temp = a[k][i];
            copy[k][i] = a[maxIndex][i];
            copy[maxIndex][i] = temp;
        }
        return copy;
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

    public static double[][] copyMatrix(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] copy = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = a[i][j];
            }
        }
        return copy;
    }

    public static double[] copyVector(double[] b) {
        int n = b.length;
        double[] copy = new double[n];
        for (int i = 0; i < n; i++) {
            copy[i] = b[i];
        }
        return copy;
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;
        double[][] result = new double[aRows][bColumns];

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " do not match B:Columns " + bRows + ".");
        }
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                result[i][j] = 0.00000;
            }
        }
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] getWithoutLastColumn(double[][] a) {
        int m = a.length;
        int n = a[0].length - 1;
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j];
            }
        }
        return result;
    }

    public static double[] getLastColumn(double[][] a) {
        int n = a.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = a[i][a[0].length - 1];
        }
        return result;
    }
}
