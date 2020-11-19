package com.damnervi.practice_02_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display1;
    private TextView display2;

    private String number1String;
    private String number2String;
    private String operation;

    private boolean pointPresent;

    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display1 = findViewById(R.id.displaysecondary);
        display2 = findViewById(R.id.display);

        number1String = "";
        number2String = "";
        operation = "";
        pointPresent = false;

    }

    public void numberPressed(View v) {
        Button button = (Button) v;
        boolean onlyZeroInNumber1 = number1String.contentEquals("0");
        boolean onlyZeroInNumber2 = number2String.contentEquals("0");
        boolean isTryingAddZero = button.getText().toString().equals("0");

        if (operation.isEmpty()) {
            if (!onlyZeroInNumber1 || !isTryingAddZero) {
                number1String = onlyZeroInNumber1 ? (String) button.getText() : number1String + button.getText();
                writeOnDisplay1(number1String);
            }
        } else {
            if (!onlyZeroInNumber2 || !isTryingAddZero) {
                number2String = onlyZeroInNumber2 ? (String) button.getText() : number2String + button.getText();
                writeOnDisplay1(number1String + operation + number2String);
            }
        }

        if (!display2.getText().toString().isEmpty()) {
            writeOnDisplay2("");
        }
    }

    public void operatorPressed(View v) {
        Button button = (Button) v;
        resolve(v);
        if (!number1String.isEmpty()) {
            operation = (String) button.getText();
            pointPresent = false;
            writeOnDisplay1(number1String + operation);
        } else {
            if (!display2.getText().toString().isEmpty()) {
                number1String = display2.getText().toString();
                operation = (String) button.getText();
                writeOnDisplay1(number1String + operation);
                writeOnDisplay2("");
            }
        }

    }

    public void pointPressed(View v) {
        if (!pointPresent) {
            if (operation.isEmpty()) {
                if (number1String.isEmpty()) {
                    number1String = "0.";
                } else {
                    number1String += ".";
                }
                writeOnDisplay1(number1String);
            } else {
                if (number2String.isEmpty()) {
                    number2String = "0.";
                } else {
                    number2String += ".";
                }
                writeOnDisplay1(number1String + operation + number2String);
            }
            pointPresent = true;
        }
    }

    public void resolve(View v) {
        boolean buttonEqualsPressed = false;

        if (((Button) v).getText().equals("=")) {
            buttonEqualsPressed = true;
        }

        if (!operation.isEmpty() && !number2String.isEmpty()) {
            double number1 = Double.parseDouble(number1String);
            double number2 = Double.parseDouble(number2String);
            result = 0;

            switch (operation) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "X":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }

            writeOnDisplay2(String.valueOf(result));

            if (buttonEqualsPressed) {
                number1String = "";
                number2String = "";
                operation = "";

            } else {
                number1String = String.valueOf(result);
                number2String = "";
                writeOnDisplay1(display2.getText() + operation);
            }
        }
    }

    public void removeOneDigit(View v) {
        if (operation.isEmpty() && !number1String.isEmpty()) {
            number1String = number1String.substring(0, number1String.length() - 1);
            writeOnDisplay1(number1String);
        } else {
            if (!number2String.isEmpty()) {
                number2String = number2String.substring(0, number2String.length() - 1);
                writeOnDisplay1(number1String + operation + number2String);
            }
        }
    }

    public void removeCurrentNumber(View v) {
        if (operation.isEmpty() && !number1String.isEmpty()) {
            number1String = "";
            writeOnDisplay1(number1String);
        } else {
            if (!number2String.isEmpty()) {
                number2String = "";
                writeOnDisplay1(number1String + operation + number2String);
            }
        }
    }

    public void resetCalculator(View v) {
        number1String = "";
        number2String = "";
        operation = "";
        pointPresent = false;
        writeOnDisplay1("");
        writeOnDisplay2("");
    }

    private void writeOnDisplay1(String msg) {
        display1.setText(msg);
    }

    private void writeOnDisplay2(String msg) {
        display2.setText(msg);
    }
}