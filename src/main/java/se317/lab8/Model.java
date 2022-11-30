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

}
