import implementations.GaussMethod;

import java.io.FileNotFoundException;

import static utils.Actions.*;
import static utils.InputOutput.*;

public class Lab_1 {

    public static void main(String[] args) throws FileNotFoundException {

        double[][] matrix = readMatrixFromFile("matrix.txt");
//        double[][] matrix = readMatrixFromFile("matrix1.txt");
//        double[][] matrix = readMatrixFromFile("matrix2.txt");
//        double[][] matrix = readMatrixFromFile("matrix3.txt");

        GaussMethod method = new GaussMethod();
        System.err.println("Input matrix: ");
        printMatrix(matrix);
        if (matrix.length == matrix[0].length) {
            System.err.println("Inverse matrix: ");
            printMatrix(method.invert(matrix));
//            System.err.println("Result of multiplication: ");
//            printMatrix(multiplyMatrix(matrix, method.invert(matrix)));
        } else if (matrix[0].length - matrix.length == 1) {
            double[][] a = getWithoutLastColumn(matrix);
            double[] b = getLastColumn(matrix);
            System.err.println("Calculated: ");
            printVector(method.calculate(a, b));
        } else {
            System.err.println("Can not do anything");
        }
    }
}
