package com.damnervi.practice_02_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Calculator implements View.OnClickListener {

    protected Double numberInScreen = null;
    protected Double waitingNumber = null;
    protected String operation = null;

    private final Display display;

    public Calculator(Display display) {
        this.display = display;
    }

    public void assignValues() {
        String textInScreen = display.getDisplay().getText().toString();
        if (isNumber(textInScreen)) { // texto que hay en ella es un numero
            numberInScreen = Double.parseDouble(display.getDisplay().getText().toString());
            if (waitingNumber == null) {
                waitingNumber = numberInScreen;
            }
            Log.i("msgInfo", " " + numberInScreen + " " + waitingNumber);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int buttonID = button.getId();

        if (!display.isEmptyScreen()) {
            assignValues();
            if (buttonID == R.id.result) {
                resolveOperation();
                Log.i("msgInfo", "Button = pulsed");
            } else {
                setOperation(buttonID);
            }
        }
    }

    public void setOperation(int buttonID) {
        resolveOperation();
        if (buttonID == R.id.buttonAdd) {
            operation = "add";
            display.newWrite = true;
        } else if (buttonID == R.id.buttonMulti) {
            operation = "multi";
            display.newWrite = true;
        }
    }

    public void resolveOperation() {
        Log.i("msgInfo", " " + numberInScreen + " " + waitingNumber);
        if (operation != null) {
            if (operation.equals("add")) {

                display.getDisplay().setText("" + (numberInScreen + waitingNumber));
                waitingNumber = numberInScreen + waitingNumber;
                operation = null;

            } else if (operation.equals("multi")) {
                display.getDisplay().setText("" + (numberInScreen * waitingNumber));
                waitingNumber = numberInScreen * waitingNumber;
                operation = null;
            }
            display.newWrite = true;
        }
    }

    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
