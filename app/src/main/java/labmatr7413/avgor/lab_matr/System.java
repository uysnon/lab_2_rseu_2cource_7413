package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class System {
    Fraction systemCoefficients[][];
    System(int vertical, int horizontal){
        systemCoefficients = new Fraction[vertical][horizontal];
    }

    public Fraction[][] getSystemCoefficients() {
        return systemCoefficients;
    }

    public void setSystemCoefficients(Fraction coefficient, int i, int j) {
        this.systemCoefficients[i][j] = coefficient;
    }
}

