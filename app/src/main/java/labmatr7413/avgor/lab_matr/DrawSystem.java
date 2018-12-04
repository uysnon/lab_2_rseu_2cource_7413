package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        idEditTexts = new int[size][size+1];
        for (int j=0; j<size; j++) {
            for (int i = 0; i < size+1; i++) {
                idEditTexts[j][i]= View.generateViewId();
            }
        }

    }

    void clear(){
        parentLinearLayout.removeAllViews();
    }

     void draw(){
        parentLinearLayout.removeAllViews();
        for (int j=0; j< size; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            for (int i = 0; i< size+1; i++){
                EditText editText = new EditText(context);
                editText.setId(idEditTexts[j][i]);
                TextView textViewSign = new TextView(context);
                if  (i<size) {
                    if (i < size - 1) {

                        textViewSign.setText("x" + Integer.toString(i + 1) + " +");
                    } else {
                        textViewSign.setText("x" + Integer.toString(i + 1) + " =");
                    }
                }
                linearLayout.addView(editText);
                linearLayout.addView(textViewSign);
            }
            parentLinearLayout.addView(linearLayout);
        }

    }

    public int[][] getIdEditTexts() {
        return idEditTexts;
    }
}
