package labmatr7413.avgor.lab_matr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static labmatr7413.avgor.lab_matr.Matrix.*;

public class MethodGauss {

    double [][] matrix;
    double [][] matrix0;
    int width;
    int height;
    double[][] resultMatrix;

    public double[] getX() {
        return x;
    }

    private double[] x;

    public ArrayList<double[][]> getMatrixhHerarchy() {
        return matrixhHerarchy;
    }

    private ArrayList<double[][]> matrixhHerarchy;

    MethodGauss(double [][] sourceMatrix ){
        matrix = setArray(sourceMatrix);
        matrix0 = setArray(matrix);
        width = matrix0[0].length;
        height = matrix0.length;
        this.x = new double[matrix0.length];
        matrixhHerarchy  = new ArrayList<>();
    }
    //    Приведение исходной матрицы к треугольному виду
    public void Gauss(){

        //для получения матрицы треугольного вида как максимум требуется произвести
        //n-1 итерацию, где n - количество строк в исходной матрице. Пример:
        // qwe|a      qwe|a      qwe|a
        // rty|s->(1) 0ty|s->(2) 0ty|s  , итого 2 итерации (переменные приведены для
        // uio|d      0io|d      00o|d    наглядности).
        int iteration = 0 ;
        matrixhHerarchy.add(setIteration(matrix));
        while (!isRectangle(matrix)) {
//            int numSort = 0;
//            while (numSort < this.matrix.length - 1) {
//                int rowMin = findMinRow(this.matrix, iteration, iteration);
//                matrix = swapLines(matrix, rowMin, numSort);
//                numSort++;
//            }
            if (!checkElement(iteration, matrix)){
                matrix = swapLines(matrix, iteration, matrix.length-1);
            }

            for(int i = iteration+1; i< matrix.length; i++){
                if(matrix[i][iteration]==0){
                    if (i==matrix.length-1){
                        break;
                    }else matrix= swapLines(matrix, i, matrix.length-1);
                }
                double c = findCoefficient(iteration, i, iteration, matrix);
                double[] line1 = editLineWithCoefficient(matrix, iteration, c);
                double[] lineCurrent = new double[width];
                for (int j = 0; j<width; j++){
                    lineCurrent[j] = matrix[i][j] + line1[j];
                }
                matrix = setLine(matrix, lineCurrent, i);
            }
            matrixhHerarchy.add(setIteration(matrix));
            iteration++;
        }
        resultMatrix = new double[matrix.length][matrix[0].length];
        for(int m = 0;m < matrix.length; m++){
            for(int n = 0;n < matrix[0].length; n++){
                Double d = new Double(matrix[m][n]);
                resultMatrix[m][n] = BigDecimal.valueOf(d)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
            }
        }
        getAnswers(resultMatrix);

    }

    private double[][] setIteration(double[][] basic){
        double[][] result = new double[basic.length][basic[0].length];
        for(int m = 0;m < basic.length; m++){
            for(int n = 0;n < basic[0].length; n++){
                Double d = new Double(basic[m][n]);
                result[m][n] = BigDecimal.valueOf(d)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
            }
        }
        return result;
    }



    private void getAnswers(double[][] array){
        for(int i= 0; i< array.length; i++){{
            int currentRow = array.length-1-i;
            double koef;

            koef = array[array.length-1-i][array.length];
            for (int j= 0; j<i; j++ ){
                koef =  koef - x[array.length-1-j]*array[currentRow][array.length-1-j];
            }
            this.x[currentRow] = koef/array[currentRow][array.length-1-i];

        }
        }
    }

    //метод поиска коэффициента, на который требуется домножить k-ую строку
    //для того чтобы при сложении с n-ой строкой произошло обнуление первого элемента
    //n-ой строки.
    //array - массив, в котором происходят вычисления

    private double findCoefficient(int k, int n, int iteration, double[][]array){
        return -array[n][iteration]/array[k][iteration];
    };


    boolean checkElement(int k, double[][] array){
        if (array[k][k]==0){
            return false;
        }else return true;
    }

    private boolean isRectangle(double[][] array){
        boolean b = true;
        stopProcess:
        for(int i = 0; i<array.length; i++) {
            for (int j = 0; j < i; j++){
                if (array[i][j]!=0){
                    b = false;
                    break stopProcess;
                }
            }
        }
        return b;
    }







}