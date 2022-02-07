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
        if (!number.toString().equals("")) {
            addNumberToInput(number);
        }
        addOperatorToInput(v);
        updateView(v);
    }

    public void onEqualsClick(View v) {
        addNumberToInput(number);
        calculate(input);
        clearInput();
    }

    public void addOperatorToInput(View v) {
        input.add(getBtnTxt(v));
    }

    public void addNumberToInput(StringBuilder s) {
        if (!s.toString().contains(".")) {
            s.append(".0");
        }
        input.add(Double.parseDouble(s.toString()));
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

    public void calculate(ArrayList a) {
        input = calculateFor(a, "x", "÷");
        input = calculateFor(a, "+", "-");
        calculationDone = true;
        TextView t = findViewById(R.id.mainOutput);
        t.setText(a.get(0).toString());
    }

    public ArrayList calculateFor(ArrayList a, String operator1, String operator2) {
        double answer;
        while (a.contains(operator1) || a.contains(operator2)) {
            int indexOperator1 = a.indexOf(operator1);
            int indexOperator2 = a.indexOf(operator2);
            int indexOperatorMain;

            if (indexOperator1 < indexOperator2 && indexOperator1 != -1) {
                indexOperatorMain = indexOperator1;
            } else {
                indexOperatorMain = indexOperator2;
            }
            if (indexOperator2 == -1) {
                indexOperatorMain = indexOperator1;
            }
            double firstNumber = (double) a.get(indexOperatorMain - 1);
            double secondNumber = (double) a.get(indexOperatorMain + 1);

            String operatorMain = a.get(indexOperatorMain).toString();

            answer = calculateSingle(firstNumber, secondNumber, operatorMain);

            a.remove(indexOperatorMain - 1);
            a.remove(indexOperatorMain - 1);
            a.remove(indexOperatorMain - 1);
            a.add(indexOperatorMain - 1, answer);
        }
        return a;
    }

    public double calculateSingle(double fNumber, double sNumber, String o) {
        switch (o) {
            case "x":
                return fNumber * sNumber;
            case "÷":
                return fNumber / sNumber;
            case "+":
                return fNumber + sNumber;
            default:
                return fNumber - sNumber;
        }
    }
}