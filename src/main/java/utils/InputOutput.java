package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                System.err.format("%7.1f ", matrix[i][j]);
            }
            System.err.format(" |%7.1f\n", vector[i]);
        }
        System.err.println("]");
    }

    public static double[][] readMatrixFromFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        List<String> lines = new ArrayList<String>();
        int rows = 0;
        int columns = 0;
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
            ++rows;
        }
        columns = lines.get(0).split("\\s+").length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = Double.parseDouble(lines.get(i).split("\\s+")[j]);
            }
        }
        return result;
    }
}
