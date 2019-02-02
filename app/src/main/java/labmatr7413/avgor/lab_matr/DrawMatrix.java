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
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class DrawMatrix {
    static private final int WIDTH_TEXT_VIEW = 65;
    Context context;
    ArrayList <double[][]>  matrixhHerarchy;
    LinearLayout parentLinearLayout;
    LinearLayout matrixTextAnswers;
    double[] answers;
    int sizeSystem;


    DrawMatrix(
            Context context,
            LinearLayout parentLinearLayout,
            LinearLayout  matrixTextAnswers,
            MethodGauss methodGauss)
    {
        this.parentLinearLayout = parentLinearLayout;
        this.parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        parentLinearLayout.setGravity(Gravity.CENTER);
        this.context = context;
        this.matrixTextAnswers = matrixTextAnswers;
        this.matrixhHerarchy = methodGauss.getMatrixhHerarchy();
        sizeSystem = matrixhHerarchy.get(0).length;
        answers = methodGauss.getX();
    }

    void clear(){
        parentLinearLayout.removeAllViews();
    }


    @SuppressLint("ResourceAsColor")
    void draw(){
        parentLinearLayout.removeAllViews();
        for (int i = 0; i < matrixhHerarchy.size(); i++){
            parentLinearLayout.addView(getIterationLayout(matrixhHerarchy.get(i)));
        }
        drawAnswers();
        parentLinearLayout.addView(getHead());
        parentLinearLayout.addView(getIterationLayout(matrixhHerarchy.get(matrixhHerarchy.size()-1)));
    }


    LinearLayout getHead(){
        LinearLayout matrixTextHead = new LinearLayout(context);
        LinearLayout.LayoutParams headParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headParams.setMargins(0,50,0,25);
        headParams.gravity = Gravity.CENTER;
        matrixTextHead.setLayoutParams(headParams);
        TextView HeadText = new TextView(context);
        HeadText.setText("Расширенная треугольная матрица:");
        HeadText.setTextSize(18);
        HeadText.setTextColor(Color.parseColor("#000000"));
        HeadText.setLayoutParams(headParams);
        matrixTextHead.addView(HeadText);
        return matrixTextHead;
    }

    LinearLayout getIterationLayout(double[][] matrix){
        LinearLayout matrixItself = new LinearLayout(context) ;
        matrixItself.setOrientation(LinearLayout.VERTICAL);

        ViewGroup.LayoutParams helpParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) helpParams).topMargin = 50;
        ((LinearLayout.LayoutParams) helpParams).gravity  = Gravity.CENTER;

        LinearLayout iterationLayout = new LinearLayout(context);
        iterationLayout.setLayoutParams(helpParams);

        LinearLayout left = new LinearLayout(context);
        LinearLayout right = new LinearLayout(context);
        matrixItself.removeAllViews();
        left.removeAllViews();
        right.removeAllViews();
        ImageView imL = new ImageView(context);
        imL.setImageResource(R.drawable.bracket_left);
        ImageView imR = new ImageView(context);
        imR.setImageResource(R.drawable.ic_right_bracket);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        switch(sizeSystem) {
            case 2:
                imgParams.height = 130;
                imgParams.width = 45;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;

            case 3:
                imgParams.height = 175;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;


            case 4:
                imgParams.height = 250;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;


            case 5:
                imgParams.height = 300;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;


        }
        left.addView(imL);
        right.addView(imR);
            iterationLayout.addView(left);
            for (int j = 0; j < sizeSystem; j++) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                for (int i = 0; i < sizeSystem + 1; i++) {
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
            iterationLayout.addView(matrixItself);
            iterationLayout.addView(right);
            return  iterationLayout;
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
        linearLayoutAnswers.setOrientation(LinearLayout.VERTICAL);
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
                String s = "x" + Integer.toString(j+1)+" = "+ bigDecimal.toString() + ";\n" ;
                textView.setText(s);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayout.addView(textView);
                linearLayoutAnswers.addView(linearLayout);
            }
        matrixTextAnswers.addView(linearLayoutAnswers);
        }
}
