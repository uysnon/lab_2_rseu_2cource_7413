package labmatr7413.avgor.lab_matr;



public class GaussMethod{


    //ТИПО ПОСЛЕ КЛИКА НА КНОПКУ
    public static void random() {
        int rows  = 5;
        double value;

        Matrix mat1 = new Matrix();
        double matRes;

        mat1.setSize(rows, rows);

        for (int i=0; i<rows; i++){
            int iNum = i+1;
            for (int j=0; j<rows; j++){
                int jNum = j+1;
                System.out.println("Введите "+jNum+" элемент "+iNum+" столбца: ");
                value = i + 1;
                mat1.setElem(i+1, j+1, value);
            }
        }

        matRes = MaxtrixCalculator.gauss(mat1);

        }





    }

