package labmatr7413.avgor.lab_matr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static labmatr7413.avgor.lab_matr.Matrix.*;

public class MethodGauss {

    Fraction [][] matrix;
    Fraction [][] matrix0;
    int width;
    int height;
    Fraction[][] resultMatrix;

    public Fraction[] getX() {
        return x;
    }

    private Fraction[] x;

    public ArrayList<Fraction[][]> getMatrixhHerarchy() {
        return matrixhHerarchy;
    }

    private ArrayList<Fraction[][]> matrixhHerarchy;

    MethodGauss(Fraction [][] sourceMatrix ){
        matrix = setArray(sourceMatrix);
        matrix0 = setArray(matrix);
        width = matrix0[0].length;
        height = matrix0.length;
        this.x = new Fraction[matrix0.length];
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
        matrixhHerarchy.add(matrix);
        while (!isRectangle(matrix)&&(iteration<matrix.length)) {
//            int numSort = 0;
//            while (numSort < this.matrix.length - 1) {
//                int rowMin = findMinRow(this.matrix, iteration, iteration);
//                matrix = swapLines(matrix, rowMin, numSort);
//                numSort++;
//            }
            int help= 0;

            breakMe:
            {
                boolean bHelp = false;
                while ((help < matrix.length) && (!bHelp)) {
                    if (iteration == matrix.length - 1) {
                        if (checkElement(iteration, matrix)) break breakMe;
                    }
                    if (!checkElement(iteration, matrix)) {
                        matrix = swapLines(matrix, iteration, iteration + 1);
                    } else bHelp = true;
                    help++;
                }
                if (help >= matrix.length) {
                    iteration++;
                }


                for (int i = iteration + 1; i < matrix.length; i++) {
                    if (matrix[i][iteration].getNumerator() == 0) {
                        if (i == matrix.length - 1) {
                            break;
                        } else matrix = swapLines(matrix, i, matrix.length - 1);
                    }
                    Fraction c = findCoefficient(iteration, i, iteration, matrix);
                    Fraction[] line1 = editLineWithCoefficient(matrix, iteration, c);
                    Fraction[] lineCurrent = new Fraction[width];
                    for (int j = 0; j < width; j++) {
                        lineCurrent[j] = Fraction.add(matrix[i][j],line1[j]);
                    }
                    matrix = setLine(matrix, lineCurrent, i);
                }
            }

            matrixhHerarchy.add(matrix);
            iteration++;
        }
        resultMatrix = matrix;
        getAnswers(resultMatrix);

    }

//    private double[][] setIteration(double[][] basic){
//        double[][] result = new double[basic.length][basic[0].length];
//        for(int m = 0;m < basic.length; m++){
//            for(int n = 0;n < basic[0].length; n++){
//                Double d = new Double(basic[m][n]);
//                result[m][n] = BigDecimal.valueOf(d)
//                        .setScale(1, RoundingMode.HALF_UP)
//                        .doubleValue();
//            }
//        }
//        return result;
//    }



    private void getAnswers(Fraction[][] array){
        for(int i= 0; i< array.length; i++){{
            int currentRow = array.length-1-i;
            Fraction koef;
            koef = new Fraction(array[array.length-1-i][array.length]);
            for (int j= 0; j<i; j++ ){
                koef =  Fraction.subtraction(koef,
                        Fraction.multiply(x[array.length-1-j],array[currentRow][array.length-1-j])
                );
            }
            this.x[currentRow] = Fraction.division(
                    koef,
                    array[currentRow][array.length-1-i]);

        }
        }
    }

    //метод поиска коэффициента, на который требуется домножить k-ую строку
    //для того чтобы при сложении с n-ой строкой произошло обнуление первого элемента
    //n-ой строки.
    //array - массив, в котором происходят вычисления

    private Fraction findCoefficient(int k, int n, int iteration, Fraction[][]array){
        return Fraction.multiply(
                new Fraction(-1,1),
                Fraction.division(array[n][iteration],array[k][iteration]));
    };


    boolean checkElement(int k, Fraction[][] array){
        if (array[k][k].getNumerator()==0){
            return false;
        }else return true;
    }

    private boolean isRectangle(Fraction[][] array){
        boolean b = true;
        stopProcess:
        for(int i = 0; i<array.length; i++) {
            for (int j = 0; j < i; j++){
                if (array[i][j].getNumerator()!=0){
                    b = false;
                    break stopProcess;
                }
            }
        }
        return b;
    }







}