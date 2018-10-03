package interfaces;

import exceptions.GaussMethodException;

public interface Method {

    double[] calculate(double[][] matrix, double[] vector) throws Exception;

}
