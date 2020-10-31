package com.damnervi.practice_02_calculator;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display implements View.OnClickListener {

    protected final TextView display;

    public Display(TextView t) {
        display = t;
    }

    public TextView getDisplay() {
        return display;
    }

    public void showScreenController(CharSequence sequence) {
        String textInScreen = display.getText().toString();

        if (TextUtils.isEmpty(textInScreen)) { // If screen is empty write on it
            if (sequence.toString().contentEquals(".")) { // Set 0. if the first pressed button is point
                display.setText("0.");
            } else {
                display.setText(sequence);
            }
        } else {
            boolean isPointPresent = textInScreen.contains(".");
            boolean isTryingAddPoint = sequence.toString().contentEquals(".");
            boolean startWithZero = textInScreen.contentEquals("0");
            boolean isTryingAddZero = sequence.toString().contentEquals("0");
            // toDO: Add method to remove any symbol (+,-,*,/) if one of them is present before show a numbers in screen
            if (isPointPresent && isTryingAddPoint) { // Avoid points repetitions
                Log.i("msgInfo", "Point already present");
            } else if (!startWithZero || !isTryingAddZero) { // Avoid left zeros
                String toDisplay = textInScreen + sequence;
                display.setText(toDisplay);
            }
        }
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
            showScreenController(cs);
        }
    }

    public void removeOne() {
        CharSequence cs = display.getText();
        if (!TextUtils.isEmpty(cs)) {
            cs = cs.subSequence(0, cs.length() - 1);
            display.setText(cs);
        }
    }

    public void clearScreen() {
        display.setText("");
    }

    public boolean isEmptyScreen() {
        String textInScreen = display.getText().toString();
        return textInScreen.isEmpty();
    }
}
