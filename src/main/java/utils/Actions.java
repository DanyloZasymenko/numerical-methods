package utils;

public class Actions {

    private static final int N_FORMAT = 8;

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

    public static void transform(double[][] a, double[] b, int k) {
        int maxIndex = k;
        for (int i = k; i < a.length; i++) {
            maxIndex = Math.abs(a[i][k]) >= Math.abs(a[maxIndex][k]) ? i : maxIndex;
        }
        if (maxIndex != k) {
            double temp;
            for (int i = 0; i < a.length; i++) {
                temp = a[k][i];
                a[k][i] = a[maxIndex][i];
                a[maxIndex][i] = temp;
            }
            temp = b[k];
            b[k] = b[maxIndex];
            b[maxIndex] = temp;
        }
    }

    public static boolean transform(double[][] a, int k) {
        int maxIndex = k;
        for (int i = k; i < a.length; i++) {
            maxIndex = Math.abs(a[i][k]) >= Math.abs(a[maxIndex][k]) ? i : maxIndex;
        }
        if (maxIndex != k) {
            double temp;
            for (int i = 0; i < a.length; i++) {
                temp = a[k][i];
                a[k][i] = a[maxIndex][i];
                a[maxIndex][i] = temp;
            }
            return true;
        }
        return false;
    }

    public static double[][] copyMatrix(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        return copyMatrix(a, m, n);
    }

    public static double[][] copyMatrix(double[][] a, int m, int n) {
        double[][] copy = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= a.length || j >= a[0].length) {
                    copy[i][j] = 0;
                } else {
                    copy[i][j] = a[i][j];
                }
            }
        }
        return copy;
    }

    public static double[] copyVector(double[] b) {
        int n = b.length;
        return copyVector(b, n);
    }

    public static double[] copyVector(double[] b, int n) {
        double[] copy = new double[n];
        for (int i = 0; i < n; i++) {
            if (i >= b.length) {
                copy[i] = 0;
            } else {
                copy[i] = b[i];
            }
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
            throw new IllegalArgumentException("a:Rows: " + aColumns + " do not match B:Columns " + bRows + ".");
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

    public static String writeDigits(double number) {
        int before = String.valueOf(number).split("\\.")[0].length();
        int after = String.valueOf(number).split("\\.")[1].length();
        if (before + after > N_FORMAT) {
            after = (before - after < 0) ? N_FORMAT - before : 0;
        } else {
            before = N_FORMAT - after;
        }
        return "%" + before + "." + after + "f";
    }

    public static double findDeterminant(double[][] a) {
        double determinant = 0;
        int n = a.length;
        if (n == 1) {
            determinant = a[0][0];
        } else if (n == 2) {
            determinant = a[0][0] * a[1][1] - a[1][0] * a[0][1];
        } else {
            determinant = 0;
            for (int j1 = 0; j1 < n; j1++) {
                double[][] m = new double[n - 1][];
                for (int k = 0; k < (n - 1); k++) {
                    m[k] = new double[n - 1];
                }
                for (int i = 1; i < n; i++) {
                    int j2 = 0;
                    for (int j = 0; j < n; j++) {
                        if (j == j1)
                            continue;
                        m[i - 1][j2] = a[i][j];
                        j2++;
                    }
                }
                determinant += Math.pow(-1.0, 1.0 + j1 + 1.0) * a[0][j1] * findDeterminant(m);
            }
        }
        return determinant;
    }

    public static boolean isSymmetric(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i])
                    return false;
            }
        }
        return true;
    }
}
