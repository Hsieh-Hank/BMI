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
    private String[] sex = {"男生", "女生"};
    private int sexSeclect = 0;
    private  String[] fruits = {"蘋果", "香蕉", "橘子"};

    private boolean[] fruitseclect = {false, false, false};

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

        //顯示訊息
//        builder.setMessage(result);

        //顯示單選
//        builder.setSingleChoiceItems(sex, sexSeclect, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                sexSeclect = which;
//            }
//        });
//         顯示多選
        builder.setMultiChoiceItems(fruits, fruitseclect, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                fruitseclect[which] = isChecked;
            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, sex[sexSeclect], Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = "";
                for(int i = 0; i < fruitseclect.length; i++) {
                    if (fruitseclect[i]) {
                        msg += fruits[i];
                    }
                }

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
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
