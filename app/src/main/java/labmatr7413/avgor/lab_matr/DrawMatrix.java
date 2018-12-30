package labmatr7413.avgor.lab_matr;
import static java.security.AccessController.getContext;
import static labmatr7413.avgor.lab_matr.Matrix.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DrawMatrix {
    static  private final int WIDTH_TEXT_VIEW = 105;
    Context context;
    double[][] matrix;
    LinearLayout parentLinearLayout;
    LinearLayout matrixItself;
    LinearLayout left;
    LinearLayout right;
    double[] answers;


    DrawMatrix(Context context, LinearLayout parentLinearLayout, LinearLayout matrixItself,LinearLayout LEFT, LinearLayout RIGHT, double[][] matrix){
        this.parentLinearLayout = parentLinearLayout;
        this.matrixItself = matrixItself;
        this.left = LEFT;
        this.right = RIGHT;
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
        left.removeAllViews();
        right.removeAllViews();
        matrixItself.removeAllViews();
        LinearLayout.LayoutParams headParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headParams.setMargins(0,50,0,25);
        headParams.gravity = Gravity.CENTER;
        TextView HeadText = new TextView(context);
        HeadText.setText("Расширенная треугольная матрица:");
        HeadText.setTextSize(18);
        HeadText.setTextColor(Color.parseColor("#000000"));
        HeadText.setLayoutParams(headParams);


        parentLinearLayout.addView(HeadText);
        ImageView im = new ImageView(context);
        im.setImageResource(R.drawable.ic_gullbraceleft);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        switch(matrix.length) {
            case 2:

            case 3:
                imgParams.height = 115;
                imgParams.setMargins(0,0,350,0);
                im.setLayoutParams(imgParams);

                left.addView(im);

            case 4:
        }


        parentLinearLayout.addView(left);

        for (int j=0; j< matrix.length; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i< matrix[0].length; i++){

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                linearLayout.setLayoutParams(params);
                TextView textView = new TextView(context);
                textView.setTextSize(15);
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setText(Double.toString(matrix[j][i]));
                textView.setWidth(WIDTH_TEXT_VIEW);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                TextView textViewSign = new TextView(context);
                linearLayout.addView(textView);

            }
            matrixItself.addView(linearLayout);
            //надо перент вью докинуть
        }

        drawAnswers();

    }
    void drawAnswers(){
        int maxLength = 9;

        TextView headAnswers = new TextView(context);
        headAnswers.setText("Решение СЛАУ: ");
        headAnswers.setTextSize(18);
        headAnswers.setTextColor(Color.parseColor("#000000"));
        LinearLayout linearLayoutAnswers = new LinearLayout(context);
        linearLayoutAnswers.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(0,30,0,0);
        parentLinearLayout.addView(headAnswers);
        linearLayoutAnswers.setLayoutParams(params);
        headAnswers.setLayoutParams(params);
        for (int j=0; j< this.answers.length; j++){
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = new TextView(context);
                textView.setTextSize(17);
                textView.setTextColor(Color.parseColor("#541137"));
                String s = (("x" +Integer.toString(j+1)+" = "+ this.answers[j]).substring(0,maxLength)) + ";" + " ";
                //textView.setFilters(fArray);
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
