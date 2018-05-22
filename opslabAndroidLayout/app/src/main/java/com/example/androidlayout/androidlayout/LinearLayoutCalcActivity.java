package com.example.androidlayout.androidlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LinearLayoutCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_calc);

        View.OnClickListener strBtn_ClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "";
                switch (view.getId()) {
                    case R.id.btn0:
                        value = "0";
                        break;
                    case R.id.btn1:
                        value = "1";
                        break;
                    case R.id.btn2:
                        value = "2";
                        break;
                    case R.id.btn3:
                        value = "3";
                        break;
                    case R.id.btn4:
                        value = "4";
                        break;
                    case R.id.btn5:
                        value = "5";
                        break;
                    case R.id.btn6:
                        value = "6";
                        break;
                    case R.id.btn7:
                        value = "7";
                        break;
                    case R.id.btn8:
                        value = "8";
                        break;
                    case R.id.btn9:
                        value = "9";
                        break;
                    case R.id.btnDot:
                        value = ".";
                        break;
                    case R.id.btnAdd:
                        value ="+";
                        break;
                    case R.id.btnSub:
                        value = "-";
                        break;
                    case R.id.btnDivision:
                        value ="/";
                        break;
                    case R.id.btnMultiplication:
                        value = "*";
                        break;
                    default:
                        value = "";
                        break;
                }
                TextView textViewresult = findViewById(R.id.textResult);
                String values = (String) textViewresult.getText();
                if("0".equals(values)){
                    textViewresult.setText(value);
                }else{
                    textViewresult.setText(values+value);
                }

            }
        };
        Button button0 = findViewById(R.id.btn0);
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        Button button4 = findViewById(R.id.btn4);
        Button button5 = findViewById(R.id.btn5);
        Button button6 = findViewById(R.id.btn6);
        Button button7 = findViewById(R.id.btn7);
        Button button8 = findViewById(R.id.btn8);
        Button button9 = findViewById(R.id.btn9);
        Button buttonDot = findViewById(R.id.btnDot);
        button0.setOnClickListener(strBtn_ClickListener);
        button1.setOnClickListener(strBtn_ClickListener);
        button2.setOnClickListener(strBtn_ClickListener);
        button3.setOnClickListener(strBtn_ClickListener);
        button4.setOnClickListener(strBtn_ClickListener);
        button5.setOnClickListener(strBtn_ClickListener);
        button6.setOnClickListener(strBtn_ClickListener);
        button7.setOnClickListener(strBtn_ClickListener);
        button8.setOnClickListener(strBtn_ClickListener);
        button9.setOnClickListener(strBtn_ClickListener);
        buttonDot.setOnClickListener(strBtn_ClickListener);


    }
}
