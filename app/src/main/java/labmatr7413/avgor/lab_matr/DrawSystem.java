package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Array;

public  class DrawSystem {
     Context context;
     private int idEditTexts[][];
     int size;
     LinearLayout parentLinearLayout;

    DrawSystem(Context context, LinearLayout parentLinearLayout, int size){
        this.context = context;
        this.size = size;
        this.parentLinearLayout = parentLinearLayout;
        idEditTexts = new int[size][size];
        for (int j=0; j<size; j++) {
            for (int i = 0; i < size; i++) {
                idEditTexts[j][i]= View.generateViewId();
            }
        }

    }

     void draw(){


        for (int j=0; j< size; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            for (int i = 0; i< size; i++){
                EditText editText = new EditText(context);
                editText.setId(idEditTexts[j][i]);
                linearLayout.addView(editText);
            }
            parentLinearLayout.addView(linearLayout);
        }

    }

    public int[][] getIdEditTexts() {
        return idEditTexts;
    }
}
