package se317.lab8.model;

import se317.lab8.Operation;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static se317.lab8.Operation.*;

public class Model extends Observable {


    private final CalculatorNumber val;
    private double storedVal;
    private Operation operation;
    private double memory;

    /**
     * True if an operation was just selected. Signifies that the next number input will go into a fresh value.
     */
    private boolean operationJustSelected;

    /**
     * True if the selected operation is not none and has been completed at least once.
     * If a number is entered while this is true, the value and operation are reset.
     */
    private boolean operationDone;

    private final ArrayList<Observer> observers;


    public Model() {
        val = new CalculatorNumber();
        storedVal = 0;
        operation = none;
        memory = 0;
        observers = new ArrayList<>();
    }

    public void completeOp() {
        //If this is true, we haven't selected a second value yet.

        if(operationJustSelected) return;
        if (operation != none) {

            if (!operationDone) {
                //Swap these values before evaluating the first time so that repeat calculations use the right modifier.
                double temp = storedVal;
                storedVal = val.value();
                val.setValue(temp);
            }

            try {
                switch (operation) {
                    case add:
                        val.setValue(val.value() + storedVal);
                        break;
                    case subtract:
                        val.setValue(val.value() - storedVal);
                        break;
                    case mutiply:
                        val.setValue(val.value() * storedVal);
                        break;
                    case divide:
                        val.setValue(val.value() / storedVal);
                        break;
                }
            } catch (Exception e) {
                setError();
            }

            operationDone = true;
            notifyObservers();
        }
    }

    public void root() {
        try {
            val.setValue(Math.sqrt(val.value()));
        } catch (Exception e) {
            setError();
        }
        setOperation(none);
        notifyObservers();
    }

    public void square() {
        try {
            val.setValue(Math.pow(val.value(), 2));
        } catch (Exception e) {
            setError();
        }
        setOperation(none);
        notifyObservers();
    }

    public void memoryRecall() {
        val.setValue(memory);
        notifyObservers();
    }

    public void memoryClear() {
        memory = 0;
    }

    public void memoryAdd() {
        memory += val.value();
        val.clear();
        setOperation(none);
        notifyObservers();
    }

    public void memorySub() {
        memory -= val.value();
        val.clear();
        setOperation(none);
        notifyObservers();
    }

    public double getValue() {
        return val.value();
    }

    public double getMemory() {
        return memory;
    }

    public void setValue(double value) {
        val.setValue(value);
    }

    public void setError() {
        val.setError();
    }

    public void setStoredVal(double storedVal) {
        this.storedVal = storedVal;
    }

    public void appendNumber(int number) {
        tryReplaceVal();
        tryResetOperation();
        this.val.appendNumber(number);
        notifyObservers();
    }

    public void appendDot() {
        tryReplaceVal();
        tryResetOperation();
        this.val.appendDot();
        notifyObservers();
    }

    private void tryReplaceVal() {
        if (operationJustSelected) {
            operationJustSelected = false;

            storedVal = val.value();
            val.clear();
        }
    }

    private void tryResetOperation() {
        if (operationDone) {
            operationDone = false;
            operation = none;

            val.clear();
        }
    }

    public void deleteNumberFromEnd() {
        this.val.deleteFromEnd();
        notifyObservers();
    }

    public void clear() {
        this.val.clear();
        this.storedVal = 0;
        setOperation(none);
        notifyObservers();
    }

    public void setOperation(Operation operation) {
        if (operation == none) {
            this.operationJustSelected = false;
            this.operationDone = false;
        } else {
            this.operationJustSelected = true;
            this.operationDone = false;
        }
        this.operation = operation;
        notifyObservers();
    }

    public Operation getOperation() {
        return operation;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public String getDisplay() {
        return val.toString();
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
