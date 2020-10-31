package com.damnervi.practice_02_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //private TextView display;
    private Button b1;
    private Button b2;
    private Button br;
    private Calculator calculator;

    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        //display = findViewById(R.id.display);
        display = new Display(findViewById(R.id.display));
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        br = findViewById(R.id.remove);
        // b1.setOnClickListener(this);
        // b2.setOnClickListener(this);
        b1.setOnClickListener(display);
        b2.setOnClickListener(display);
        br.setOnClickListener(display);
    }

}