import flanagan.math.Matrix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File file = new File("data.txt");
        FileHelper fh = new FileHelper(file);

        double[] xValues = fh.getXData();
        double[] yValues = fh.getYData();
        int m = xValues.length;

        //sums of xi^1 to xi^2m
        List<Double> sumsX = new ArrayList<>();
        int maxDegree = (m - 1) * 2;
        for (int i = 1; i <= maxDegree; i++) {
            double temp = 0;
            for (int j = 0; j < m; j++) {
                temp += Math.pow(xValues[j], i);
            }
            sumsX.add(temp);
        }
        List<Double> sumsY = new ArrayList<>();
        //sums from xi^0yi to xi^m-1yi
        for (int i = 0; i <= m - 1; i++) {
            double temp = 0;
            for (int j = 0; j < m; j++) {
                temp += Math.pow(xValues[j], i) * yValues[j];
            }
            sumsY.add(temp);
        }
        double[][] dataMatrix = new double[m][m + 1];
        int xIndex = 0;
        int yIndex = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 && j == 0) {
                    //the first element is m
                    dataMatrix[i][j] = m;
                } else if (j == m) {
                    //if we are on the last column, insert Y sum
                    dataMatrix[i][j] = sumsY.get(yIndex);
                    yIndex++;
                } else if (j < m) {
                    //if we are not in the last column and not in the
                    //first element, insert X sum
                    dataMatrix[i][j] = sumsX.get(xIndex);
                    xIndex++;
                }
            }
            //return the index for the next row
            xIndex -= m - 1;
        }
        Matrix matrix = new Matrix(dataMatrix);
        Matrix solvedMatrix = matrix.reducedRowEchelonForm();
        System.out.println("=========================");
        System.out.println("Solved matrix:");
        print2DArray(solvedMatrix.getArrayCopy());
        System.out.println("=========================");
        System.out.println("a_x values:");
        for(int i = 0; i < solvedMatrix.getNrow(); i++){
            System.out.println("a_" + i + "= " + solvedMatrix.getElement(i, solvedMatrix.getNcol()-1));
        }
        System.out.println("=========================");
        System.out.println("Function:");
        for(int i = 0; i < solvedMatrix.getNrow(); i++){
            if (i == 0){
                System.out.print(solvedMatrix.getElement(0, solvedMatrix.getNcol()-1) + " + ");
            }else if(i == solvedMatrix.getNrow()-1){
                System.out.print(solvedMatrix.getElement(i, solvedMatrix.getNcol()-1) + "x^"+i);
            }else{
                System.out.print(solvedMatrix.getElement(i, solvedMatrix.getNcol()-1) + "x^"+i +  " + ");
            }
        }
    }
    public static void print2DArray(double[][] array){
        int m = array.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m + 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
