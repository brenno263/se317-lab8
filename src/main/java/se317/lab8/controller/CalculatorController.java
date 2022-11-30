package se317.lab8.controller;

import se317.lab8.model.CalculatorNumber;
import se317.lab8.model.Model;
import se317.lab8.view.CalculatorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private final CalculatorGUI view;
    private final Model model;
    private final CalculatorNumber cNumber;


    public CalculatorController(CalculatorGUI view, Model model) {


        cNumber = new CalculatorNumber();

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
