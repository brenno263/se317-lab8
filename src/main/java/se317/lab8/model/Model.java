package se317.lab8.model;

import se317.lab8.Operation;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static se317.lab8.Operation.*;

public class Model extends Observable {


    private final CalculatorNumber val;
    private double prevVal;
    private Operation operation;
    private double memory;

    private final ArrayList<Observer> observers;

    public Model() {
        val = new CalculatorNumber();
        prevVal = 0;
        operation = none;
        memory = 0;
        observers = new ArrayList<>();
    }

    public void completeOp () {
        switch (operation) {
            case add:
                 val.setValue(prevVal + val.value());
                break;
            case subtract:
                val.setValue(prevVal - val.value());
                break;
            case mutiply:
                val.setValue(prevVal * val.value());
                break;
            case divide:
                val.setValue(prevVal / val.value());
                break;
            default:
                return;
        }
        notifyObservers();
    }

    public void square() {
        val.setValue(Math.sqrt(val.value()));
        notifyObservers();
    }

    public void root() {
        val.setValue(Math.pow(val.value(), 2));
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
        notifyObservers();
    }

    public void memorySub() {
        memory -= val.value();
        val.clear();
        notifyObservers();
    }

    public double getValue(){
        return val.value();
    }

    public void setValue(double value) {
        val.setValue(value);
    }

    public void appendNumber(int number) {
        this.val.appendNumber(number);
        notifyObservers();
    }

    public void appendDot() {
        this.val.appendDot();
        notifyObservers();
    }

    public void deleteNumberFromEnd() {
        this.val.deleteFromEnd();
        notifyObservers();
    }

    public void clear() {
        this.val.clear();
        this.prevVal = 0;
        this.operation = none;
        notifyObservers();
    }

    public void setOperation(Operation operation) {
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
