package com.damnervi.practice_02_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bp;
    private Button br, bc;
    private Button buttonAdd, buttonSubtract, buttonMulti, buttonDivide;
    private Button buttonResult;
    private Calculator calculator;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = new Display(findViewById(R.id.display), findViewById(R.id.displaysecondary));
        calculator = new Calculator(display);
        display.setCalculator(calculator);
        // Numeric buttons
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bp = findViewById(R.id.buttonPoint);
        // Control buttons
        br = findViewById(R.id.remove);
        bc = findViewById(R.id.clear);
        // Operations buttons
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonResult = findViewById(R.id.result);

        // Numeric buttons
        b0.setOnClickListener(display);
        b1.setOnClickListener(display);
        b2.setOnClickListener(display);
        b3.setOnClickListener(display);
        b4.setOnClickListener(display);
        b5.setOnClickListener(display);
        b6.setOnClickListener(display);
        b7.setOnClickListener(display);
        b8.setOnClickListener(display);
        b9.setOnClickListener(display);
        bp.setOnClickListener(display);
        // Control buttons
        br.setOnClickListener(display);
        bc.setOnClickListener(display);
        // Operations buttons
        buttonAdd.setOnClickListener(calculator);
        buttonSubtract.setOnClickListener(calculator);
        buttonMulti.setOnClickListener(calculator);
        buttonDivide.setOnClickListener(calculator);
        buttonResult.setOnClickListener(calculator);
    }
}