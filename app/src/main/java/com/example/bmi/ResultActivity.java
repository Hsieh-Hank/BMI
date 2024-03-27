package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView show = findViewById(R.id.tvShowbmi);
        ImageView img = findViewById(R.id.Ivshow);
        double bmi = getIntent().getDoubleExtra("bmi", 0);
        if (bmi < 18.5){
            show.setText("體重過輕");
            img.setImageResource(R.drawable.light);
        } else if (bmi <=24){
            show.setText("體重正常");
            img.setImageResource(R.drawable.normla);
        } else{
            show.setText("體重過重");
            img.setImageResource(R.drawable.fat);
            //55556
        }
    }
    public void goBack(View view){
        finish();
    }
}