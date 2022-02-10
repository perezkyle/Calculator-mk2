package com.example.calculatormk2;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    private StringBuilder inputAsText = new StringBuilder();
    private ArrayList inputAsArrayList;

    public void addDigitToInput(String digit) {
        inputAsText.append(digit);
    }

    public void addOperatorToInput(String operator) {
        inputAsText.append(" ").append(operator).append(" ");
    }

    public void delete() {
        if (inputAsText.length() != 0) {
            if (Character.isWhitespace(inputAsText.charAt(inputAsText.length() - 1))) {
                inputAsText.deleteCharAt(inputAsText.length() - 1);
                inputAsText.deleteCharAt(inputAsText.length() - 1);
                inputAsText.deleteCharAt(inputAsText.length() - 1);
            } else {
                inputAsText.deleteCharAt(inputAsText.length() - 1);
            }
        }
    }

    public void clearInput() {
        inputAsText.delete(0, inputAsText.length());
        inputAsArrayList.clear();
    }

    public String getInput() {
        return this.inputAsText.toString();
    }

    public String calculate() {
        String[] tmpArray = inputAsText.toString().split(" ");
        inputAsArrayList = new ArrayList(Arrays.asList(tmpArray));
        inputAsArrayList = calculateFor("x", "÷");
        inputAsArrayList = calculateFor("+", "-");
        String answer = String.valueOf(inputAsArrayList.get(0));
        clearInput();
        return answer;
    }

    public ArrayList calculateFor(String operator1, String operator2) {
        while (inputAsArrayList.contains(operator1) || inputAsArrayList.contains(operator2)) {

            int indexOperator1 = inputAsArrayList.indexOf(operator1);
            int indexOperator2 = inputAsArrayList.indexOf(operator2);
            int indexOperatorMain = Math.min(indexOperator1, indexOperator2);

            if (indexOperator1 == -1) {
                indexOperatorMain = indexOperator2;
            } else if (indexOperator2 == -1) {
                indexOperatorMain = indexOperator1;
            }

            String operatorMain = inputAsArrayList.get(indexOperatorMain).toString();

            double firstNumber = Double.parseDouble(inputAsArrayList.get(indexOperatorMain - 1).toString());
            double secondNumber = Double.parseDouble(inputAsArrayList.get(indexOperatorMain + 1).toString());

            double answer = calculateSegment(firstNumber, secondNumber, operatorMain);

            inputAsArrayList.remove(indexOperatorMain - 1);
            inputAsArrayList.remove(indexOperatorMain - 1);
            inputAsArrayList.remove(indexOperatorMain - 1);
            inputAsArrayList.add(indexOperatorMain - 1, answer);
        }
        return inputAsArrayList;
    }

    public double calculateSegment(double fNumber, double sNumber, String o) {
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
