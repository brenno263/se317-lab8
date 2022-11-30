package se317.lab8.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Because of the bizarre behavior required, where deleting values can leave a hanging dot etc. This class wraps a lot
 * of that functionality so that the number can be displayed properly, and turned into a simple double with ease.
 */
public class CalculatorNumber {

    private final static int MAX_LENGTH = 14;

    private String displayValue;

    public CalculatorNumber() {
        displayValue = "";
    }

    public void appendNumber(int num) {
        if(displayValue.length() > MAX_LENGTH) return;
        displayValue += num;
    }

    public void appendDot() {
        if(displayValue.length() > MAX_LENGTH) return;
        if(!displayValue.contains(".")) {
            displayValue += '.';
        }
    }

    public void deleteFromEnd() {
        displayValue = displayValue.substring(0, displayValue.length() - 1);
    }

    public void clear() {
        displayValue = "";
    }

    public double value() {
        if(displayValue.length() == 0) {
            return 0;
        } else if (displayValue.charAt(displayValue.length() - 1) == '.') {
            return Double.parseDouble(displayValue.substring(0, displayValue.length() - 1));
        } else {
            return Double.parseDouble(displayValue);
        }
    }

    public void setValue(double value) {
        displayValue = String.valueOf(value);
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
