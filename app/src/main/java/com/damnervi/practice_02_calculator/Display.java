package com.damnervi.practice_02_calculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display implements View.OnClickListener {

    private final TextView display;
    protected boolean newWrite = true;

    public Display(TextView t) {
        display = t;
    }

    public TextView getDisplay() {
        return display;
    }

    public void screenController(CharSequence sequence) {

        String textInScreen = display.getText().toString();
        boolean isPointPresent = textInScreen.contains(".");
        boolean isTryingAddPoint = sequence.toString().contentEquals(".");
        boolean startWithZero = textInScreen.contentEquals("0");
        boolean isSymbolPresent = textInScreen.matches("[+x/#-]");

        if (isEmptyScreen() || newWrite) { // Initialize the screen if It's empty
            if (isTryingAddPoint) { // Set 0. if the first pressed button is point
                writeOnScreen("0.");
            } else {
                writeOnScreen(sequence);
            }
        } else {
            if (isSymbolPresent) {
                if (isTryingAddPoint) {
                    writeOnScreen("0.");
                } else {
                    writeOnScreen(sequence);
                }
            } else if (isPointPresent && isTryingAddPoint) { // Avoid points repetitions
                Log.i("msgInfo", "Point already present");
            } else if (startWithZero) { // Avoid left zeros
                if (isTryingAddPoint) {
                    writeOnScreen("0.");
                } else {
                    writeOnScreen(sequence);
                }
            } else {
                String toDisplay = textInScreen + sequence;
                writeOnScreen(toDisplay);
            }
        }
        newWrite = false;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int buttonID = button.getId();

        if (buttonID == R.id.remove) {
            removeOne();
        } else if (buttonID == R.id.clear) {
            clearScreen();
        } else {
            CharSequence cs = button.getText();
            screenController(cs);
        }
    }

    public void removeOne() {
        String textInScreen = display.getText().toString();
        boolean isSymbolPresent = textInScreen.toString().matches("[+x/#-]");
        if (!isEmptyScreen() && !isSymbolPresent) {
            textInScreen = textInScreen.substring(0, textInScreen.length() - 1);
            writeOnScreen(textInScreen);
        }
    }

    public void clearScreen() {
        String textInScreen = display.getText().toString();
        boolean isSymbolPresent = textInScreen.matches("[+x/#-]");
        if (!isSymbolPresent) {
            writeOnScreen("");
        }
    }

    public boolean isEmptyScreen() {
        String textInScreen = display.getText().toString();
        return textInScreen.isEmpty();
    }

    public void writeOnScreen(CharSequence cs) {
        display.setText(cs);
    }
}