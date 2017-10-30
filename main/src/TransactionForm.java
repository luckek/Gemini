import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionForm extends JDialog {

    private JComboBox<String> nameBox;
    private JTextField dateField;
    private JTextField amntField;
    private JTextField descriptionField;
    private JComboBox<String> typeBox;
    private JComboBox<String> debitCreditBox;
    private String[] transactionTypes = new String[] {"Check", "Credit Card", "Cash"}; // Make enum class / constant of transaction class?

    public TransactionForm(Frame frame, String title, boolean modality, String[] accountNames) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 400));

        // Creating components
        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameBox = new JComboBox<>(accountNames);
        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField(10);
        JLabel amntLabel = new JLabel("Amount: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        amntField = new JTextField(10);
        JLabel typeLabel = new JLabel("Type: ");
        typeBox = new JComboBox<>(transactionTypes);
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionField = new JTextField(10);
        JButton okButton = new JButton("Ok");
        JLabel debitCreditLabel = new JLabel("Dr / Cr");
        debitCreditBox = new JComboBox<>(new String[] {"Credit", "Debit"});

        okButton.addActionListener(new okAction());

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel datePanel = new JPanel();
        JPanel amntPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel typePanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel debitCreditPanel = new JPanel();

        // Setting layouts
        centerPanel.setLayout(new GridLayout(7, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(300, 400));
        nameBox.setPreferredSize(new Dimension(112, 20));
        typeBox.setPreferredSize(new Dimension(112, 20));
        debitCreditBox.setPreferredSize(new Dimension(112, 20));
        mainPanel.setLayout(new BorderLayout());

        // Adding components
        add(mainPanel);

        mainPanel.add(okButton, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        centerPanel.add(infoPanel);
        centerPanel.add(namePanel);
        centerPanel.add(datePanel);
        centerPanel.add(amntPanel);
        centerPanel.add(typePanel);
        centerPanel.add(descriptionPanel);
        centerPanel.add(debitCreditPanel);

        infoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        infoPanel.add(new JLabel("<html><center><p>Please enter the name of the " +
                "account</p><p>and all other pertinent information" +
                "</p></center></html>"));

        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(39, 10)));
        namePanel.add(nameBox);

        datePanel.add(dateLabel);
        datePanel.add(Box.createRigidArea(new Dimension(46, 10)));
        datePanel.add(dateField);

        amntPanel.add(amntLabel);
        amntPanel.add(Box.createRigidArea(new Dimension(28, 10)));
        amntPanel.add(amntField);

        typePanel.add(typeLabel);
        typePanel.add(Box.createRigidArea(new Dimension(45, 10)));
        typePanel.add(typeBox);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(8, 10)));
        descriptionPanel.add(descriptionField);

        debitCreditPanel.add(debitCreditLabel);
        debitCreditPanel.add(Box.createRigidArea(new Dimension(35, 10)));
        debitCreditPanel.add(debitCreditBox);

        pack();
        setVisible(true);
    }

    public String getAcctName() { return (String)nameBox.getSelectedItem(); }
    public String getDate() { return dateField.getText(); }
    public String getAmnt() { return amntField.getText(); }
    public String getTransactionType() { return (String)typeBox.getSelectedItem(); }
    public String getIsDebit() { return (String)debitCreditBox.getSelectedItem(); }
    public String getDescription() { return descriptionField.getText(); }

    // Closes dialog
    private void close() { this.dispose(); }

    // Creates popup warning
    private void showWarning() {
        JOptionPane.showMessageDialog(this, "Please fill out all fields",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if(dateField.getText().isEmpty() || amntField.getText().isEmpty() || descriptionField.getText().isEmpty() ){
                showWarning();
                return;
            }
            close();
        }
    }
}
