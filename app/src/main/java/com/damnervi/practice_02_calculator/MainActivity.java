package com.damnervi.practice_02_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int[] displayClassButtonsIDs = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonPoint, R.id.remove, R.id.clear};
    private final int[] calculatorClassButtonsIDs = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMulti, R.id.buttonDivide,
            R.id.result};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = new Display(findViewById(R.id.display), findViewById(R.id.displaysecondary));
        Calculator calculator = new Calculator(display);
        display.setCalculator(calculator);

        // Find and assign numeric buttons listeners
        for (int id : displayClassButtonsIDs) {
            findViewById(id).setOnClickListener(display);
        }
        // Find and assign operations buttons listener
        for (int id : calculatorClassButtonsIDs) {
            findViewById(id).setOnClickListener(calculator);
        }
    }
}