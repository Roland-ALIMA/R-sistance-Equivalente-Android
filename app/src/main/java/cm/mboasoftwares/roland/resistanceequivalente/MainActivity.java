package cm.mboasoftwares.roland.resistanceequivalente;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.widget.RelativeLayout.BELOW;
import static android.widget.RelativeLayout.END_OF;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] anneau1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] anneau2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] multiplicator = {"1", "10", "100", "1000", "10000", "100000", "1000000", "10000000", "100000000", "1000000000", "0.1", "0.01"};
    private String[] tolerance = {"1 %", "2 %", "0.5 %", "0.25 %", "0.1 %", "0.05 %", "5 %", "10 %"};
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

    TextView resultText;
    TextView result;
    TextView resultUnit;
    TextView toleranceText;
    TextView toleranceTv;
    TextView coefTempText;
    TextView coefTempTv;
    TextView coefTempUnit;
    Button evaluate;

    TextView band1Text;
    TextView band2Text;
    TextView band3Text;
    TextView band4Text;
    TextView band5Text;
    TextView band6Text;

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

        RelativeLayout.LayoutParams blp1 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp1.addRule(BELOW, R.id.colors_choice_text);
        blp1.addRule(Gravity.CENTER);

        RelativeLayout.LayoutParams blp2 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp2.addRule(BELOW, R.id.colors_choice_text);
        blp2.addRule(Gravity.CENTER);
        blp2.addRule(END_OF, R.id.band1_text);

        RelativeLayout.LayoutParams blp3 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp3.addRule(BELOW, R.id.colors_choice_text);
        blp3.addRule(Gravity.CENTER);
        blp3.addRule(END_OF, R.id.band2_text);

        RelativeLayout.LayoutParams blp4 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp4.addRule(BELOW, R.id.color1_spinner);
        blp4.addRule(Gravity.CENTER);

        RelativeLayout.LayoutParams blp5 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp5.addRule(BELOW, R.id.color1_spinner);
        blp5.addRule(Gravity.CENTER);
        blp5.addRule(END_OF, R.id.band4_text);

        RelativeLayout.LayoutParams blp6 = new RelativeLayout.LayoutParams(width/3, ViewGroup.LayoutParams.WRAP_CONTENT);
        blp6.addRule(BELOW, R.id.color1_spinner);
        blp6.addRule(Gravity.CENTER);
        blp6.addRule(END_OF, R.id.band5_text);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(width/3, 150);
        lp1.addRule(BELOW, R.id.band1_text);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(width/3, 150);
        lp2.addRule(BELOW, R.id.band1_text);
        lp2.addRule(END_OF, R.id.color1_spinner);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(width/3, 150);
        lp3.addRule(BELOW, R.id.band1_text);
        lp3.addRule(END_OF, R.id.color2_spinner);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(width/3, 150);
        lp4.addRule(BELOW, R.id.band4_text);
        //lp4.addRule(END_OF, R.id.color2_spinner);

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(width/3, 150);
        lp5.addRule(BELOW, R.id.band4_text);
        lp5.addRule(END_OF, R.id.color4_spinner);

        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams(width/3, 150);
        lp6.addRule(BELOW, R.id.band4_text);
        lp6.addRule(END_OF, R.id.color5_spinner);

        band1Text = findViewById(R.id.band1_text);
        band1Text.setLayoutParams(blp1);

        band2Text = findViewById(R.id.band2_text);
        band2Text.setLayoutParams(blp2);

        band3Text = findViewById(R.id.band3_text);
        band3Text.setLayoutParams(blp3);

        band4Text = findViewById(R.id.band4_text);
        band4Text.setLayoutParams(blp4);

        band5Text = findViewById(R.id.band5_text);
        band5Text.setLayoutParams(blp5);

        band6Text = findViewById(R.id.band6_text);
        band6Text.setLayoutParams(blp6);

        toleranceTv = (TextView) findViewById(R.id.tolerance_value);
        toleranceText = (TextView) findViewById(R.id.tolerance_text);

        coefTempText = (TextView) findViewById(R.id.coefTempe_text);
        coefTempTv = (TextView) findViewById(R.id.coefTempe_value);
        coefTempUnit = (TextView) findViewById(R.id.coefTempe_unit);

        resultText = findViewById(R.id.result_text);
        resultUnit = findViewById(R.id.result_unit);
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
        color1_spinner.setLayoutParams(lp1);

        color2_spinner = (Spinner) findViewById(R.id.color2_spinner);
        color2_spinner.setAdapter(colors_anneau2_adapter);
        color2_spinner.setVisibility(View.INVISIBLE);
        //color2_spinner.setOnItemSelectedListener(this);
        color2_spinner.setLayoutParams(lp2);

        color3_spinner = (Spinner) findViewById(R.id.color3_spinner);
        color3_spinner.setAdapter(colors_anneau3_adapter);
        color3_spinner.setVisibility(View.INVISIBLE);
        //color3_spinner.setOnItemSelectedListener(this);
        color3_spinner.setLayoutParams(lp3);

        color4_spinner = (Spinner) findViewById(R.id.color4_spinner);
        color4_spinner.setAdapter(colors_anneau4_adapter);
        color4_spinner.setVisibility(View.INVISIBLE);
        //color4_spinner.setOnItemSelectedListener(this);
        color4_spinner.setLayoutParams(lp4);

        color5_spinner = (Spinner) findViewById(R.id.color5_spinner);
        color5_spinner.setAdapter(colors_anneau5_adapter);
        color5_spinner.setVisibility(View.INVISIBLE);
        //color5_spinner.setOnItemSelectedListener(this);
        color5_spinner.setLayoutParams(lp5);

        color6_spinner = (Spinner) findViewById(R.id.color6_spinner);
        color6_spinner.setAdapter(colors_anneau6_adapter);
        color6_spinner.setVisibility(View.INVISIBLE);
        //color1_spinner.setOnItemSelectedListener(this);
        color6_spinner.setLayoutParams(lp6);
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
                resultText.setVisibility(View.INVISIBLE);
                resultUnit.setVisibility(View.INVISIBLE);
                result.setVisibility(View.INVISIBLE);
                result.setText("");
                toleranceText.setVisibility(View.INVISIBLE);
                toleranceTv.setVisibility(View.INVISIBLE);
                coefTempText.setVisibility(View.INVISIBLE);
                coefTempTv.setVisibility(View.INVISIBLE);
                coefTempUnit.setVisibility(View.INVISIBLE);
                band4Text.setVisibility(View.INVISIBLE);
                band5Text.setVisibility(View.INVISIBLE);
                band6Text.setVisibility(View.INVISIBLE);
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
                band4Text.setVisibility(View.VISIBLE);
                band5Text.setVisibility(View.INVISIBLE);
                band6Text.setVisibility(View.INVISIBLE);
                resultText.setVisibility(View.INVISIBLE);
                resultUnit.setVisibility(View.INVISIBLE);
                result.setVisibility(View.INVISIBLE);
                result.setText("");
                toleranceText.setVisibility(View.INVISIBLE);
                toleranceTv.setVisibility(View.INVISIBLE);
                toleranceTv.setText("");
                coefTempText.setVisibility(View.INVISIBLE);
                coefTempTv.setVisibility(View.INVISIBLE);
                coefTempUnit.setVisibility(View.INVISIBLE);
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
                band4Text.setVisibility(View.VISIBLE);
                band5Text.setVisibility(View.VISIBLE);
                band6Text.setVisibility(View.INVISIBLE);
                color6_spinner.setVisibility(View.INVISIBLE);
                resultText.setVisibility(View.INVISIBLE);
                resultUnit.setVisibility(View.INVISIBLE);
                result.setVisibility(View.INVISIBLE);
                result.setText("");
                toleranceText.setVisibility(View.INVISIBLE);
                toleranceTv.setVisibility(View.INVISIBLE);
                toleranceTv.setText("");
                coefTempText.setVisibility(View.INVISIBLE);
                coefTempTv.setVisibility(View.INVISIBLE);
                coefTempUnit.setVisibility(View.INVISIBLE);
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
                band4Text.setVisibility(View.VISIBLE);
                band5Text.setVisibility(View.VISIBLE);
                band6Text.setVisibility(View.VISIBLE);
                resultText.setVisibility(View.INVISIBLE);
                resultUnit.setVisibility(View.INVISIBLE);
                result.setVisibility(View.INVISIBLE);
                result.setText("");
                toleranceText.setVisibility(View.INVISIBLE);
                toleranceTv.setVisibility(View.INVISIBLE);
                toleranceTv.setText("");
                coefTempText.setVisibility(View.INVISIBLE);
                coefTempTv.setVisibility(View.INVISIBLE);
                coefTempUnit.setVisibility(View.INVISIBLE);
                coefTempTv.setText("");
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
                resultText.setVisibility(View.VISIBLE);
                resultUnit.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                double v3 = Double.parseDouble(val1 + val2)*Double.parseDouble(val3);
                result.setText(String.valueOf(v3));
                break;
            case 4:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = multiplicator[color3_spinner.getSelectedItemPosition()];
                val4 = tolerance[color4_spinner.getSelectedItemPosition()];
                resultText.setVisibility(View.VISIBLE);
                resultUnit.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                toleranceText.setVisibility(View.VISIBLE);
                toleranceTv.setVisibility(View.VISIBLE);
                double v4 = Double.parseDouble(val1 + val2)*Double.parseDouble(val3);
                result.setText(String.valueOf(v4));
                String tol4 = "\u00B1 " + val4;
                toleranceTv.setText(tol4);
                break;
            case 5:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = anneau2[color3_spinner.getSelectedItemPosition()];
                val4 = multiplicator[color4_spinner.getSelectedItemPosition()];
                val5 = tolerance[color5_spinner.getSelectedItemPosition()];
                resultText.setVisibility(View.VISIBLE);
                resultUnit.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                toleranceText.setVisibility(View.VISIBLE);
                toleranceTv.setVisibility(View.VISIBLE);
                double v5 = Double.parseDouble(val1 + val2 + val3)*Double.parseDouble(val4);
                result.setText(String.valueOf(v5));
                String tol5 = "\u00B1 " + val5;
                toleranceTv.setText(tol5);
                break;
            case 6:
                val1 = anneau1[color1_spinner.getSelectedItemPosition()];
                val2 = anneau2[color2_spinner.getSelectedItemPosition()];
                val3 = anneau2[color3_spinner.getSelectedItemPosition()];
                val4 = multiplicator[color4_spinner.getSelectedItemPosition()];
                val5 = tolerance[color5_spinner.getSelectedItemPosition()];
                val6 = coeffTemp[color6_spinner.getSelectedItemPosition()];
                resultText.setVisibility(View.VISIBLE);
                resultUnit.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                toleranceText.setVisibility(View.VISIBLE);
                toleranceTv.setVisibility(View.VISIBLE);
                coefTempText.setVisibility(View.VISIBLE);
                coefTempTv.setVisibility(View.VISIBLE);
                coefTempUnit.setVisibility(View.VISIBLE);
                double v6 = Double.parseDouble(val1 + val2 + val3)*Double.parseDouble(val4);
                result.setText(String.valueOf(v6));
                String tol6 = "\u00B1 " + val5;
                toleranceTv.setText(tol6);
                coefTempTv.setText(val6);
                break;
            default:
                break;
        }
    }
}
