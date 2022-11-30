package se317.lab8.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Because of the bizarre behavior required, where deleting values can leave a hanging dot etc. This class wraps a lot
 * of that functionality so that the number can be displayed properly, and turned into a simple double with ease.
 */
public class CalculatorNumber extends Observable {

    private final static int MAX_LENGTH = 14;

    private String displayValue;

    private ArrayList<Observer> observers;

    public CalculatorNumber() {
        displayValue = "";
        observers = new ArrayList<>();
    }

    public void appendNumber(int num) {
        if(displayValue.length() > MAX_LENGTH) return;
        displayValue += num;
        notifyObservers();
    }

    public void appendDot() {
        if(displayValue.length() > MAX_LENGTH) return;
        if(!displayValue.contains(".")) {
            displayValue += '.';
        }
        notifyObservers();
    }

    public void deleteFromEnd() {
        displayValue = displayValue.substring(0, displayValue.length() - 2);
        notifyObservers();
    }

    public void clear() {
        displayValue = "";
        notifyObservers();
    }

    public double value() {
        return Double.parseDouble(displayValue);
    }

    @Override
    public String toString() {
        return String.format(displayValue);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    @Override
    public synchronized void deleteObservers() {
        observers.clear();
    }
}
