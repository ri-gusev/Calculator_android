package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private StringBuilder input;
    private TextView textViewInputTask;
    private TextView textViewAnswerOutput;
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
        Button buttonCheckAnswer = findViewById(R.id.buttonCheckAnswer);
        textViewAnswerOutput = findViewById(R.id.textViewAnswerOutPut);

        input = new StringBuilder(); // this is input line

        textViewInputTask.setText(input);

        // now create setOnClickListener on every button
        button0.setOnClickListener(view -> {
            String currentText = input.toString();
            if (!currentText.isEmpty() && !isNumber(currentText.charAt(currentText.length()-1))) {
                input.append("0");
                textViewInputTask.setText(input);
            }
        });

        button1.setOnClickListener(view -> checkAndAddNumber('1'));

        button2.setOnClickListener(view -> checkAndAddNumber('2'));

        button3.setOnClickListener(view -> checkAndAddNumber('3'));

        button4.setOnClickListener(view -> checkAndAddNumber('4'));

        button5.setOnClickListener(view -> checkAndAddNumber('5'));

        button6.setOnClickListener(view -> checkAndAddNumber('6'));

        button7.setOnClickListener(view -> checkAndAddNumber('7'));

        button8.setOnClickListener(view -> checkAndAddNumber('8'));

        button9.setOnClickListener(view -> checkAndAddNumber('9'));

        buttonPlus.setOnClickListener(view -> addOperator('+'));

        buttonMinus.setOnClickListener(view -> {
            if (input.length() == 0 ){
                input.append('-');
                textViewInputTask.setText(input.toString());
            } else {
                addOperator('-');
            }
        });

        buttonMultiple.setOnClickListener(view -> addOperator('×'));

        buttonDivide.setOnClickListener(view -> addOperator('÷'));

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

        buttonCheckAnswer.setOnClickListener(view -> {
            if (input.length() != 0) {
                String task = input.toString();

                List<String> numsOfTask = new ArrayList<>(Arrays.asList(task.split("")));

                while (numsOfTask.size() != 1) {
                    // if contains operators
                    if (numsOfTask.contains("×") || numsOfTask.contains("÷") || numsOfTask.contains("+") || numsOfTask.contains("-")) {
                        //If first element is minus
                        if (numsOfTask.get(0).equals("-")) {
                            subtractNextElement(numsOfTask);
                        }
                        // while contains division
                        while (numsOfTask.contains("÷")) {
                            int index = numsOfTask.indexOf("÷");
                            double result = Double.valueOf(numsOfTask.get(index - 1)) / Double.valueOf(numsOfTask.get(index + 1));
                            numsOfTask.add(index - 1, String.valueOf(result));
                            numsOfTask.remove(index + 1);
                            removeNext2Elements(numsOfTask, index);
                        }
                        // division is over, move on to multiplication
                        // while contains multiplication
                        while (numsOfTask.contains("×")) {
                            int index = numsOfTask.indexOf("×");
                            double result = Double.valueOf(numsOfTask.get(index - 1)) * Double.valueOf(numsOfTask.get(index + 1));
                            numsOfTask.add(index - 1, String.valueOf(result));
                            numsOfTask.remove(index + 1);
                            removeNext2Elements(numsOfTask, index);
                        }

                        // while contains minuses
                        while (numsOfTask.contains("-")) {
                            int index = numsOfTask.indexOf("-");
                            double result = Double.valueOf(numsOfTask.get(index - 1)) - Double.valueOf(numsOfTask.get(index + 1));
                            numsOfTask.add(index - 1, String.valueOf(result));
                            numsOfTask.remove(index + 1);
                            removeNext2Elements(numsOfTask, index);
                        }

                        // while contains pluses
                        while (numsOfTask.contains("+")) {
                            int index = numsOfTask.indexOf("+");
                            double result = Double.valueOf(numsOfTask.get(index - 1)) + Double.valueOf(numsOfTask.get(index + 1));
                            numsOfTask.add(index - 1, String.valueOf(result));
                            numsOfTask.remove(index + 1);
                            removeNext2Elements(numsOfTask, index);
                        }

                    }
                }
                textViewAnswerOutput.setText(numsOfTask.get(0));
            } else {
                textViewAnswerOutput.setText("Вы ничего не ввели");
            }
        });

    }

    private static void removeNext2Elements(List<String> array, int index) {
        array.remove(index);
        array.remove(index);
    }

    private static void subtractNextElement(List<String> array) {
        int index = array.indexOf("-");
        double result = -Double.valueOf(array.get(index + 1));
        array.add(0, String.valueOf(result));
        array.remove(2);
        array.remove(1);
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷';
    }

    private void addOperator(char c) {
        String currentText = input.toString();
        // if stringBuilder is empty or last element is operator
        if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
            // if true then do not append operator
            return;
        }
        input.append(c);
        textViewInputTask.setText(input.toString());
    }

    private boolean isNumber(char c){
        return  c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8' || c == '9' || c == '0';
    }

    private void checkAndAddNumber(char element){
        if (input.length() != 0 ){
            String currentText = input.toString();
            if (isNumber(currentText.charAt(currentText.length()-1))){
                return;
            }
            input.append(element);
        } else {
            input.append(element);
        }
        textViewInputTask.setText(input.toString());
    }

    private void ifPreviousElementIsNotNumberThenAddNumber(char element){
        String currentText = input.toString();
        if (isNumber(currentText.charAt(currentText.length()-1))){
            return;
        }
        input.append(element);
    }
}