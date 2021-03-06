import exceptions.GaussMethodException;
import implementations.GaussMethod;

import java.io.IOException;

import static utils.Actions.*;
import static utils.InputOutput.*;

public class Lab_1 {

    private static final String INPUT_FILE_PATH = FILE_PATH + "lab1/matrix1.txt";
    //        private static final String INPUT_FILE_PATH = FILE_PATH +"lab1/matrix2.txt";
//        private static final String INPUT_FILE_PATH = FILE_PATH +"lab1/matrix3.txt";
//        private static final String INPUT_FILE_PATH = FILE_PATH +"lab1/matrix4.txt";
    private static final String OUTPUT_FILE_PATH = FILE_PATH + "lab1/result.txt";
    private static final Boolean APPEND = false;

    public static void main(String[] args) throws IOException {

        double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);

        GaussMethod method = new GaussMethod();
        System.err.println("Input matrix: ");
        printMatrix(matrix);
        try {
            if (matrix.length == matrix[0].length) {
//                System.err.println("Determinant of matrix: " + findDeterminant(matrix));
                System.err.println("Determinant of matrix: " + method.findDeterminant(matrix));
                System.err.println("Inverse matrix: ");
                double[][] inverse = method.invert(matrix);
                printMatrix(inverse);
                writeMatrixIntoFile(inverse, OUTPUT_FILE_PATH, APPEND);
                System.err.println("Result of multiplication: ");
                printMatrix(multiplyMatrix(matrix, inverse));
            } else if (matrix[0].length - matrix.length == 1) {
                double[][] a = getWithoutLastColumn(matrix);
                double[] b = getLastColumn(matrix);
//                System.err.println("Determinant of matrix a: " + findDeterminant(a));
                System.err.println("Calculated: ");
                printVector(method.calculate(a, b));
            } else {
                System.err.println("Can not do anything");
            }
        } catch (GaussMethodException e) {
            System.err.println(e.getMessage());
        }
    }
}
