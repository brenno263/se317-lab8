package se317.lab8.view;

import se317.lab8.Operation;
import se317.lab8.model.Model;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class CalculatorGUI extends JFrame implements Observer {
    protected JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDot;
    protected JButton bClr, bDel;
    protected JButton bAdd, bSub, bMult, bDiv, bSq, bRt;
    protected JButton bMRec, bMClr, bMAdd, bMSub;

    protected JButton bEnter;
    protected JTextField tDisplay;

    protected Font defaultFont;
    protected Font boldFont;

    protected Color defaultColor;
    protected Color selectedColor;

    public CalculatorGUI() {
        initializeComponents();
        SwingUtilities.invokeLater(this::initializeDisplay);
    }

    private void initializeDisplay() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jpMain = new JPanel();
        jpMain.setLayout(new GridBagLayout());

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 1;
        gridConstraints.weighty = 1;
        gridConstraints.insets = new Insets(4, 4, 4, 4);
        gridConstraints.anchor = GridBagConstraints.CENTER;
        gridConstraints.fill = GridBagConstraints.BOTH;

        tDisplay.setHorizontalAlignment(JTextField.RIGHT);
        tDisplay.setEditable(false);
        gridConstraints.gridwidth = 5;
        jpMain.add(tDisplay, gridConstraints);

        gridConstraints.gridwidth = 1;
        addButtonsInGrid(jpMain, gridConstraints, new Component[][]{
                {null, null, null, null, null, bMRec},
                {b7, b8, b9, bClr, bDel, bMClr},
                {b4, b5, b6, bAdd, bSub, bMAdd},
                {b1, b2, b3, bMult, bDiv, bMSub},
                {null, b0, bDot, bSq, bRt, bEnter}
        }, 0, 0);

        setStyleOnAll(jpMain, defaultFont, defaultColor);
        this.add(jpMain);
        this.setVisible(true);
    }

    /**
     * Initialize all components to the most basic degree.
     */
    private void initializeComponents() {
        tDisplay = new JTextField();

        //Ideally we would extract these values into statics for less coupling. But I think that's beyond the scope of
        // this assignment.
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bDot = new JButton(".");

        bClr = new JButton("C");
        bDel = new JButton("D");

        bAdd = new JButton("+");
        bSub = new JButton("-");
        bMult = new JButton("*");
        bDiv = new JButton("/");
        bSq = new JButton("sq");
        bRt = new JButton("rt");

        bMRec = new JButton("MR");
        bMClr = new JButton("MC");
        bMAdd = new JButton("M+");
        bMSub = new JButton("M-");

        bEnter = new JButton("=");

        defaultFont = new Font("Helvetica", Font.PLAIN, 24);
        boldFont = new Font("Helvetica", Font.BOLD, 24);

        defaultColor = new Color(220, 220, 255);
        selectedColor = new Color(210, 200, 255);
    }

    private void addButtonsInGrid(JPanel panel, GridBagConstraints gridContstraints, Component[][] compGrid, int xOffset, int yOffset) {
        for (int y = 0; y < compGrid.length; y++) {
            Component[] compRow = compGrid[y];
            for (int x = 0; x < compRow.length; x++) {
                Component comp = compRow[x];
                if (comp == null) continue;
                gridContstraints.gridy = 1 + y + yOffset;
                gridContstraints.gridx = 1 + x + xOffset;
                panel.add(comp, gridContstraints);
            }
        }
    }

    private void setStyleOnAll(Component component, Font font, Color color) {
        component.setFont(font);
        component.setBackground(color);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setStyleOnAll(child, font, color);
            }
        }
    }

    private void addActionListenerToAll(JButton[] buttons, ActionListener actionListener) {
        for (JButton b : buttons) {
            b.addActionListener(actionListener);
        }
    }

    public void addNumberListener(ActionListener numberListener) {
        addActionListenerToAll(new JButton[]{
                b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDot
        }, numberListener);
    }

    public void addOperationListener(ActionListener operationListener) {
        addActionListenerToAll(new JButton[]{
                bAdd, bSub, bMult, bDiv
        }, operationListener);
    }

    public void addCommandListener(ActionListener commandListener) {
        addActionListenerToAll(new JButton[]{
                bClr, bDel, bEnter, bSq, bRt, bMRec, bMClr, bMAdd, bMSub
        }, commandListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            Model model = (Model) o;
            tDisplay.setText(model.getDisplay());
            updateOperatorStyles(model.getOperation());
        }
    }

    public void updateOperatorStyles(Operation selectedOperator) {
        JButton[] operatorButtons = new JButton[]{bAdd, bSub, bMult, bDiv};

        for(JButton b : operatorButtons) {
            if(b.getText().equals(selectedOperator.toSymbol())) {
                b.setFont(boldFont);
                b.setBackground(selectedColor);
            } else {
                b.setFont(defaultFont);
                b.setBackground(defaultColor);
            }
        }
    }
}