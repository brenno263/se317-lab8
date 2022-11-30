package se317.lab8.controller;

import se317.lab8.CalculatorNumber;
import se317.lab8.view.CalculatorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private final CalculatorGUI view;
    //private final Model model;
    // This will be replaced once model exists. It's good to get some functionality down now.
    private final CalculatorNumber cNumber;

    private final NumberListener numberListener;
    private final OperatorListener operatorListener;
    private final CommandListener commandListener;


    public CalculatorController(CalculatorGUI view) {
        numberListener = new NumberListener();
        operatorListener = new OperatorListener();
        commandListener = new CommandListener();

        cNumber = new CalculatorNumber();

        this.view = view;
        view.addNumberListener(numberListener);
    }

    public void update() {

    }

    class NumberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals(".")) {
                cNumber.appendDot();
            } else {
                cNumber.appendNumber(Integer.parseInt(command));
            }
        }
    }

    class OperatorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            //switch
        }
    }

    class CommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            //switch
        }
    }
}
