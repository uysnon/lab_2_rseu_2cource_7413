package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSizeSystem;
    LinearLayout systemLinearLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context =  getApplication().getApplicationContext();
        systemLinearLayout =  findViewById(R.id.linear_layout_system_picture);
        spinnerSizeSystem = findViewById(R.id.spinner_size_system);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SizeSystem.SIZES_ARRAY_STRING);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSizeSystem.setAdapter(adapter);

        spinnerSizeSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               DrawSystem drawSystem = new DrawSystem(context, systemLinearLayout , position+2 );

               drawSystem.draw();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
