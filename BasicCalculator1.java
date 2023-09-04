import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;


public class BasicCalculator1 extends JFrame implements ActionListener {
    private JTextField textField;

    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton decimalButton, equalsButton, deleteButton, clearButton;
    private JButton emptyButton;

    private double firstNumber, secondNumber, result;
    private String operator;

    public BasicCalculator1() {
        setTitle("Basic Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 60));
        textField.setForeground(Color.white);
        textField.setBackground(Color.black);
        textField.setEditable(true);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            numberButtons[i].setOpaque(true);
            numberButtons[i].setBackground(Color.darkGray);
            numberButtons[i].setForeground(Color.white);
            numberButtons[i].setBorderPainted(false);
        }


        addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.PLAIN, 25));
        addButton.addActionListener(this);
        addButton.setOpaque(true);
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.white);
        addButton.setBorderPainted(false);

        subtractButton = new JButton("-");
        subtractButton.setFont(new Font("Arial", Font.PLAIN, 30));
        subtractButton.addActionListener(this);
        subtractButton.setOpaque(true);
        subtractButton.setBackground(Color.darkGray);
        subtractButton.setForeground(Color.white);
        subtractButton.setBorderPainted(false);

        multiplyButton = new JButton("x");
        multiplyButton.setFont(new Font("Arial", Font.PLAIN, 20));
        multiplyButton.addActionListener(this);
        multiplyButton.setOpaque(true);
        multiplyButton.setBackground(Color.darkGray);
        multiplyButton.setForeground(Color.white);
        multiplyButton.setBorderPainted(false);

        divideButton = new JButton("÷");
        divideButton.setFont(new Font("Arial", Font.PLAIN, 30));
        divideButton.addActionListener(this);
        divideButton.setOpaque(true);
        divideButton.setBackground(Color.darkGray);
        divideButton.setForeground(Color.white);
        divideButton.setBorderPainted(false);


        decimalButton = new JButton(".");
        decimalButton.setFont(new Font("Arial", Font.PLAIN, 40));
        decimalButton.setOpaque(true);
        decimalButton.setBackground(Color.darkGray);
        decimalButton.setForeground(Color.white);
        decimalButton.setBorderPainted(false);

        equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.PLAIN, 20));
        equalsButton.setOpaque(true);
        equalsButton.setBackground(Color.green);
        equalsButton.setForeground(Color.black);
        equalsButton.setBorderPainted(false);

        deleteButton = new JButton("⌫");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 20));
        deleteButton.setOpaque(true);
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        deleteButton.setBorderPainted(false);

        clearButton = new JButton("AC");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 20));
        clearButton.setOpaque(true);
        clearButton.setBackground(Color.orange);
        clearButton.setForeground(Color.black);
        clearButton.setBorderPainted(false);

        emptyButton = new JButton("(+/-)");
        emptyButton.setFont(new Font("Arial", Font.PLAIN, 22));
        emptyButton.setOpaque(true);
        emptyButton.setBackground(Color.darkGray);
        emptyButton.setForeground(Color.white);
        emptyButton.setBorderPainted(false);
        emptyButton.addActionListener(this);

        decimalButton.addActionListener(this);
        equalsButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(emptyButton);
        buttonPanel.add(divideButton);
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(multiplyButton);
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(subtractButton);
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(addButton);
        buttonPanel.add(decimalButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);

        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {

        new BasicCalculator1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.equals("AC")) {
            textField.setText("");
        } else if (input.equals("⌫")) {
            String currentText = textField.getText();
            if (!currentText.isEmpty()) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (input.equals("(+/-)")) {
            String str = textField.getText();
            if (!str.contains("-")) {
                textField.setText("-" + str);
            }
        } else if (input.equals("+") || input.equals("-") || input.equals("x") || input.equals("÷")) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = input;
            textField.setText(textField.getText() + input);

        } else if (input.equals("=")) {
            String expression = textField.getText();
            secondNumber = Double.parseDouble(expression.substring(expression.lastIndexOf(operator) + 1));

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "x":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    result = firstNumber / secondNumber;
                    break;
            }

            textField.setText(String.valueOf(result));

            operator = "";
            firstNumber = 0;
            secondNumber = 0;

        } else if (input.equals(".") && !textField.getText().contains(".")) {
            textField.setText(textField.getText() + ".");
        } else {
            textField.setText(textField.getText() + input);
        }
    }
}