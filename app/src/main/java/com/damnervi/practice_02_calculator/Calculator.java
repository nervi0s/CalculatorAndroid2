package com.damnervi.practice_02_calculator;

import android.view.View;
import android.widget.Button;

public class Calculator implements View.OnClickListener {

    private final Display display;
    private Double numberInScreen = null;
    private Double waitingNumber = null;
    private String operation = null;
    // The following members variables are used to avoid errors controlling the flow of the users inputs
    protected boolean operationResolved = false;
    protected boolean buttonEqualPressed = false;
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
                buttonEqualPressed = true;
                display.writeOnSecondaryScreen(""); // To remove the secondary screen when equals button is pressed
                resolveOperation();
            } else {
                buttonEqualPressed = false;
                buttonOperationPressed = true;
                display.writeOnSecondaryScreen(display.readMainDisplay());
                setOperation(buttonID);
            }
        }
    }

    public void setWaitingNumber(Double d) {
        waitingNumber = d;
    }

    public void assignValues() {
        numberInScreen = Double.parseDouble(display.readMainDisplay());
        if (waitingNumber == null) {
            waitingNumber = numberInScreen;
        }
    }

    public void setOperation(int buttonID) {
        resolveOperation();
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
        if (buttonEqualPressed) {
            display.writeOnSecondaryScreen("");
        }
    }

    public void resolveOperation() {
        double result = 0;
        if (operation != null) {
            switch (operation) {
                case "+":
                    result = numberInScreen + waitingNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "-":
                    result = waitingNumber - numberInScreen;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "×":
                    result = numberInScreen * waitingNumber;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
                case "÷":
                    result = waitingNumber / numberInScreen;
                    display.writeOnMainScreen(String.valueOf(result));
                    break;
            }
            waitingNumber = result;
            operation = null;
            operationResolved = true;
            display.overWritable = true;
        }
    }
}