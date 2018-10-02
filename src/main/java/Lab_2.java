import implementations.OrthogonalizationMethod;
import interfaces.Method;

import java.io.FileNotFoundException;

import static utils.Actions.getLastColumn;
import static utils.Actions.getWithoutLastColumn;
import static utils.InputOutput.printMatrix;
import static utils.InputOutput.printVector;
import static utils.InputOutput.readMatrixFromFile;

public class Lab_2 {

    private static final String INPUT_FILE_PATH = "matrix.txt";

    public static void main(String[] args) throws FileNotFoundException {

        double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);

        Method method = new OrthogonalizationMethod();
        System.err.println("Input matrix: ");

        double[][] a = getWithoutLastColumn(matrix);
        double[] b = getLastColumn(matrix);

        printMatrix(a, b);

        printVector(method.calculate(a, b));

    }

}
