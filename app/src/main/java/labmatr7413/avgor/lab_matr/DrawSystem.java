package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import java.lang.reflect.Array;

public class DrawSystem {
    static private final int WIDTH_EDIT_TEXT = 105;
    Context context;
    private int idEditTexts[][];
    int size;
    LinearLayout parentLinearLayout;

    DrawSystem(Context context, LinearLayout parentLinearLayout, int size) {
        this.parentLinearLayout = parentLinearLayout;
        if (size == 1) {
            parentLinearLayout.removeAllViews();
            idEditTexts = new int[0][0];
        }
        this.context = context;
        this.size = size;
        idEditTexts = new int[size][size + 1];
        for (int j = 0; j < idEditTexts.length; j++) {
            for (int i = 0; i < idEditTexts[0].length; i++) {
                idEditTexts[j][i] = View.generateViewId();
            }
        }

    }

    void clear() {
        parentLinearLayout.removeAllViews();
    }

    void draw() {
        Random random = new Random();

        parentLinearLayout.removeAllViews();
        for (int j = 0; j < idEditTexts.length; j++) {
            LinearLayout linearLayout = new LinearLayout(context);
            for (int i = 0; i < idEditTexts[0].length; i++) {
                EditText editText = new EditText(context);
                editText.setId(idEditTexts[j][i]);
                editText.setText(Integer.toString(random.nextInt(20)));
                editText.setWidth(WIDTH_EDIT_TEXT);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                editText.setGravity(Gravity.CENTER_HORIZONTAL);
                TextView textViewSign = new TextView(context);
                textViewSign.setPadding(0,0,0,8);
                if (i < size) {
                    if (i < size - 1) {
                        textViewSign.setText(Html.fromHtml("x" + "<sub>" + Integer.toString(i + 1) + "</sub>" + " +"));
                    } else {
                        textViewSign.setText(Html.fromHtml("x" + "<sub>" + Integer.toString(i + 1) + "</sub>" + " ="));
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
