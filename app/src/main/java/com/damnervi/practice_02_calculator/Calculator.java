package com.damnervi.practice_02_calculator;

import android.view.View;
import android.widget.Button;

public class Calculator implements View.OnClickListener {

    private final Display display;

    private Double inScreenNumber = null;
    private Double waitingNumber = null;
    private String operation = null;

    // The following members variables are used to avoid errors controlling the flow of the users inputs
    protected boolean operationResolved = false;
    protected boolean buttonEqualsPressed = false;
    protected boolean buttonOperationPressed = false;

    // Constructor to initialize this object with Display data type
    public Calculator(Display display) {
        this.display = display;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int buttonID = button.getId();

        if (!display.isEmptyScreen()) {
            assignValues();
            if (buttonID == R.id.result) {
                buttonEqualsPressed = true;
                display.writeOnSecondaryScreen(""); // To remove the secondary screen when equals button is pressed
                inScreenNumber = Double.parseDouble(display.readMainDisplay());
                resolveOperation();
                resetValues();
            } else {
                if (display.allowWriteInScreenNumber) {
                    inScreenNumber = Double.parseDouble(display.readMainDisplay());
                    display.allowWriteInScreenNumber = false;
                }
                buttonEqualsPressed = false;
                buttonOperationPressed = true;
                display.writeOnSecondaryScreen(display.readMainDisplay());
                setOperation(buttonID);
            }
        }
    }

    public void setWaitingNumber(Double d) {
        waitingNumber = d;
    }

    public Double getInScreenNumber() {
        return inScreenNumber;
    }

    public Double getWaitingNumber() {
        return waitingNumber;
    }

    public void assignValues() {
        if (waitingNumber == null) {
            waitingNumber = Double.parseDouble(display.readMainDisplay());
        }
    }

    public void setOperation(int buttonID) {
        if (inScreenNumber != null) {
            resolveOperation();

        }
        if (buttonID == R.id.buttonAdd) {
            operation = "+";
            display.overWritable = true;
        } else if (buttonID == R.id.buttonSubtract) {
            operation = "-";
            display.overWritable = true;
        } else if (buttonID == R.id.buttonMulti) {
            operation = "×";
            display.overWritable = true;
        } else if (buttonID == R.id.buttonDivide) {
            operation = "÷";
            display.overWritable = true;
        }

        display.writeOnSecondaryScreen(display.readMainDisplay() + operation);
        if (buttonEqualsPressed) {
            display.writeOnSecondaryScreen("");
        }
    }

    public void resolveOperation() {
        double result = 0;
        if (operation != null && inScreenNumber != null) {
            switch (operation) {
                case "+":
                    result = inScreenNumber + waitingNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "-":
                    result = waitingNumber - inScreenNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "×":
                    result = inScreenNumber * waitingNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "÷":
                    result = waitingNumber / inScreenNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
            }
            waitingNumber = result;
            inScreenNumber = null;
            display.allowWriteInScreenNumber = false;
            operation = null;
            operationResolved = true;
            display.overWritable = true;
        }
    }

    public void resetValues() {
        inScreenNumber = null;
        waitingNumber = null;
        operation = null;
        operationResolved = false;
        buttonEqualsPressed = false;
        buttonOperationPressed = false;
    }
}