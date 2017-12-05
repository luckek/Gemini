import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CodePanel extends JDialog {

    private JTextField codeField;
    private String userCode;
    private JComboBox<String> isExpenseBox;
    private String[] expDep = {"Expense", "Deposit"};

    public CodePanel(Frame frame, String title, boolean modality) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 280));
        setResizable(false);

        // Creating components
        JLabel infoLabel = new JLabel("<html><center><p>Please enter the transaction code and type of code below:</p></center></html>");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setPreferredSize(new Dimension(260,100));

        JLabel codeLabel = new JLabel("Code");
        codeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        isExpenseBox = new JComboBox<>(expDep);

        codeField = new JTextField(10);

        JButton addButton = new JButton("Add Code");
        addButton.addActionListener(new addAction());
        getRootPane().setDefaultButton(addButton);

        JPanel mainPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel codePanel = new JPanel();
        JPanel isExpensePanel = new JPanel();

        // Layouts
        centerPanel.setLayout(new GridLayout(3, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(300, 200));
        mainPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

        // Add components
        add(mainPanel);
        mainPanel.add(addButton, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        isExpensePanel.add(isExpenseBox);

        centerPanel.add(infoPanel);
//		centerPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        centerPanel.add(codePanel);
        centerPanel.add(isExpensePanel);

        infoPanel.add(infoLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        codePanel.add(codeLabel);
        codePanel.add(codeField);

        pack();
        setVisible(true);

    }

    public String getCode() { return codeField.getText(); }

    private void emptyWarning() {

        JOptionPane.showMessageDialog(this, "Code input cannot be empty",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    private void invalidCodeWarning() {

        JOptionPane.showMessageDialog(this, "Please enter a 5-digit transaction code",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    private void duplicateCodeWarning() {

        JOptionPane.showMessageDialog(this, "Duplicate code",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    private void addConfirmation() {
        JOptionPane.showMessageDialog(this, "Code successfully added",
                "", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    class addAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            if(codeField.getText().isEmpty()) {

                emptyWarning();
                return;
            }

            if(!codeField.getText().matches("[0-9]{5}")) {

                invalidCodeWarning();
                return;
            }
            // pull code
            userCode = getCode();

            // check for duplicate codes in transaction array
            if (TransactionForm.containsCode(userCode)) {
                duplicateCodeWarning();
                return;
            }

            // call method to add to respective transaction array
            try {
				TransactionForm.addCode(userCode, (String)isExpenseBox.getSelectedItem());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            addConfirmation();
        }
    }
}
