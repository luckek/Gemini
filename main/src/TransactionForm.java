import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionForm extends JDialog {

    private JComboBox<String> nameBox;
    private JFormattedTextField dateField;
    private JTextField amntField;
    private JComboBox<String> codeBox;
    private JComboBox<String> typeBox;
    private JComboBox<String> depositExpenseBox;
    private String[] transactionTypes = new String[] {"Check", "Credit Card", "Cash", "Custom"}; // Make enum class / constant of transaction class?
    private String[] checkCodes = new String[] {"1000", "2000"};
    private String[] creditCodes = new String[] {"3000", "4000"};
    private String[] cashCode = new String[] {"0000"};
    private ArrayList<String> customCodes = new ArrayList<>();
    private Controller controller;

    private final DateFormat dateFormatter = new SimpleDateFormat("mm/dd/yyyy");
    
    public TransactionForm(Frame frame, String title, boolean modality, String[] accountNames) {

        super(frame, title, modality);

        try {
			customCodes =  controller.loadCustomCodes();
		} catch (FileNotFoundException e1) {
			System.out.println("FileNotFound Exception: ");
			e1.printStackTrace();
		}
        setPreferredSize(new Dimension(300, 400));

        // Creating components
        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameBox = new JComboBox<>(accountNames);
        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JFormattedTextField(dateFormatter);
        dateField.setColumns(10);
        JLabel amntLabel = new JLabel("Amount: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        amntField = new JTextField(10);
        JLabel typeLabel = new JLabel("Type: ");
        typeBox = new JComboBox<>(transactionTypes);
        JLabel codeLabel = new JLabel("Code: ");
        codeBox = new JComboBox<>(checkCodes);
        JButton okButton = new JButton("Ok");
        JLabel debitCreditLabel = new JLabel("Exp / Dep");
        depositExpenseBox = new JComboBox<>(new String[] {"Expense", "Deposit"});
        
        try {
        	MaskFormatter dateMask = new MaskFormatter("##/##/####");
        	dateMask.setPlaceholderCharacter('_');
        	dateMask.install(dateField);
        	
        } catch (ParseException e) { 
        	System.out.println("Parse Exception: ");
        	e.printStackTrace();
        }

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel datePanel = new JPanel();
        JPanel amntPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel codePanel = new JPanel();
        JPanel typePanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel debitCreditPanel = new JPanel();

        // Setting layouts / sizing
        centerPanel.setLayout(new GridLayout(7, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(300, 400));
        nameBox.setPreferredSize(new Dimension(112, 20));
        typeBox.setPreferredSize(new Dimension(112, 20));
        depositExpenseBox.setPreferredSize(new Dimension(112, 20));
        codeBox.setPreferredSize(new Dimension(112, 20));
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
        centerPanel.add(codePanel);
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

        codePanel.add(codeLabel);
        codePanel.add(Box.createRigidArea(new Dimension(46, 10)));
        codePanel.add(codeBox);

        debitCreditPanel.add(debitCreditLabel);
        debitCreditPanel.add(Box.createRigidArea(new Dimension(27, 10)));
        debitCreditPanel.add(depositExpenseBox);

        okButton.addActionListener(new okAction());
        typeBox.addActionListener(new selectionAction());

        pack();
        setVisible(true);
    }

    public String getAcctName() { return (String)nameBox.getSelectedItem(); }
    public String getDate() { return dateField.getText(); }
    public String getAmnt() { return amntField.getText(); }
    public String getTransactionType() { return (String)typeBox.getSelectedItem(); }
    public String isDeposit() { return (String) depositExpenseBox.getSelectedItem(); }
    public String getCode() { return (String)codeBox.getSelectedItem(); }

    // Closes dialog
    private void close() { this.dispose(); }

    // Creates popup warning - Empty Fields
    private void showWarning() {
        JOptionPane.showMessageDialog(this, "Please fill out all fields",
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }
    
    // Creates popup warning - Incorrect Amount Format
    private void inputWarning() {
    	JOptionPane.showMessageDialog(this,  "Transaction amount must contain a valid numerical value",
    			                      "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if(dateField.getText().isEmpty() || amntField.getText().isEmpty()){
                showWarning();
                return;
            }
            
            if(!amntField.getText().matches("^[0-9]*(\\.\\d+)?$")) {
            	inputWarning();
            	return;
            }
            close();
        }
    }

    class selectionAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           String typeStr = (String)typeBox.getSelectedItem();

           if(typeStr.equalsIgnoreCase("Check")) {

               ComboBoxModel<String> model = new DefaultComboBoxModel<>(checkCodes);
               codeBox.setModel(model);
           }
           else if(typeStr.equalsIgnoreCase("Credit Card")) {

               ComboBoxModel<String> model = new DefaultComboBoxModel<>(creditCodes);
               codeBox.setModel(model);
           } 
           else if(typeStr.equalsIgnoreCase("Custom")) {
        	   ComboBoxModel<String> model = new DefaultComboBoxModel<>(customCodes.toArray(new String[customCodes.size()]));
        	   codeBox.setModel(model);
           }
           else {

               ComboBoxModel<String> model = new DefaultComboBoxModel<>(cashCode);
               codeBox.setModel(model);
           }
        }
    }
}
