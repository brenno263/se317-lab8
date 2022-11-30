package se317.lab8.controller;

import se317.lab8.Operation;
import se317.lab8.model.CalculatorNumber;
import se317.lab8.model.Model;
import se317.lab8.view.CalculatorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private final CalculatorGUI view;
    private final Model model;


    public CalculatorController(CalculatorGUI view, Model model) {

        this.view = view;
        view.addNumberListener(new NumberListener());
        view.addOperationListener(new OperatorListener());
        view.addCommandListener(new CommandListener());

        this.model = model;
        model.addObserver(view);
    }

    public void update() {

    }

    class NumberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals(".")) {
                model.appendDot();
            } else {
                model.appendNumber(Integer.parseInt(command));
            }
        }
    }

    class OperatorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "+":
                    model.setOperation(Operation.add);
                    break;
                case "-":
                    model.setOperation(Operation.subtract);
                    break;
                case "*":
                    model.setOperation(Operation.mutiply);
                    break;
                case "/":
                    model.setOperation(Operation.divide);
                    break;
                default:
                    model.setOperation(Operation.none);
            }
        }
    }

    class CommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "C":
                    model.clear();
                    break;
                case "D":
                    model.deleteNumberFromEnd();
                    break;
                case "sq":
                    model.square();
                    break;
                case "rt":
                    model.root();
                    break;
                case "MR":
                    model.memoryRecall();
                    break;
                case "MC":
                    model.memoryClear();
                    break;
                case "M+":
                    model.memoryAdd();
                    break;
                case "M-":
                    model.memorySub();
                    break;
                default:
                    break;
            }
        }
    }
}
