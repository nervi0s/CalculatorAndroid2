package com.damnervi.practice_02_calculator;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display implements View.OnClickListener {
    private TextView display;

    public Display(TextView t) {
        display = t;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (button.getId() == R.id.remove) {
            removeOne();
        } else {
            CharSequence cs = display.getText();
            cs = cs + button.getText().toString();
            display.setText(cs);
        }
    }

    public void removeOne() {
        CharSequence cs = display.getText();
        if (!TextUtils.isEmpty(cs)){
            cs = cs.subSequence(0, cs.length() - 1);
            display.setText(cs);
        }
    }

    public void clearScreen() {
        display.setText("");
    }
}
