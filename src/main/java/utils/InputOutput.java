package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Actions.writeDigits;

public class InputOutput {

    public static final String FILE_PATH = "src/main/java/files/";

    public static void printVector(double[] vector) {
        System.err.print("[");
        for (double i : vector) {
            System.err.format(" %7.2f", i);
        }
        System.err.println("]");
    }

    public static void printMatrix(double[][] matrix) {
        System.err.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.err.format(" %7.1f", matrix[i][j]);
            }
            System.err.println();
        }
        System.err.println("]");
    }

    public static void printMatrix(double[][] matrix, double[] vector) {
        System.err.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.err.format(" %7.1f", matrix[i][j]);
            }
            System.err.format("| " + writeDigits(vector[i]) + "\n", vector[i]);
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

    public static void writeMatrixIntoFile(double[][] matrix, String path, boolean append) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(path, append));
        for (int i = 0; i < matrix.length; i++) {
            writer.format("|");
            for (int j = 0; j < matrix[0].length; j++) {
                writer.format(" %7.1f", matrix[i][j]);
            }
            writer.format("|\n");
        }
        writer.format("\n");
        writer.close();
    }

    public static void writeVectorIntoFile(double[] vector, String path, boolean append) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(path, append));
        writer.format("|");
        for (double i : vector) {
            writer.format(" %7.1f", i);
        }
        writer.format("|\n");
        writer.close();
    }


}
