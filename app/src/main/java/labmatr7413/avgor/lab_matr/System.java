package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class System {
    double systemCoefficients[][];
    System(int vertical, int horizontal){
        systemCoefficients = new double[vertical][horizontal];
    }

    public double[][] getSystemCoefficients() {
        return systemCoefficients;
    }

    public void setSystemCoefficients(double coefficient, int i, int j) {
        this.systemCoefficients[i][j] = coefficient;
    }
}

