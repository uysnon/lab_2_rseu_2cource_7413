package labmatr7413.avgor.lab_matr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class DrawMatrix {
    static private final int WIDTH_TEXT_VIEW = 65;
    Context context;
    ArrayList<Fraction[][]> matrixhHerarchy;
    LinearLayout parentLinearLayout;
    LinearLayout matrixTextAnswers;
    Fraction[] answers;
    int sizeSystem;


    DrawMatrix(
            Context context,
            LinearLayout parentLinearLayout,
            LinearLayout matrixTextAnswers,
            MethodGauss methodGauss) {
        this.parentLinearLayout = parentLinearLayout;
        this.parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        parentLinearLayout.setGravity(Gravity.CENTER);
        this.context = context;
        this.matrixTextAnswers = matrixTextAnswers;
        this.matrixhHerarchy = methodGauss.getMatrixhHerarchy();
        sizeSystem = matrixhHerarchy.get(0).length;
        answers = methodGauss.getX();
    }

    void clear() {
        parentLinearLayout.removeAllViews();
    }


    @SuppressLint("ResourceAsColor")
    void draw() {
        parentLinearLayout.removeAllViews();
        drawAnswers();
        parentLinearLayout.addView(getHead());
        parentLinearLayout.addView(getIterationLayout(matrixhHerarchy.get(matrixhHerarchy.size() - 1)));
    }

    void drawDetailed() {
        parentLinearLayout.removeAllViews();
        for (int i = 0; i < matrixhHerarchy.size(); i++) {
            parentLinearLayout.addView(getIterationLayout(matrixhHerarchy.get(i)));
        }
        drawAnswers();
        parentLinearLayout.addView(getHead());
        parentLinearLayout.addView(getIterationLayout(matrixhHerarchy.get(matrixhHerarchy.size() - 1)));
    }


    LinearLayout getHead() {
        LinearLayout matrixTextHead = new LinearLayout(context);
        LinearLayout.LayoutParams headParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        headParams.setMargins(16, 20, 16, 25);
        headParams.gravity = Gravity.CENTER;
        matrixTextHead.setLayoutParams(headParams);
        TextView headText = new TextView(context);
        headText.setText(context.getString(R.string.lower_triangular_matrix));
        headText.setTextSize(16);
        headText.setTypeface(headText.getTypeface(), Typeface.BOLD);
        headText.setTextColor(Color.parseColor("#000000"));
        headText.setLayoutParams(headParams);
        matrixTextHead.addView(headText);
        return matrixTextHead;
    }

    LinearLayout getIterationLayout(Fraction[][] matrix) {
        LinearLayout matrixItself = new LinearLayout(context);
        matrixItself.setOrientation(LinearLayout.VERTICAL);

        ViewGroup.LayoutParams helpParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) helpParams).topMargin = 50;
        ((LinearLayout.LayoutParams) helpParams).gravity = Gravity.CENTER;

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
        switch (sizeSystem) {
            case 2:
                imgParams.height = 240;
                imgParams.width = 45;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;

            case 3:
                imgParams.height = 360;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;


            case 4:
                imgParams.height = 480;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;


            case 5:
                imgParams.height = 600;
                imgParams.width = 65;
                imL.setLayoutParams(imgParams);
                imR.setLayoutParams(imgParams);
                break;

            case 6:
                imgParams.height = 720;
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
                String s;
                s = drawFraction(matrix[j][i]);
                textView.setWidth(200);
                textView.setHeight(120);
                textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView.setPadding(0, 10, 0, 0);
                textView.setText(Html.fromHtml(s));
                linearLayout.addView(textView);
            }
            matrixItself.addView(linearLayout);
        }
        iterationLayout.addView(matrixItself);
        iterationLayout.addView(right);
        return iterationLayout;
    }


    void drawAnswers() {
        matrixTextAnswers.removeAllViews();
        TextView headAnswers = new TextView(context);
        headAnswers.setText(context.getString(R.string.solution));
        headAnswers.setTextSize(16);
        headAnswers.setTypeface(headAnswers.getTypeface(), Typeface.BOLD);
        headAnswers.setTextColor(Color.parseColor("#000000"));
        LinearLayout linearLayoutAnswers = new LinearLayout(context);
        linearLayoutAnswers.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(0, 30, 0, 0);
        linearLayoutAnswers.setOrientation(LinearLayout.VERTICAL);
        matrixTextAnswers.addView(headAnswers);
        linearLayoutAnswers.setLayoutParams(params);
        headAnswers.setLayoutParams(params);
        for (int j = 0; j < this.answers.length; j++) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView = new TextView(context);
            textView.setTextSize(17);
            textView.setHeight(130);

            textView.setPadding(0, 10, 0, 0);
            textView.setTextColor(Color.parseColor("#541137"));
            String s = "x" + "<sub>" + Integer.toString(j + 1) + "</sub>" + " = " + drawFractionResult(this.answers[j]);
            textView.setText(Html.fromHtml(s));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.addView(textView);
            linearLayoutAnswers.addView(linearLayout);
        }
        matrixTextAnswers.addView(linearLayoutAnswers);
    }

    private String drawFraction(Fraction fraction) {
        String s = ((fraction.getNumerator() == 0) || (fraction.getDenominator() == 1)) ?
                String.valueOf(fraction.getNumerator()) :
                "<sup>" + fraction.getNumerator() + "</sup>" + "/<sub>" + fraction.getDenominator() + "</sub>";
        return s;
    }

    private String drawFractionResult(Fraction fraction) {
        String s = drawFraction(fraction);
        double d = (double) fraction.getNumerator() / fraction.getDenominator();
        s = s + " ≈" + String.valueOf(
                BigDecimal.valueOf(d)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()
        );

        return s;
    }
}
