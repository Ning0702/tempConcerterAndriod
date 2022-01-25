package com.hfad.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText input;
    private TextView result;
    private TextView history;
    private RadioGroup radioGroup;
    private Boolean fToC = true;
    private TextView show1;
    private TextView show2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind var's to the screen widgets
        input = findViewById(R.id.inputNum);
        result = findViewById(R.id.resultTextView);
        history = findViewById(R.id.conversionHistory);
        radioGroup = findViewById(R.id.radioGroup);
        show1 = findViewById(R.id.showTextView1);
        show2 = findViewById(R.id.showTextView2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fToC:
                        fToC = true;
                        break;
                    case R.id.cToF:
                        fToC = false;
                        break;
                }
            }
        });

        history.setMovementMethod(new ScrollingMovementMethod());//scroll

        if(fToC == true) {
            show1.setText("Fahrenheit to Celsius:");
            show2.setText("Celsius to Fahrenheit:");
        }
        else{
            show1.setText("Celsius to Fahrenheit:");
            show2.setText("Fahrenheit to Celsius:");
        }
    }

    //radioButton
    public void fToC(View v){
        show1.setText("Fahrenheit to Celsius:");
        show2.setText("Celsius to Fahrenheit:");
    }

    //radioButton
    public void cToF(View v){
        show1.setText("Celsius to Fahrenheit:");
        show2.setText("Fahrenheit to Celsius:");
    }

    public void convertButton(View v){
        double temp = Double.parseDouble(input.getText().toString());
        double newTemp;

        if(fToC == true){
            newTemp = (temp -32.0)/1.8;
            newTemp = (double) (Math.round(newTemp*10)/10.0);
            String historyText = history.getText().toString();
            history.setText(temp + " F ==> " + newTemp + " C" + "\n" +  historyText  );
        }
        else{
            newTemp = (temp * 1.8) + 32;
            newTemp = (double) (Math.round(newTemp*10)/10.0);
            String historyText = history.getText().toString();
            history.setText(temp + " C ==> " + newTemp + " F" + "\n" +  historyText  );
        }
        result.setText(newTemp + "");

    }

    public void clearButtonFC(View v){
        history.setText("");
        input.setText("");
        result.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        outState.putString("INPUT", input.getText().toString());
        outState.putString("RESULT", result.getText().toString());
        outState.putString("HISTORY", history.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        //call super first
        super.onRestoreInstanceState(saveInstanceState);

        input.setText(saveInstanceState.getString("INPUT"));
        result.setText(saveInstanceState.getString("RESULT"));
        history.setText(saveInstanceState.getString("HISTORY"));

        if(fToC == true) {
            show1.setText("Fahrenheit to Celsius:");
            show2.setText("Celsius to Fahrenheit:");
        }
        else{
            show1.setText("Celsius to Fahrenheit:");
            show2.setText("Fahrenheit to Celsius:");
        }

    }

}
