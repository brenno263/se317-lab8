package se317.lab8;

/**
 * Because of the bizarre behavior required, where deleting values can leave a hanging dot etc. This class wraps a lot
 * of that functionality so that the number can be displayed properly, and turned into a simple double with ease.
 */
public class CalculatorNumber {

    private final static int MAX_LENGTH = 12;

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
        displayValue = displayValue.substring(0, displayValue.length() - 2);
    }

    public void clear() {
        displayValue = "";
    }

    public double value() {
        return Double.parseDouble(displayValue);
    }

    @Override
    public String toString() {
        return String.format(displayValue);
    }
}
