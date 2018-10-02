package implementations;

import interfaces.Method;

import static utils.Actions.copyMatrix;
import static utils.Actions.copyVector;
import static utils.InputOutput.printMatrix;
import static utils.InputOutput.printVector;

public class OrthogonalizationMethod implements Method {

    public double[] calculate(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[][] a = copyMatrix(matrix, n + 1, n + 1);
        double[] b = copyVector(vector);
        double[][] rr = new double[n][n];
        double[][] ss = new double[n][n];
        double[] ppn = new double[n];
        double[] x = new double[n];
        double s2 = 0;
        double s = 0;
        printMatrix(a);
        printVector(b);
        System.err.println(n);
        for (int i = 0; i < n; i++) {
            a[i][n] = -b[i];
        }
        a[n][n] = 1;
        printMatrix(a);
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    rr[i][j] = a[i][j];
                }
            } else {
                System.err.println("in else");
                for (int l = 0; l < i; l++) {
                    s2 = 0;
                    for (int z = 0; z < n; z++) {
                        s2 += a[i][z] * ss[l][z];
                    }
                    System.err.println("s2=" + s2);
                    System.err.println("before p loop");
                    for (int p = 0; p < n; p++) {
                        System.err.println("in p loop");
                        if (l == 0) {
                            ppn[p] = s2 * ss[l][p];
                            System.err.println("ppn[" + p + "]=" + ppn[p]);
                        } else {
                            ppn[p] += s2 * ss[l][p];
                            System.err.println("ppn[" + p + "]=" + ppn[p]);
                        }
                    }
                }
                System.err.println("ppn:");
                printVector(ppn);
                for (int j = 0; j < n; j++) {
                    rr[i][j] = a[i][j] - ppn[j];
                }
            }
            s = 0;
            for (int p = 0; p < n; p++) {
                s += Math.pow(rr[i][p], 2);
            }
            for (int j = 0; j < n; j++) {
                ss[i][j] = rr[i][j] / Math.sqrt(s);
            }
        }
        System.err.println("rr:");
        printMatrix(rr);
        for (int i = 0; i < n; i++) {
            x[i] = rr[n-1][i] / rr[n-1][n-1];
        }

        return x;
    }
}
// for i := 1 to n + 1 do
//           begin
//             A[i, n+1] := -b[i];
//             if (i <= n) then
//               A[n + 1, i] := 0
//             else
//               A[n + 1, i] := 1;
//           end;
//
//         for i := 1 to n + 1 do
//           begin
//             if (i = 1) then
//               begin
//                 for j := 1 to n + 1 do
//                   rr[i, j] := A[i, j];
//               end
//             else
//               begin
//                 for l := 1 to i-1 do
//                   begin
//                     S2 := 0;
//                     for z := 1 to n + 1 do
//                       S2 := S2 + (A[i, z] * ss[l, z]);
//                     for p := 1 to n + 1 do
//                       begin
//                         if (l = 1) then
//                           ppn[p] := (S2 * ss[l, p])
//                         else
//                           ppn[p] := ppn[p] + (S2 * ss[l, p]);
//                       end;
//                   end;
//
//                 for j := 1 to n+1 do
//                   rr[i, j] := A[i, j] - ppn[j];
//               end;
//               S := 0;
//               for p := 1 to n + 1 do
//                 S := S + sqr(rr[i, p]);
//               for j := 1 to n+1 do
//                 ss[i, j] := rr[i, j] / sqrt(S);
//           end;
//
//         for i := 1 to n do
//           X[i] := rr[n+1, i] / rr[n+1, n+1];