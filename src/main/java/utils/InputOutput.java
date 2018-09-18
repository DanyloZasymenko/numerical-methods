package utils;

public class InputOutput {

    public static void printVector(double[] vector) {
        System.err.print("[");
        for (double i : vector) {
            System.err.format("%7.1f", i);
        }
        System.err.println("]");
    }

    public static void printMatrix(double[][] matrix) {
        System.err.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.err.format("%7.1f ", matrix[i][j]);
            }
            System.err.println();
        }
        System.err.println("]");
    }

    public static void printMatrix(double[][] matrix, double[] vector) {
        System.err.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.err.format("%3.5f ", matrix[i][j]);
            }
            System.err.format(" |%3.5f\n", vector[i]);
        }
        System.err.println("]");
    }
}
