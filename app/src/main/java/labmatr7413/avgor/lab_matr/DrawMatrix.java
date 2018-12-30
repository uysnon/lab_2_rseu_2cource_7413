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

import java.math.BigDecimal;
import java.math.RoundingMode;


public class DrawMatrix {
    static private final int WIDTH_TEXT_VIEW = 65;
    Context context;
    double[][] matrix;
    LinearLayout parentLinearLayout;
    LinearLayout matrixItself;
    LinearLayout left;
    LinearLayout right;
    LinearLayout matrixTextHead;
    LinearLayout matrixTextAnswers;
    double[] answers;


    DrawMatrix(Context context, LinearLayout matrixTextHead, LinearLayout parentLinearLayout, LinearLayout matrixItself,LinearLayout LEFT, LinearLayout RIGHT,LinearLayout  matrixTextAnswers, double[][] matrix){
        this.parentLinearLayout = parentLinearLayout;
        this.matrixItself = matrixItself;
        this.left = LEFT;
        this.right = RIGHT;
        this.context = context;
        this.matrixTextHead = matrixTextHead;
        this.matrixTextAnswers = matrixTextAnswers;
        this.matrix = setArray(matrix);
        answers = new double[matrix.length];
    }

    void clear(){
        parentLinearLayout.removeAllViews();
    }


    @SuppressLint("ResourceAsColor")
    void draw(){
        parentLinearLayout.removeAllViews();
        matrixItself.removeAllViews();
        left.removeAllViews();
        right.removeAllViews();
        matrixTextHead.removeAllViews();

        LinearLayout.LayoutParams headParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headParams.setMargins(0,50,0,25);
        headParams.gravity = Gravity.CENTER;
        TextView HeadText = new TextView(context);
        HeadText.setText("Расширенная треугольная матрица:");
        HeadText.setTextSize(18);
        HeadText.setTextColor(Color.parseColor("#000000"));
        HeadText.setLayoutParams(headParams);
        matrixTextHead.addView(HeadText);

        ImageView im = new ImageView(context);
        im.setImageResource(R.drawable.bracket_left);
        ImageView imR = new ImageView(context);
        imR.setImageResource(R.drawable.ic_right_bracket);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams imgParamsR = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        switch(matrix.length) {
            case 2:
                imgParams.height = 130;
                imgParams.width = 45;
                im.setLayoutParams(imgParams);



                imgParamsR.height = 130;
                imgParamsR.width = 45;
                imR.setLayoutParams(imgParamsR);
                break;

            case 3:
                imgParams.height = 175;
                imgParams.width = 65;
                im.setLayoutParams(imgParams);
                imgParamsR.height = 175;
                imgParamsR.width = 65;
                imR.setLayoutParams(imgParamsR);
                break;


            case 4:
                imgParams.height = 275;
                imgParams.width = 65;
                im.setLayoutParams(imgParams);
                imgParamsR.height = 275;
                imgParamsR.width = 65;
                imR.setLayoutParams(imgParamsR);
                break;


        }
        left.addView(im);
        right.addView(imR);

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
                textView.setWidth(125);
                linearLayout.addView(textView);


            }
            matrixItself.addView(linearLayout);



        }

        parentLinearLayout.addView(matrixItself);

        parentLinearLayout.addView(right);
        drawAnswers();








    }
    void drawAnswers(){
        matrixTextAnswers.removeAllViews();
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
        matrixTextAnswers.addView(headAnswers);
        linearLayoutAnswers.setLayoutParams(params);
        headAnswers.setLayoutParams(params);
        for (int j=0; j< this.answers.length; j++){
            BigDecimal bigDecimal = new BigDecimal(this.answers[j]).setScale(2,RoundingMode.HALF_UP);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = new TextView(context);
                textView.setTextSize(17);
                textView.setTextColor(Color.parseColor("#541137"));
                String s = "x" + Integer.toString(j+1)+" = "+ bigDecimal.toString() + ";" + " ";

                textView.setText(s);


//                textView.setWidth(WIDTH_TEXT_VIEW);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
//                TextView textViewSign = new TextView(context);
                linearLayout.addView(textView);
                linearLayoutAnswers.addView(linearLayout);
            }
        matrixTextAnswers.addView(linearLayoutAnswers);
        }

    public void setAnswers(double[] answers) {
        this.answers = answers;
    }
}
