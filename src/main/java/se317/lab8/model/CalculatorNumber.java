package se317.lab8.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Because of the bizarre behavior required, where deleting values can leave a hanging dot etc. This class wraps a lot
 * of that functionality so that the number can be displayed properly, and turned into a simple double with ease.
 */
public class CalculatorNumber {

    //Display should max at this value
    private final static int MAX_LENGTH = 20;
    private final static String formatString = "%" + MAX_LENGTH + "f";

    private String displayValue;
    private boolean hasError;

    public CalculatorNumber() {
        displayValue = "";
    }

    public void appendNumber(int num) {
        if (displayValue.length() >= MAX_LENGTH) return;
        displayValue += num;
        hasError = false;
    }

    public void appendDot() {
        if (displayValue.length() >= MAX_LENGTH) return;
        if (!displayValue.contains(".")) {
            displayValue += '.';
            hasError = false;
        }
    }

    public void deleteFromEnd() {
        if (displayValue.length() > 0) {
            displayValue = displayValue.substring(0, displayValue.length() - 1);
        }
    }

    public void clear() {
        displayValue = "";
        hasError = false;
    }

    public double value() {
        try {
            if (displayValue.length() == 0) {
                return 0;
            } else if (displayValue.charAt(displayValue.length() - 1) == '.') {
                return Double.parseDouble(displayValue.substring(0, displayValue.length() - 1));
            } else {
                return Double.parseDouble(displayValue);
            }
        } catch (NumberFormatException e) {
            clear();
            return 0;
        }
    }

    public void setError() {
        clear();
        hasError = true;
    }

    public void setValue(double value) {
        String str = String.valueOf(value);
        if (str.length() > MAX_LENGTH || Double.isInfinite(value) || Double.isNaN(value)) {
            setError();
        }
        displayValue = str;
    }

    @Override
    public String toString() {
        return hasError ? "Error" : displayValue;
    }
}
