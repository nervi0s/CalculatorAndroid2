package com.damnervi.practice_02_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display implements View.OnClickListener {

    private final TextView mainDisplay;
    private final TextView secondaryDisplay;
    private Calculator calculator;
    protected boolean overWritable = true;

    // Constructor
    public Display(TextView mainDisplay, TextView secondaryDisplay) {
        this.mainDisplay = mainDisplay;
        this.secondaryDisplay = secondaryDisplay;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int buttonID = button.getId();

        if (buttonID == R.id.remove) {
            removeOneDigit();
        } else if (buttonID == R.id.clear) {
            clearScreen();
        } else {
            CharSequence cs = button.getText();
            screenController(cs);
        }
    }

    // Setter to communicate with Calculator object
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public String readMainDisplay() {
        return mainDisplay.getText().toString();
    }

    public void writeOnMainScreen(CharSequence cs) {
        mainDisplay.setText(cs);
    }

    public void writeOnSecondaryScreen(CharSequence cs) {
        secondaryDisplay.setText(cs);
    }

    // Method to control the information that appears in the main screen
    public void screenController(CharSequence sequence) {
        String textInScreen = readMainDisplay();
        boolean isPointPresent = textInScreen.contains(".");
        boolean isTryingAddPoint = sequence.toString().contentEquals(".");
        boolean startWithZero = textInScreen.contentEquals("0");
        // Statements used to avoid errors controlling the flow of the users inputs
        if (calculator.operationResolved && calculator.buttonEqualPressed && calculator.buttonOperationPressed) {
            calculator.setWaitingNumber(null);
            calculator.operationResolved = false;
            calculator.buttonEqualPressed = false;
            calculator.buttonOperationPressed = false;
        }

        if (isEmptyScreen() || overWritable) { // Initialize the screen if it's empty or writable
            if (isTryingAddPoint) { // Set 0. if the first pressed button is point
                writeOnMainScreen("0.");
            } else {
                writeOnMainScreen(sequence);
            }
        } else {
            if (isPointPresent && isTryingAddPoint) { // Avoid points repetitions
                Log.i("msgInfo", "Point already present");
            } else if (startWithZero) { // Avoid left zeros
                if (isTryingAddPoint) {
                    writeOnMainScreen("0.");
                } else {
                    writeOnMainScreen(sequence);
                }
            } else {
                String toDisplay = textInScreen + sequence;
                writeOnMainScreen(toDisplay);
            }
        }
        overWritable = false;
    }

    public void removeOneDigit() {
        String textInScreen = readMainDisplay();
        if (!isEmptyScreen()) {
            textInScreen = textInScreen.substring(0, textInScreen.length() - 1);
            writeOnMainScreen(textInScreen);
        }
    }

    public void clearScreen() {
        writeOnMainScreen("");
    }

    public boolean isEmptyScreen() {
        String textInScreen = mainDisplay.getText().toString();
        return textInScreen.isEmpty();
    }
}