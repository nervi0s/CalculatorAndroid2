package com.damnervi.practice_02_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Calculator implements View.OnClickListener {

    private double number1;
    private double number2;
    private String operation;
    private final Display display;

    public Calculator(Display display) {
        this.display = display;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int buttonID = button.getId();

        if (!display.isEmptyScreen()) { // Do it if the screen isn't empty
            try {
                number1 = Double.parseDouble(display.getDisplay().getText().toString());
            } catch (NumberFormatException e) {
                Log.i("msgInfo", "Trying to parse a symbol: " + e.getMessage());
            }
            if (buttonID == R.id.buttonAdd) {
                operation = "add";
                number2 = number1;
                display.getDisplay().setText("+");
            }
        }
    }
}
