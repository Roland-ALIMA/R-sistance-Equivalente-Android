package cm.mboasoftwares.roland.resistanceequivalente;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner nbr_anneaux_spinner;
    Spinner color1_spinner;
    Spinner color2_spinner;
    Spinner color3_spinner;
    Spinner color4_spinner;
    Spinner color5_spinner;
    Spinner color6_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width/3, LinearLayout.LayoutParams.WRAP_CONTENT);

        nbr_anneaux_spinner = (Spinner) findViewById(R.id.nbr_anneaux_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> nbr_anneaux_adapter = ArrayAdapter.createFromResource(this, R.array.nbr_anneaux_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        nbr_anneaux_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        nbr_anneaux_spinner.setAdapter(nbr_anneaux_adapter);
        nbr_anneaux_spinner.setOnItemSelectedListener(this);

        color1_spinner = (Spinner) findViewById(R.id.color1_spinner);
        color1_spinner.setAdapter(nbr_anneaux_adapter);
        color1_spinner.setVisibility(View.INVISIBLE);
        //color1_spinner.setOnItemSelectedListener(this);
        //color1_spinner.setLayoutParams(lp);

        color2_spinner = (Spinner) findViewById(R.id.color2_spinner);
        color2_spinner.setAdapter(nbr_anneaux_adapter);
        color2_spinner.setVisibility(View.INVISIBLE);
        //color2_spinner.setOnItemSelectedListener(this);
        //color2_spinner.setLayoutParams(lp);

        color3_spinner = (Spinner) findViewById(R.id.color3_spinner);
        color3_spinner.setAdapter(nbr_anneaux_adapter);
        color3_spinner.setVisibility(View.INVISIBLE);
        //color3_spinner.setOnItemSelectedListener(this);

        color4_spinner = (Spinner) findViewById(R.id.color4_spinner);
        color4_spinner.setAdapter(nbr_anneaux_adapter);
        color4_spinner.setVisibility(View.INVISIBLE);
        //color4_spinner.setOnItemSelectedListener(this);

        color5_spinner = (Spinner) findViewById(R.id.color5_spinner);
        color5_spinner.setAdapter(nbr_anneaux_adapter);
        color5_spinner.setVisibility(View.INVISIBLE);
        //color5_spinner.setOnItemSelectedListener(this);

        color6_spinner = (Spinner) findViewById(R.id.color6_spinner);
        color6_spinner.setAdapter(nbr_anneaux_adapter);
        color6_spinner.setVisibility(View.INVISIBLE);
        //color1_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) parent.getSelectedItem();
        switch (Integer.valueOf(s)) {
            case 3:
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setVisibility(View.INVISIBLE);
                color5_spinner.setVisibility(View.INVISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                break;
            case 4:
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setVisibility(View.VISIBLE);
                color5_spinner.setVisibility(View.INVISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                break;
            case 5:
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setVisibility(View.VISIBLE);
                color5_spinner.setVisibility(View.VISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                break;
            case 6:
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setVisibility(View.VISIBLE);
                color5_spinner.setVisibility(View.VISIBLE);
                color6_spinner.setVisibility(View.VISIBLE);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
