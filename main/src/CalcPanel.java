
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalcPanel extends JDialog {

    private JTextField amntField;
    private JTextField percentField;
    private JLabel resultLabel;

    public CalcPanel(Frame frame, String title, boolean modality) {

        super (frame, title, modality);

        setPreferredSize(new Dimension(300, 220));

        // Creating components
        JLabel amntLabel = new JLabel ("Amount: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel percentLabel = new JLabel ("Percent: ");
        percentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultLabel = new JLabel ("Result: ");

        amntField = new JTextField(10);
        percentField = new JTextField(10);

        JButton calcButton = new JButton("Calculate");
        calcButton.addActionListener(new calcAction());
        getRootPane().setDefaultButton(calcButton);

        JPanel mainPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel amntPanel = new JPanel();
        JPanel percentPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        // Layouts
        centerPanel.setLayout(new GridLayout(5, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(300, 400));
        calcButton.setPreferredSize(new Dimension(100, 20));
        mainPanel.setLayout(new BorderLayout());

        // Add components
        add(mainPanel);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bottomPanel.add(calcButton);

        centerPanel.add(amntPanel);
        centerPanel.add(percentPanel);
        centerPanel.add(resultPanel);

        amntPanel.add(amntLabel);
        amntPanel.add(Box.createRigidArea(new Dimension(39, 10)));
        amntPanel.add(amntField);

        percentPanel.add(percentLabel);
        percentPanel.add(Box.createRigidArea(new Dimension(39, 10)));
        percentPanel.add(percentField);

        resultPanel.add(resultLabel);
        resultPanel.add(Box.createRigidArea(new Dimension(39, 10)));

        pack();
        setVisible(true);
    }

    // Calculate result & update label
    private void calculate() {
        DecimalFormat formatter = new DecimalFormat("#.00");

        String amount = amntField.getText();
        String percent = percentField.getText();

        double result = Double.parseDouble(amount) * (Double.parseDouble(percent) / 100);

        resultLabel.setText("Result: $"+formatter.format(result));
    }

    // Close dialog
    private void close() { this.dispose(); }

    // Create popup warning - Empty Fields
    private void emptyWarning() {

        JOptionPane.showMessageDialog(this, "Please fill out all fields",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    // Create popup warning - Incorrect Amount Format
    private void amountWarning() {

        JOptionPane.showMessageDialog(this, "Amount must contain a valid numerical value",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    // Create popup warning - Incorrect percent format
    private void percentWarning() {

        JOptionPane.showMessageDialog(this, "Percent must contain a valid numerical value only",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class calcAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            if(amntField.getText().isEmpty() || percentField.getText().isEmpty()) {

                emptyWarning();
                return;
            } else if(!amntField.getText().matches("^[0-9]*(\\.\\d+)?$")) {

                amountWarning();
                return;
            } else if(!percentField.getText().matches("^[0-9]*(\\.\\d+)?$")) {

                percentWarning();
                return;
            }
            calculate();
        }
    }
}
