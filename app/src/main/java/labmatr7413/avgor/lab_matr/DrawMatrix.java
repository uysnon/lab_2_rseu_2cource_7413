package labmatr7413.avgor.lab_matr;
import static labmatr7413.avgor.lab_matr.Matrix.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrawMatrix {
    static  private final int WIDTH_TEXT_VIEW = 105;
    Context context;
    double[][] matrix;
    LinearLayout parentLinearLayout;
    double[] answers;


    DrawMatrix(Context context, LinearLayout parentLinearLayout, double[][] matrix){
        this.parentLinearLayout = parentLinearLayout;
        this.context = context;
        this.matrix = setArray(matrix);
        answers = new double[matrix.length];
    }

    void clear(){
        parentLinearLayout.removeAllViews();
    }


    @SuppressLint("ResourceAsColor")
    void draw(){
        parentLinearLayout.removeAllViews();
        for (int j=0; j< matrix.length; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i< matrix[0].length; i++){
                TextView textView = new TextView(context);
                textView.setTextSize(15);
                textView.setTextSize(Color.parseColor("#000000"));
                textView.setText(Double.toString(matrix[j][i]));
                textView.setWidth(WIDTH_TEXT_VIEW);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                TextView textViewSign = new TextView(context);
                linearLayout.addView(textView);
            }
            parentLinearLayout.addView(linearLayout);
        }
        drawAnswers();

    }
    void drawAnswers(){
         LinearLayout linearLayoutAnswers = new LinearLayout(context);
         linearLayoutAnswers.setOrientation(LinearLayout.VERTICAL);
        for (int j=0; j< this.answers.length; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = new TextView(context);
                textView.setTextSize(20);
                textView.setTextColor(Color.parseColor("#D81B60"));
                String s = "x"+Integer.toString(j+1)+" = "+ this.answers[j];
                textView.setText(s);
//                textView.setWidth(WIDTH_TEXT_VIEW);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
//                TextView textViewSign = new TextView(context);
                linearLayout.addView(textView);
                linearLayoutAnswers.addView(linearLayout);
            }
        parentLinearLayout.addView(linearLayoutAnswers);
        }

    public void setAnswers(double[] answers) {
        this.answers = answers;
    }
}
