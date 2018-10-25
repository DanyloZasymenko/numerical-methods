import exceptions.OrthogonalizationMethodException;
import implementations.OrthogonalizationMethod;

import java.io.IOException;

import static utils.Actions.getLastColumn;
import static utils.Actions.getWithoutLastColumn;
import static utils.InputOutput.*;

public class Lab_2 {

    private static final String INPUT_FILE_PATH = FILE_PATH + "lab2/matrix1.txt";
    //    private static final String INPUT_FILE_PATH = FILE_PATH + "lab2/matrix2.txt";
//    private static final String INPUT_FILE_PATH = FILE_PATH + "lab2/matrix3.txt";
    private static final String OUTPUT_FILE_PATH = FILE_PATH + "lab2/result.txt";

    public static void main(String[] args) throws IOException {

        try {
            double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);
            OrthogonalizationMethod method = new OrthogonalizationMethod();
            System.err.println("Input matrix: ");
            double[][] a = getWithoutLastColumn(matrix);
            double[] b = getLastColumn(matrix);
            printMatrix(a, b);
            printVector(method.calculate(a, b));
            writeVectorIntoFile(method.calculate(a, b), OUTPUT_FILE_PATH, false);
        } catch (OrthogonalizationMethodException e) {
            System.err.println(e.getMessage());
        }
    }

}
