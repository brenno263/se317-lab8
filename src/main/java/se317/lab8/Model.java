package se317.lab8;

import java.util.ArrayList;
import java.util.Observer;

import static se317.lab8.Operation.*;

public class Model {

    private ArrayList<Observer> observers;

    private double val;
    private double prevVal;
    private Operation operation;
    private double memory;



    public Model() {
        observers = new ArrayList<>();
        val = 0;
        prevVal = 0;
        operation = none;
        memory = 0;
    }

    public void completeOp () {
        double answer = 0;
        switch (operation) {
            case add:
                prevVal += val;
                break;
            case subtract:
                prevVal -= val;
                break;
            case mutiply:
                prevVal *= val;
                break;
            case divide:
                prevVal /= val;
                break;
            case memoryStore:
                memory = prevVal;
                break;
            case memoryAdd:
                memory += prevVal;
                break;
            case memorySubtract:
                memory -= prevVal;
                break;
            case memoryClear:
                memory = 0;
                break;
        }
    }
    public double getVal(){
        return val;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public double getMemory() {
        return memory;
    }

    public double getPrevVal() {
        return prevVal;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public void setPrevVal(double prevVal) {
        this.prevVal = prevVal;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }
}
