package com.example.calculatormk2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Calculator calculatorMain = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNumberClick(View view) {
        calculatorMain.addDigitToInput(getTextFrom(view));
        updateTextView();
    }

    public void onOperatorClick(View view) {
        calculatorMain.addOperatorToInput(getTextFrom(view));
        updateTextView();
    }

    public void onEqualsClick(View v) {
        TextView mainOutput = findViewById(R.id.mainOutput);
        mainOutput.setText(calculatorMain.calculate());
    }

    public void onDeleteClick(View v) {
        calculatorMain.delete();
        updateTextView();
    }

    public String getTextFrom(View v) {
        return ((Button) v).getText().toString();
    }

    public void updateTextView() {
        TextView mainOutput = findViewById(R.id.mainOutput);
        mainOutput.setText(calculatorMain.getInput());
    }
}