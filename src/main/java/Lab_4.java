import implementations.SPAlgorithm;

import static utils.InputOutput.*;

public class Lab_4 {

//    private static final String INPUT_FILE_PATH = FILE_PATH + "lab4/matrix1.txt";
            private static final String INPUT_FILE_PATH = FILE_PATH + "lab4/matrix2.txt";
//    private static final String INPUT_FILE_PATH = FILE_PATH + "lab4/matrix3.txt";
    private static final String OUTPUT_FILE_PATH = FILE_PATH + "lab4/result.txt";

    public static void main(String[] args) {
        try {
            double[][] matrix = readMatrixFromFile(INPUT_FILE_PATH);
            SPAlgorithm algorithm = new SPAlgorithm();
            System.err.println("Input matrix: ");
            printMatrix(matrix);
            System.err.print("Enter E:");
            double e = DOUBLE_SCANNER.nextDouble();
            System.err.print("Do you want to enter y[0](y - yes, n - no):");
            String wantToEnterY = STRING_SCANNER.nextLine();
            double[] y = new double[matrix.length];
            if (wantToEnterY.equals("y") || wantToEnterY.equals("yes")) {
                for (int i = 0; i < y.length; i++) {
                    System.err.print("y[" + (i + 1) + "]: ");
                    y[i] = DOUBLE_SCANNER.nextDouble();
                }
            } else if (wantToEnterY.equals("n") || wantToEnterY.equals("no")) {
                for (int i = 0; i < y.length; i++) {
                    y[i] = 1;
                }
            }
            System.err.print("y0: ");
            printVector(y);
            double[] result = algorithm.calculate(matrix, y, e);
            System.err.println("Result:");
            printVector(result);
            writeVectorIntoFile(result, OUTPUT_FILE_PATH, false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
