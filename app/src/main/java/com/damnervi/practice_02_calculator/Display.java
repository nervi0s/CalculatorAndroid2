package com.damnervi.practice_02_calculator;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display implements View.OnClickListener {
    private final TextView display;

    public Display(TextView t) {
        display = t;
    }

    private void showInScreen(CharSequence sequence) {
        String textInScreen = display.getText().toString();

        if (TextUtils.isEmpty(textInScreen)) { // If screen is empty write on it
            display.setText(sequence);
        } else {
            if (!textInScreen.contentEquals("0") || !sequence.toString().contentEquals("0")) { // Avoid left zeros
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
        } else {
            CharSequence cs = button.getText();
            showInScreen(cs);
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
}
