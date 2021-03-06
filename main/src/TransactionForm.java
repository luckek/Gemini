import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TransactionForm extends JDialog {

    private JComboBox<String> nameBox;
    private JFormattedTextField dateField;
    private JTextField amntField;
    private JComboBox<String> codeBox;
    private JComboBox<String> typeBox;
    private JComboBox<String> depositExpenseBox;
    private JTextField descField;
    private String[] transactionTypes = new String[] {"Check", "Credit Card", "Cash"}; // Make enum class / constant of transaction class?
    private static String[] depositCodes = new String[] {"50109", "50287"};
    private static String[] expenseCodes = new String[] {"61123", "61225", "62210", "62241", "62245"};
    private static ArrayList<String> customCodes = new ArrayList<String>();
    private static boolean loadCodes = true;
	private static Model_Read_Files readFile = new Model_Read_Files();

    private final DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private Date date = new Date();
    
    public TransactionForm(Frame frame, String title, boolean modality, String[] accountNames) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 500));
        
        // Loads custom transaction codes
        if (loadCodes) {
            loadCodes();
        }

        // Creating components
        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameBox = new JComboBox<>(accountNames);
        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JFormattedTextField(dateFormatter);
        dateField.setColumns(10);
        dateField.setFocusLostBehavior(EXIT_ON_CLOSE); // Why is this here?
        JLabel amntLabel = new JLabel("Amount: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        amntField = new JTextField(10);
        JLabel typeLabel = new JLabel("Type: ");
        typeBox = new JComboBox<>(transactionTypes);
        JLabel codeLabel = new JLabel("Code: ");
        codeBox = new JComboBox<>(expenseCodes);
        JButton okButton = new JButton("Ok");
        JLabel expenseDepositLabel = new JLabel("Exp / Dep: ");
        depositExpenseBox = new JComboBox<>(new String[] {"Expense", "Deposit"});
        JLabel descLabel = new JLabel("Description:");
        descField = new JTextField(10);
        
        try {
        	MaskFormatter dateMask = new MaskFormatter("##/##/####");
        	dateMask.setPlaceholderCharacter('_');
        	dateMask.install(dateField);
        } catch (ParseException e) {
        	System.out.println("Parse Exception: ");
        	e.printStackTrace();
        }
        
        dateField.setText(dateFormatter.format(date));

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel datePanel = new JPanel();
        JPanel amntPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel codePanel = new JPanel();
        JPanel typePanel = new JPanel();
        JPanel descPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel expDepositPanel = new JPanel();
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Setting layouts / sizing
        centerPanel.setLayout(new GridLayout(8, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(300, 400));
        nameBox.setPreferredSize(new Dimension(112, 20));
        typeBox.setPreferredSize(new Dimension(112, 20));
        depositExpenseBox.setPreferredSize(new Dimension(112, 20));
        codeBox.setPreferredSize(new Dimension(112, 20));
        okButton.setPreferredSize(new Dimension(50, 20));
        mainPanel.setLayout(new BorderLayout());
        getRootPane().setDefaultButton(okButton);

        // Adding components
        add(mainPanel);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        bottomPanel.add(okButton);

        centerPanel.add(infoPanel);
        centerPanel.add(namePanel);
        centerPanel.add(datePanel);
        centerPanel.add(amntPanel);
        centerPanel.add(typePanel);
        centerPanel.add(expDepositPanel);
        centerPanel.add(codePanel);
        centerPanel.add(descPanel);

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

        expDepositPanel.add(expenseDepositLabel);
        expDepositPanel.add(Box.createRigidArea(new Dimension(15, 10)));
        expDepositPanel.add(depositExpenseBox);

        descPanel.add(descLabel);
        descPanel.add(Box.createRigidArea(new Dimension(13, 0)));
        descPanel.add(descField);

        okButton.addActionListener(new okAction());
        depositExpenseBox.addActionListener(new selectionAction());

        pack();
        setVisible(true);
    }

    public String getAcctName() { return (String)nameBox.getSelectedItem(); }
    public String getDate() { return dateField.getText(); }
    public String getAmnt() { return amntField.getText(); }
    public String getTransactionType() { return (String)typeBox.getSelectedItem(); }
    public String isDeposit() { return (String) depositExpenseBox.getSelectedItem(); }
    public String getCode() { return (String)codeBox.getSelectedItem(); }
    public String getDesc() {return descField.getText(); }
    
    public static void addCode(String customCode, String isExpense) throws IOException {

    	// Saves codes to file
        if(isExpense.startsWith("E")) {

        	
            expenseCodes = Arrays.copyOf(expenseCodes, expenseCodes.length + 1);
            expenseCodes[expenseCodes.length - 1] = customCode;

        } else {
        	
            depositCodes = Arrays.copyOf(depositCodes, depositCodes.length + 1);
            depositCodes[depositCodes.length - 1] = customCode;
        }
        
        readFile.saveCode(customCode, isExpense);
    }
    
    // Loads codes when window is first opened
    public static void loadCodes() {

        customCodes = readFile.loadCodes();

    	for (String code : customCodes) {
    		String customCode = code.substring(0, code.indexOf(','));
    		String isExpense = code.substring(code.indexOf(',') + 1);
    		
    		if (isExpense.startsWith("E")) {
    			expenseCodes = Arrays.copyOf(expenseCodes, expenseCodes.length + 1);
    			expenseCodes[expenseCodes.length - 1] = customCode;
    		}
    		
    		else if (isExpense.startsWith("D")) {
    			depositCodes = Arrays.copyOf(depositCodes, depositCodes.length + 1);
    			depositCodes[depositCodes.length - 1] = customCode;
    		}
    		
    		loadCodes = false;
    		
    	}
    }
    
    public static boolean containsCode(String customCode) {

        // Check expense codes
        for(String code : expenseCodes) {
            if(code.equalsIgnoreCase(customCode)) {
                return true;
            }
        }

        // Check deposit codes
        for(String code : depositCodes) {
            if(code.equalsIgnoreCase(customCode)) {
                return true;
            }
        }

        // If they are not found, code doesn't exist
    	return false;
    }

    // Closes dialog
    private void close() { this.dispose(); }

    // Creates popup warning with specified message
    private void showWarning(String msg) {
        JOptionPane.showMessageDialog(this, msg,
                "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if(dateField.getText().isEmpty() || amntField.getText().isEmpty()){
                showWarning("Please fill out all fields");
                return;
            }
            
            if(!amntField.getText().matches("^[0-9]*(\\.\\d+)?$")) {
                showWarning("Transaction amount must contain a valid numerical value");
            	return;
            }
            close();
        }
    }

    class selectionAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           String isDeposit = (String)depositExpenseBox.getSelectedItem();

           if (isDeposit == null) { return; }

           // Deposit
           if(isDeposit.startsWith("D")) {

               ComboBoxModel<String> model = new DefaultComboBoxModel<>(depositCodes);
               codeBox.setModel(model);
           } // Expense
           else if(isDeposit.startsWith("E")) {

               ComboBoxModel<String> model = new DefaultComboBoxModel<>(expenseCodes);
               codeBox.setModel(model);
           }
        }
    }
}
