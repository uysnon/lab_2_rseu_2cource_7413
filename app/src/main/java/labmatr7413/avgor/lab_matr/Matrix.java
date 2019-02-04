package labmatr7413.avgor.lab_matr;

public class Matrix {

    //поменять значения строки row масссива array на значения из массива line
    static Fraction[][] setLine(Fraction [][] array, Fraction[] line, int row ){
        Fraction [][] array0 = setArray(array);
        for (int i = 0; i< array[0].length; i++){
            array0[row][i] = line[i];
        }
        return array0;
    }

    //Создать одномерный массив ряда исходного двумерного массива с опередленным коэффиециентом
    static Fraction[] editLineWithCoefficient(Fraction [][] matrix0, int line, Fraction coefficient){
        Fraction[]array  = new  Fraction[matrix0[0].length];
        for (int i = 0; i< array.length; i++){
            array[i] = Fraction.multiply(matrix0[line][i],coefficient);
        }
        return  array;
    }

    //Поменять  местами 2 ряда в массиве array 0
    static Fraction[][] swapLines(Fraction[][]array , int row1, int row2){
        Fraction [][] array0 = setArray(array);
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
    static  Fraction[][] setArray(Fraction[][] source){
        Fraction[][] array = new Fraction[source.length][source[0].length] ;
        for (int i = 0 ; i< source.length; i++){
            for (int j = 0 ; j< source[0].length; j++){
                array[i][j]= source[i][j];
            }
        }
        return  array;
    };
}
