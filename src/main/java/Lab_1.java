import implementations.GaussMethod;

import java.io.IOException;

import static utils.Actions.*;
import static utils.InputOutput.*;

public class Lab_1 {

    private static final String INPUT_FILE_PATH = "matrix.txt";
    //    private static final String INPUT_FILE_PATH = "matrix1.txt";
//    private static final String INPUT_FILE_PATH = "matrix2.txt";
//    private static final String INPUT_FILE_PATH = "matrix3.txt";
    private static final String OUTPUT_FILE_PATH = "result.txt";
    private static final Boolean APPEND = false;

    public static void main(String[] args) throws IOException {

        double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);

        GaussMethod method = new GaussMethod();
        System.err.println("Input matrix: ");
        printMatrix(matrix);
        if (matrix.length == matrix[0].length) {
            System.err.println("Determinant of matrix: " + findDeterminant(matrix));
            System.err.println("Inverse matrix: ");
            printMatrix(method.invert(matrix));
            writeMatrixIntoFile(method.invert(matrix), OUTPUT_FILE_PATH, APPEND);
            System.err.println("Result of multiplication: ");
            printMatrix(multiplyMatrix(matrix, method.invert(matrix)));
        } else if (matrix[0].length - matrix.length == 1) {
            double[][] a = getWithoutLastColumn(matrix);
            double[] b = getLastColumn(matrix);
            System.err.println("Calculated: ");
            printVector(method.calculate(a, b));
            System.err.println("Determinant of matrix a: " + findDeterminant(a));
        } else {
            System.err.println("Can not do anything");
        }
    }
}
