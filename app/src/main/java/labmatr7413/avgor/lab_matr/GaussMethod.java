package labmatr7413.avgor.lab_matr;


import android.widget.EditText;

public class GaussMethod{

    MainActivity mainActivity = new MainActivity();


    int rows = mainActivity.size_horizontal;
    int cols = mainActivity.size_vertical;

    DrawSystem drawSystem = new DrawSystem(mainActivity.context, mainActivity.systemLinearLayout,rows);


    //ТИПО ПОСЛЕ КЛИКА НА КНОПКУ
    public void random() {



        Matrix mat1 = new Matrix();
        double matRes;

        mat1.setSize(rows, cols);

        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){

                mat1.setElem(i+1, j+1,5);
            }
        }

        matRes = MaxtrixCalculator.gauss(mat1);

        }





    }

