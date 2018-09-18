import implementations.GaussMethod;
import interfaces.Method;

import static utils.InputOutput.printMatrix;

public class Lab_1 {

    private static final int N = 3;

    public static void main(String[] args) {

        double[][] a = {{0, 1, -6, -4}, {3, -1, -6, -4}, {2, 3, 9, 2}, {3, 2, 3, 8}};
        double[] b = {6, 2, 6, -7};
        double[][] i = {{1, 2}, {3, 4}};
        double[][] a1 = {{1, 2, -1, -2}, {3, 8, 0, -4}, {2, 2, -4, -3}, {3, 8, -1, -6}};

        Method method = new GaussMethod();
        System.err.println("Input matrix: ");
        printMatrix(a1);
//        method.calculate(a, b);
        printMatrix(method.invert(a1));

    }
}
