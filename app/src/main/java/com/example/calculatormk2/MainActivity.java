package com.example.calculatormk2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList input = new ArrayList();
    private StringBuilder number = new StringBuilder();
    private boolean calculationDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNumberClick(View v) {
        number.append(getBtnTxt(v));
        updateView(v);
    }

    public void onOperatorClick(View v) {
        addNumberToInput(number);
        addOperatorToInput(v);
        updateView(v);
    }

    public void onEqualsClick(View v) {
        addNumberToInput(number);
        calculate(input);
        clearInput();
    }

    public void calculate(ArrayList i) {
        while (i.contains("x") || i.contains("÷")) {
            int indexOperatorTimes = i.indexOf("x");
            int indexOperatorDivideBy = i.indexOf("÷");
            int indexOperatorMain;

            if (indexOperatorTimes < indexOperatorDivideBy && indexOperatorTimes != -1) {
                indexOperatorMain = indexOperatorTimes;
            } else {
                indexOperatorMain = indexOperatorDivideBy;
            }
            if (indexOperatorDivideBy == -1) {
                indexOperatorMain = indexOperatorTimes;
            }

            double firstNumber;
            double secondNumber;
            String operator = i.get(indexOperatorMain).toString();
            double answer;

            if (i.get(indexOperatorMain - 1) instanceof Integer) {
                firstNumber = (int) i.get(indexOperatorMain - 1);
            } else {
                firstNumber = (double) i.get(indexOperatorMain - 1);
            }
            if (i.get(indexOperatorMain + 1) instanceof Integer) {
                secondNumber = (int) i.get(indexOperatorMain + 1);
            } else {
                secondNumber = (double) i.get(indexOperatorMain + 1);
            }

            if (operator.equals("x")) {
                answer = firstNumber * secondNumber;
            } else {
                answer = firstNumber / secondNumber;
            }

            i.remove(indexOperatorMain - 1);
            i.remove(indexOperatorMain - 1);
            i.remove(indexOperatorMain - 1);
            i.add(indexOperatorMain - 1, answer);
            System.out.println(i);
        }

        while (i.contains("+") || i.contains("-")) {
            int indexOperator1 = i.indexOf("+");
            int indexOperator2 = i.indexOf("-");
            int indexOperatorDef;

            if (indexOperator1 < indexOperator2 && indexOperator1 != -1) {
                indexOperatorDef = indexOperator1;
            } else {
                indexOperatorDef = indexOperator2;
            }
            if (indexOperator2 == -1) {
                indexOperatorDef = indexOperator1;
            }

            double firstNumber;
            double secondNumber;
            String operator = i.get(indexOperatorDef).toString();
            double answer;

            if (i.get(indexOperatorDef - 1) instanceof Integer) {
                firstNumber = (int) i.get(indexOperatorDef - 1);
            } else {
                firstNumber = (double) i.get(indexOperatorDef - 1);
            }
            if (i.get(indexOperatorDef + 1) instanceof Integer) {
                secondNumber = (int) i.get(indexOperatorDef + 1);
            } else {
                secondNumber = (double) i.get(indexOperatorDef + 1);
            }

            if (operator.equals("+")) {
                answer = firstNumber + secondNumber;
            } else {
                answer = firstNumber - secondNumber;
            }
            i.remove(indexOperatorDef - 1);
            i.remove(indexOperatorDef - 1);
            i.remove(indexOperatorDef - 1);
            i.add(indexOperatorDef - 1, answer);
        }
        calculationDone = true;
        TextView t = findViewById(R.id.mainOutput);
        String s = i.get(0).toString();
        t.setText(s);
    }

    public void addOperatorToInput(View v) {
        input.add(getBtnTxt(v));
    }

    public void addNumberToInput(StringBuilder s) {
        if (s.toString().contains(".")) {
            input.add(Double.parseDouble(s.toString()));
        } else {
            input.add(Integer.parseInt(s.toString()));
        }
        clearNumber();
    }

    public String getBtnTxt(View v) {
        return ((Button) v).getText().toString();
    }

    public void clearNumber() {
        number.delete(0, number.length());
    }

    public void clearInput() {
        input.clear();
    }

    @SuppressLint("SetTextI18n")
    public void updateView(View v) {
        TextView mainOutput = findViewById(R.id.mainOutput);
        if (calculationDone) {
            mainOutput.setText("");
            calculationDone = false;
        }
        mainOutput.setText(mainOutput.getText() + getBtnTxt(v));
    }
}