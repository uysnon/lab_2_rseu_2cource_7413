package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSizeSystem;
    LinearLayout systemLinearLayout;
    Context context;
    int size_horizontal;
    int size_vertical;
    DrawSystem drawSystem;
    TextView tv;
    System system;
    Button buttonGauss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context =  getApplication().getApplicationContext();
        systemLinearLayout =  findViewById(R.id.linear_layout_system_picture);
        spinnerSizeSystem = findViewById(R.id.spinner_size_system);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SizeSystem.SIZES_ARRAY_STRING);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buttonGauss = findViewById(R.id.button_method_Gauss);
        spinnerSizeSystem.setAdapter(adapter);
        spinnerSizeSystem.setSelection(0);


            buttonGauss.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    try {
                    system = new System(drawSystem.getIdEditTexts().length, drawSystem.getIdEditTexts()[0].length);
                    parseEditTexts(drawSystem);
                    tv = findViewById(R.id.res);
                    int rows  = size_horizontal;
                     Matrix mat1 = new Matrix();
                     double matRes;
                     double value;
                     mat1.setSize(rows, rows);

                        for (int i=0; i<rows; i++){
                            for (int j=0; j<rows; j++){
                                value = system.getSystemCoefficients()[i][j];
                                mat1.setElem(i+1, j+1,value);
                            }
                        }

                        //matRes = MaxtrixCalculator.gauss(mat1);
                        matRes = mat1.getDet(mat1);
                        tv.setText(Double.toString(matRes));

                }catch (Exception e){
                        ToastMessages.dataError(context);
                    }
                }

            });

        spinnerSizeSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               drawSystem = new DrawSystem(context, systemLinearLayout , position+2 );

               size_horizontal = position+3;
               size_vertical = position+2;
               drawSystem.draw();

                   drawSystem = new DrawSystem(context, systemLinearLayout, position + 1);
                   if (position != 0) {drawSystem.draw();}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    void parseEditTexts(DrawSystem drawSystem){
        for (int i = 0 ; i< system.getSystemCoefficients().length; i++){
            for (int j = 0 ; j< system.getSystemCoefficients()[0].length; j++){
                EditText editText = (EditText)findViewById(drawSystem.getIdEditTexts()[i][j]);
                double value = Double.parseDouble(editText.getText().toString());
                system.setSystemCoefficients(value, i, j);
            }
        }
    }
}
