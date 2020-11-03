package com.damnervi.practice_02_calculator;

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
        numberInScreen = Double.parseDouble(display.getDisplay().getText().toString());
        if (waitingNumber == null) {
            waitingNumber = numberInScreen;
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
                waitingNumber = null;
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
        } else if (buttonID == R.id.buttonSubtract) {
            operation = "subtract";
            display.newWrite = true;
        } else if (buttonID == R.id.buttonMulti) {
            operation = "multi";
            display.newWrite = true;
        } else if (buttonID == R.id.buttonDivide) {
            operation = "divide";
            display.newWrite = true;
        }
    }

    public void resolveOperation() {
        double result = 0;
        if (operation != null) {
            if (operation.equals("add")) {
                result = numberInScreen + waitingNumber;
                display.writeOnScreen(String.valueOf(result));
            } else if (operation.equals("subtract")) {
                result = waitingNumber - numberInScreen;
                display.writeOnScreen(String.valueOf(result));
            } else if (operation.equals("multi")) {
                result = numberInScreen * waitingNumber;
                display.writeOnScreen(String.valueOf(result));
            } else if (operation.equals("divide")) {
                result = waitingNumber / numberInScreen;
                display.writeOnScreen(String.valueOf(result));
            }
            waitingNumber = result;
            operation = null;
            display.newWrite = true;
        }
    }
}