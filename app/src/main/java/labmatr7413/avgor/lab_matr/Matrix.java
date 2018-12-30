package labmatr7413.avgor.lab_matr;

public class Matrix {

    //поменять значения строки row масссива array на значения из массива line
    static double[][] setLine(double [][] array, double[] line, int row ){
        double [][] array0 = setArray(array);
        for (int i = 0; i< array[0].length; i++){
            array0[row][i] = line[i];
        }
        return array0;
    }

    //Создать одномерный массив ряда исходного двумерного массива с опередленным коэффиециентом
    static double[] editLineWithCoefficient(double [][] matrix0, int line, double coefficient){
        double[]array  = new  double[matrix0[0].length];
        for (int i = 0; i< array.length; i++){
            array[i] = matrix0[line][i] * coefficient;
        }
        return  array;
    }

    //Поменять  местами 2 ряда в массиве array 0
    static double[][] swapLines(double[][]array , int row1, int row2){
        double [][] array0 = setArray(array);
        if (row1 != row2) {
            for (int i = 0; i < array[0].length; i++) {
                array0[row1][i] = array[row2][i];
            }
            for (int i = 0; i < array[0].length; i++) {
                array0[row2][i] = array[row1][i];
            }
        }
        return array0;
    }
    //Найти ряд с минимальным k-ым элементом из все, кроме n первых рядов
    static int findMinRow(double[][] array, int k, int n){
        double min = array[n][k];
        int minAddress= n;
        for (int i = n+2 ; i < array.length; i ++){
            if ((array[i][k] < min)&&(array[i][k]!=0)) {
                min = array[i][k];
                minAddress = i;
            }
        }
        return minAddress;
    };

    //Создать двумерный массив на основе другого массива
    static  double[][] setArray(double[][] source){
        double[][] array = new double[source.length][source[0].length] ;
        for (int i = 0 ; i< source.length; i++){
            for (int j = 0 ; j< source[0].length; j++){
                array[i][j]= source[i][j];
            }
        }
        return  array;
    };
}
