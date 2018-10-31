import exceptions.JacobisMethodException;
import implementations.JacobisMethod;

import static utils.Actions.*;
import static utils.InputOutput.*;

public class Lab_3 {

    private static final String INPUT_FILE_PATH = FILE_PATH + "lab3/matrix1.txt";
    //    private static final String INPUT_FILE_PATH = FILE_PATH + "lab3/matrix2.txt";
//    private static final String INPUT_FILE_PATH = FILE_PATH + "lab3/matrix3.txt";
//    private static final String INPUT_FILE_PATH = FILE_PATH + "lab3/matrix4.txt";
    private static final String OUTPUT_FILE_PATH = FILE_PATH + "lab3/result.txt";

    public static void main(String[] args) {
        try {
            double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);
            JacobisMethod method = new JacobisMethod();
            System.err.println("Input matrix: ");
            double[][] a = getWithoutLastColumn(matrix);
            double[] b = getLastColumn(matrix);
            printMatrix(a, b);
            if (!hasDiagonalAdvantage(a)) {
                throw new JacobisMethodException("The matrix do not have diagonal advantage!");
            } else {
                System.err.println("The matrix has diagonal advantage!");
            }
            System.err.print("Enter E:");
            double e = DOUBLE_SCANNER.nextDouble();
            method.setE(e);
            System.err.print("Do you want to enter x[0](y - yes, n - no):");
            String wantToEnterX = STRING_SCANNER.nextLine();
            double[] x = new double[a.length];
            if (wantToEnterX.equals("y") || wantToEnterX.equals("yes")) {
                for (int i = 0; i < x.length; i++) {
                    System.err.print("x[" + (i + 1) + "]: ");
                    x[i] = DOUBLE_SCANNER.nextDouble();
                }
                method.setX0(x);
            } else if (wantToEnterX.equals("n") || wantToEnterX.equals("no")) {
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[0].length; j++) {
                        if (i == j) {
                            x[i] = a[i][j];
                        }
                    }
                }
                method.setX0(x);
            } else {
                throw new JacobisMethodException("Wrong input!");
            }
            System.err.print("x0: ");
            printVector(x);
            double[] result = method.calculate(a, b);
            System.err.println("Result:");
            printVector(result);
            writeVectorIntoFile(result, OUTPUT_FILE_PATH, false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
