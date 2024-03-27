package com.example.bmi;

import com.example.bmi.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    private EditText height;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    public void calcMBI(View view) {


        double bmi = getBmi();
        String result  = getString(R.string.StrShowbmi) + String.valueOf(bmi);
        show.setText(result);
    }

    private double getBmi() {
        DecimalFormat df = new DecimalFormat("#.00");
        double h = Double.parseDouble(height.getText().toString());
        double w = Double.parseDouble(weight.getText().toString());
        double bmi = w / (h/100.0 * h/100.0);
        bmi = Double.parseDouble(df.format(bmi));
        return bmi;
    }

    public void GoNext(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        double bmi = getBmi();
        intent.putExtra("bmi", bmi);
        startActivity(intent);
    }


    private void findViews() {
        height = findViewById(R.id.etHeight);
        weight = findViewById(R.id.etWeight);
        show = findViewById(R.id.tvShow);
    }
}
