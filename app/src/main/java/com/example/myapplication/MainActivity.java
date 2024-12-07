package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private StringBuilder input;
    private TextView textViewInputTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create some variables
        setContentView(R.layout.activity_main);
        Button button0 = findViewById(R.id.buttonZero);
        Button button1 = findViewById(R.id.buttonOne);
        Button button2 = findViewById(R.id.buttonTwo);
        Button button3 = findViewById(R.id.buttonThree);
        Button button4 = findViewById(R.id.buttonFour);
        Button button5 = findViewById(R.id.buttonFive);
        Button button6 = findViewById(R.id.buttonSix);
        Button button7 = findViewById(R.id.buttonSeven);
        Button button8 = findViewById(R.id.buttonEight);
        Button button9 = findViewById(R.id.buttonNine);
        textViewInputTask = findViewById(R.id.textViewInputTask);
        Button buttonDivide = findViewById(R.id.buttonDivider);
        Button buttonMultiple = findViewById(R.id.buttonMultiple);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonDeleteAll = findViewById(R.id.buttonDeleteAll);

        input = new StringBuilder(); // this is input line

        textViewInputTask.setText(input);

        // now create setOnClickListener on every button
        button0.setOnClickListener(view -> {
            input.append("0");
            textViewInputTask.setText(input);
        });

        button1.setOnClickListener(view -> {
            input.append("1");
            textViewInputTask.setText(input);
        });

        button2.setOnClickListener(view -> {
            input.append("2");
            textViewInputTask.setText(input);
        });

        button3.setOnClickListener(view -> {
            input.append("3");
            textViewInputTask.setText(input);
        });

        button4.setOnClickListener(view -> {
            input.append("4");
            textViewInputTask.setText(input);
        });

        button5.setOnClickListener(view -> {
            input.append("5");
            textViewInputTask.setText(input);
        });

        button6.setOnClickListener(view -> {
            input.append("6");
            textViewInputTask.setText(input);
        });

        button7.setOnClickListener(view -> {
            input.append("7");
            textViewInputTask.setText(input);
        });

        button8.setOnClickListener(view -> {
            input.append("8");
            textViewInputTask.setText(input);
        });

        button9.setOnClickListener(view -> {
            input.append("9");
            textViewInputTask.setText(input);
        });

        buttonPlus.setOnClickListener(view -> {
            addCharacter('+');
        });

        buttonMinus.setOnClickListener(view -> {
            addCharacter('-');
        });

        buttonMultiple.setOnClickListener(view -> {
            addCharacter('×');
        });

        buttonDivide.setOnClickListener(view -> {
            addCharacter('÷');
        });

        buttonDelete.setOnClickListener(view -> {
            int indexOfLastElement = input.length() - 1;
            if (input.length() > 0) {
                input.deleteCharAt(indexOfLastElement);
                textViewInputTask.setText(input);
            }
        });

        buttonDeleteAll.setOnClickListener(view -> {
            input.setLength(0);
            textViewInputTask.setText(input);
        });
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷';
    }

    private void addCharacter(char c) {
        String currentText = input.toString();

        // if stringBuilder is empty or last element is operator
        if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
            // if true then do not append operator
            return;
        }

        input.append(c);
        textViewInputTask.setText(input.toString());
    }
}