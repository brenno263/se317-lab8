package se317.lab8;

import java.util.ArrayList;
import java.util.Observer;

import static se317.lab8.Operation.*;

public class Model {

    private ArrayList<Observer> observers;

    private double val;
    private double prevVal;
    private Operation operation;



    public Model() {
        observers = new ArrayList<>();
        val = 0;
        prevVal = 0;
        operation = none;
    }

    public Double completeOp () {
        double answer = 0;
        switch (operation) {
            case add:
                answer = prevVal + val;
                break;
            case subtract:
                answer = prevVal - val;
                break;
            case mutiply:
                answer = prevVal * val;
                break;
            case divide:
                answer = prevVal / val;
                break;
        }
        prevVal = answer;
        return answer;
    }

    public double multiplier() {

    }

}
