package cm.mboasoftwares.roland.resistanceequivalente;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] anneau1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] anneau2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] multiplicator = {"1", "10", "100", "1000", "10000", "100000", "1000000", "10000000", "100000000", "1000000000", "0.1", "0.01"};
    private String[] tolerance = {"1%", "2%", "0.5%", "0.25%", "0.1%", "0.05%", "5%", "10%"};
    private String[] coeffTemp = {"100", "50", "15", "25" , "10", "5"};

    Spinner nbr_anneaux_spinner;
    Spinner color1_spinner;
    Spinner color2_spinner;
    Spinner color3_spinner;
    Spinner color4_spinner;
    Spinner color5_spinner;
    Spinner color6_spinner;

    int nbrNoeux = 3;

    String val1, val2, val3, val4, val5, val6;

    TextView result;
    Button evaluate;

    ArrayAdapter<CharSequence> colors_anneau2_adapter;
    ArrayAdapter<CharSequence> colors_anneau3_adapter;
    ArrayAdapter<CharSequence> colors_anneau4_adapter;

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

        result = (TextView) findViewById(R.id.result);
        evaluate = (Button) findViewById(R.id.evaluate_button);
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateResult();
            }
        });

        nbr_anneaux_spinner = (Spinner) findViewById(R.id.nbr_anneaux_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> nbr_anneaux_adapter = ArrayAdapter.createFromResource(this, R.array.nbr_anneaux_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> colors_anneau1_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau1_list_array, android.R.layout.simple_spinner_item);
        colors_anneau2_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau2_list_array, android.R.layout.simple_spinner_item);
        colors_anneau3_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau3_list_array, android.R.layout.simple_spinner_item);
        colors_anneau4_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau4_list_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> colors_anneau5_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau4_list_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> colors_anneau6_adapter = ArrayAdapter.createFromResource(this, R.array.colors_anneau6_list_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        nbr_anneaux_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau3_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau4_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau5_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors_anneau6_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        nbr_anneaux_spinner.setAdapter(nbr_anneaux_adapter);
        nbr_anneaux_spinner.setOnItemSelectedListener(this);

        color1_spinner = (Spinner) findViewById(R.id.color1_spinner);
        color1_spinner.setAdapter(colors_anneau1_adapter);
        color1_spinner.setVisibility(View.INVISIBLE);
        //color1_spinner.setOnItemSelectedListener(this);
        //color1_spinner.setLayoutParams(lp);

        color2_spinner = (Spinner) findViewById(R.id.color2_spinner);
        color2_spinner.setAdapter(colors_anneau2_adapter);
        color2_spinner.setVisibility(View.INVISIBLE);
        //color2_spinner.setOnItemSelectedListener(this);
        //color2_spinner.setLayoutParams(lp);

        color3_spinner = (Spinner) findViewById(R.id.color3_spinner);
        color3_spinner.setAdapter(colors_anneau3_adapter);
        color3_spinner.setVisibility(View.INVISIBLE);
        //color3_spinner.setOnItemSelectedListener(this);

        color4_spinner = (Spinner) findViewById(R.id.color4_spinner);
        color4_spinner.setAdapter(colors_anneau4_adapter);
        color4_spinner.setVisibility(View.INVISIBLE);
        //color4_spinner.setOnItemSelectedListener(this);

        color5_spinner = (Spinner) findViewById(R.id.color5_spinner);
        color5_spinner.setAdapter(colors_anneau5_adapter);
        color5_spinner.setVisibility(View.INVISIBLE);
        //color5_spinner.setOnItemSelectedListener(this);

        color6_spinner = (Spinner) findViewById(R.id.color6_spinner);
        color6_spinner.setAdapter(colors_anneau6_adapter);
        color6_spinner.setVisibility(View.INVISIBLE);
        //color1_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) parent.getSelectedItem();
        switch (Integer.valueOf(s)) {
            case 3:
                nbrNoeux = 3;
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setAdapter(colors_anneau3_adapter);
                color4_spinner.setVisibility(View.INVISIBLE);
                color5_spinner.setVisibility(View.INVISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                result.setText("");
                break;
            case 4:
                nbrNoeux = 4;
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setAdapter(colors_anneau3_adapter);
                color4_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setAdapter(colors_anneau4_adapter);
                color5_spinner.setVisibility(View.INVISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                result.setText("");
                break;
            case 5:
                nbrNoeux = 5;
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setAdapter(colors_anneau2_adapter);
                color4_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setAdapter(colors_anneau3_adapter);
                color5_spinner.setVisibility(View.VISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                result.setText("");
                break;
            case 6:
                nbrNoeux = 6;
                color1_spinner.setVisibility(View.VISIBLE);
                color2_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setVisibility(View.VISIBLE);
                color3_spinner.setAdapter(colors_anneau2_adapter);
                color4_spinner.setVisibility(View.VISIBLE);
                color4_spinner.setAdapter(colors_anneau3_adapter);
                color5_spinner.setVisibility(View.VISIBLE);
                color6_spinner.setVisibility(View.VISIBLE);
                result.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void evaluateResult() {
        switch (nbrNoeux) {
            case 3:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = multiplicator[color3_spinner.getSelectedItemPosition()];
                double v3 = Double.parseDouble(val1 + val2)*Double.parseDouble(val3);
                result.setText(String.valueOf(v3));
                break;
            case 4:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = multiplicator[color3_spinner.getSelectedItemPosition()];
                val4 = tolerance[color4_spinner.getSelectedItemPosition()];
                double v4 = Double.parseDouble(val1 + val2)*Double.parseDouble(val3);
                result.setText(String.valueOf(v4));
                break;
            case 5:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = anneau2[color3_spinner.getSelectedItemPosition()];
                val4 = multiplicator[color4_spinner.getSelectedItemPosition()];
                val5 = tolerance[color5_spinner.getSelectedItemPosition()];
                double v5 = Double.parseDouble(val1 + val2 + val3)*Double.parseDouble(val4);
                result.setText(String.valueOf(v5));
                break;
            case 6:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = anneau2[color3_spinner.getSelectedItemPosition()];
                val4 = multiplicator[color4_spinner.getSelectedItemPosition()];
                val5 = tolerance[color5_spinner.getSelectedItemPosition()];
                val6 = coeffTemp[color6_spinner.getSelectedItemPosition()];
                double v6 = Double.parseDouble(val1 + val2 + val3)*Double.parseDouble(val4);
                result.setText(String.valueOf(v6));
                break;
            default:
                break;
        }
    }
}
