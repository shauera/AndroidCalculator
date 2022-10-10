package com.shauera.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;
    private final String DIV0ERR = "Div/0!";

    // Variables to hold the operation and the type of calculation
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUIComponents();
    }

    private void bindUIComponents() {
        result =  findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        Button buttonEquals =  findViewById(R.id.buttonEquals);
        Button buttonPlus =  findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);

        View.OnClickListener numberButtonListener = view -> {
            Button button = (Button) view;
            newNumber.append(button.getText().toString());
        };

        button0.setOnClickListener(numberButtonListener);
        button1.setOnClickListener(numberButtonListener);
        button2.setOnClickListener(numberButtonListener);
        button3.setOnClickListener(numberButtonListener);
        button4.setOnClickListener(numberButtonListener);
        button5.setOnClickListener(numberButtonListener);
        button6.setOnClickListener(numberButtonListener);
        button7.setOnClickListener(numberButtonListener);
        button8.setOnClickListener(numberButtonListener);
        button9.setOnClickListener(numberButtonListener);
        buttonDot.setOnClickListener(numberButtonListener);

        View.OnClickListener operationButtonListener = view -> {
            Button button = (Button) view;
            String operation = button.getText().toString();
            String value = newNumber.getText().toString();
            if (value.length() != 0) {
                if (value.equals(".")) {
                    value = "0";
                }
                operand2 = Double.parseDouble(value);
                performOperation(operand2, pendingOperation);
            }
            pendingOperation = operation;
            displayOperation.setText(pendingOperation);
        };

        buttonDivide.setOnClickListener(operationButtonListener);
        buttonMultiply.setOnClickListener(operationButtonListener);
        buttonPlus.setOnClickListener(operationButtonListener);
        buttonMinus.setOnClickListener(operationButtonListener);
        buttonEquals.setOnClickListener(operationButtonListener);
    }

    private void performOperation(Double value, String operation) {
        if (operand1 == null) {
            operand1 = value;
        } else {
            switch (pendingOperation) {
                case "+" :
                    operand1 += operand2;
                    break;
                case "-" :
                    operand1 -= operand2;
                    break;
                case "/" :
                    if (operand2 == 0) {
                        result.setText(DIV0ERR);
                        operand1 = null;
                        operand2 = null;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "*" :
                    operand1 *= operand2;
                    break;
                case "=" :
                    operand1 = operand2;
                    break;
            }
        }
        if (!(result.getText().toString().equals("Div/0!") && pendingOperation.equals("/"))) {
            result.setText(operand1.toString());
        }
        newNumber.setText("");
    }
}