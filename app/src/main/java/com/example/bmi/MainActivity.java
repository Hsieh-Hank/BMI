package com.example.bmi;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    private EditText height;
    private EditText weight;
    private RadioGroup RgSex;
    private RadioButton RbMale;
    private RadioButton RbFemale;
    private CheckBox cbApple;
    private CheckBox cbBanana;
    private CheckBox cbOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        Listener();
    }

    private void Listener() {
        RgSex.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.RbMale) {
                show.setText("男生");
            } else {
                show.setText("女生");
            }
        });

        cbApple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getFruit();
            }
        });
        cbBanana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getFruit();
            }
        });
        cbOrange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getFruit();
            }
        });
    }

    private void getFruit() {
        String msg = "";
        if (cbApple.isChecked()) {
            msg += "蘋果";
        }
        if (cbBanana.isChecked()) {
            msg += "香蕉";
        }
        if (cbOrange.isChecked()) {
            msg += "橘子";
        }
        show.setText("我喜歡" + msg);
    }

    public void showDiaLog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BMI");
        double bmi = getBmi();
        String result  = getString(R.string.StrShowbmi) + bmi;
        builder.setMessage(result);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }

    public void calcMBI(View view) {
        double bmi = getBmi();
        String result  = getString(R.string.StrShowbmi) + String.valueOf(bmi);
//        show.setText(result);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
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
        RgSex = findViewById(R.id.RgSex);
        cbApple = findViewById(R.id.cbApple);
        cbBanana = findViewById(R.id.cbBanana);
        cbOrange = findViewById(R.id.cbOrange);
    }
}
