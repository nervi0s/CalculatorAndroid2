package com.damnervi.practice_02_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //private TextView display;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button br, bc;

    private Calculator calculator;

    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        //display = findViewById(R.id.display);
        display = new Display(findViewById(R.id.display));
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
        br = findViewById(R.id.remove);
        // b1.setOnClickListener(this);
        // b2.setOnClickListener(this);
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

        br.setOnClickListener(display);
    }

}