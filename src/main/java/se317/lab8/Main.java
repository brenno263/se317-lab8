package se317.lab8;

import se317.lab8.controller.CalculatorController;
import se317.lab8.model.Model;
import se317.lab8.view.CalculatorGUI;

public class Main {
    public static void main(String[] args) {
        CalculatorGUI view = new CalculatorGUI();
        Model model = new Model();
        CalculatorController c = new CalculatorController(view, model);
    }
}