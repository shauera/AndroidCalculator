package com.shauera.calculator;

import androidx.annotation.NonNull;
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

    private enum STATE_KEYS {
        OPERAND1,
        PENDING_OPERATION
    }

    // Variables to hold the operation and the type of calculation
    private Double operand1 = null;
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
                Double operand2 = Double.parseDouble(value);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (operand1 != null) {
            outState.putDouble(STATE_KEYS.OPERAND1.toString(), operand1);
        }
        outState.putString(STATE_KEYS.PENDING_OPERATION.toString(), pendingOperation);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand1 = savedInstanceState.getDouble(STATE_KEYS.OPERAND1.toString());
        pendingOperation = savedInstanceState.getString(STATE_KEYS.PENDING_OPERATION.toString());
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double operand2, String operation) {
        if (operand1 == null) {
            operand1 = operand2;
        } else {
            switch (operation) {
                case "+" :
                    operand1 += operand2;
                    break;
                case "-" :
                    operand1 -= operand2;
                    break;
                case "/" :
                    if (operand2 == 0) {
                        String DIV0ERR = "Div/0!";
                        result.setText(DIV0ERR);
                        operand1 = null;
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
        if (!(result.getText().toString().equals("Div/0!") && pendingOperation.equals("/")))
            result.setText(operand1.toString());
        newNumber.setText("");
    }
}