
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// TODO: make sure exiting w/all fields filled out does NOT make a new acct / transaction

public class InitialScreen extends JFrame {

    private JComboBox<String> accountList;
    private JTable transactionTable;
    private JLabel grossBalanceLabel;
    private JLabel netBalanceLabel;
    private JLabel feesLabel;
    private JLabel logoLabel;
    private String logoPath = "main/resources/logo1.png";
    private ButtonGroup radioGroup;
    private Controller controller;
    private String[] columnNames = {"Name", "Date", "Gross Amt", "Type", "Code", "Net Amt", "Exp / Dep", "Fees"};
    private boolean changeCheck = false;
    private boolean inInit;

    public InitialScreen(String title) {

        super(title);

        // Configuring frame behavior
        setVisible(true);

        setSize(1100, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Instantiating components
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(13, 0, 20, 31));
        JPanel leftPanel = new JPanel();
        JPanel wrapperPanel = new JPanel();
        JPanel transactionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel outterPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel grossBalancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel netBalancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel feesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel outterBalancePanel = new JPanel();
        JPanel addRemovePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel radioPanel = new JPanel();

        JLabel acctLabel = new JLabel("Account: ");
        JLabel transactionLabel = new JLabel("Transactions: ");
        JLabel devLabel = new JLabel(Main.DEV_STRING);
        grossBalanceLabel = new JLabel();
        netBalanceLabel = new JLabel();
        feesLabel = new JLabel();

        JButton addAcctButton = new JButton("Add Account");
        JButton calcBttn = new JButton("Benefits Calculator");
        JButton acctInfoButton = new JButton("View Account Info");
        JButton viewBalancesButton = new JButton("View Account Balances");
        JButton deleteButton = new JButton("Delete Account");
        JButton logoutBttn = new JButton("Logout");
        JButton retireBttn = new JButton("Retire Account");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton codeButton = new JButton("Add New Code");

        radioGroup = new ButtonGroup();
        JRadioButton bothButton = new JRadioButton("Both", true);
        JRadioButton debitButton = new JRadioButton("Expense");
        JRadioButton creditButton = new JRadioButton("Deposit");

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenu printMenu = new JMenu("Print");
      
        JMenuItem save = new JMenuItem("Save");
        JMenuItem userGuide = new JMenuItem("User Guide");
        JMenuItem print = new JMenuItem("Print");

        accountList = new JComboBox<>(); // This should be populated by a list of all accounts
        transactionTable = new JTable(new DefaultTableModel(columnNames, 0));

        // More components
        JScrollPane transactionPane = new JScrollPane(transactionTable);
        transactionTable.setFillsViewportHeight(true);

        // Layouts and sizing
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.LINE_AXIS));
        outterBalancePanel.setLayout(new BoxLayout(outterBalancePanel, BoxLayout.LINE_AXIS));
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        wrapperPanel.setLayout(new GridLayout(0, 1));

        leftPanel.setMaximumSize(new Dimension(700, 200));
        transactionPane.setMaximumSize(new Dimension(700, 500));
        headerPanel.setPreferredSize(new Dimension(300, 25));
        outterBalancePanel.setPreferredSize(new Dimension(100, 100));
        addButton.setPreferredSize(new Dimension(100, 22));
        removeButton.setPreferredSize(new Dimension(100, 22));
        codeButton.setPreferredSize(new Dimension(150, 22));
        wrapperPanel.setPreferredSize(new Dimension(150, 700));
        wrapperPanel.setMaximumSize(new Dimension(150, 700));
        radioPanel.setMaximumSize(new Dimension(150, 700));
        grossBalanceLabel.setPreferredSize(new Dimension(65, 20));

        bothButton.setActionCommand("Both");
        creditButton.setActionCommand("Deposit");
        debitButton.setActionCommand("Expense");


        // Setting up Menu

        // File Menu
        fileMenu.add(save);
        menuBar.add(fileMenu);
        printMenu.add(print);
        menuBar.add(printMenu);
        
        // Help Menu
        helpMenu.add(userGuide);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);

        // Populating panels / content pane
        add(mainPanel);

        mainPanel.add(leftPanel, BorderLayout.LINE_START);
        mainPanel.add(outterPanel, BorderLayout.CENTER);
        mainPanel.add(devLabel, BorderLayout.PAGE_END);
        mainPanel.add(wrapperPanel, BorderLayout.LINE_END);

        leftPanel.add(Box.createRigidArea(new Dimension(50, 200)));
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createVerticalBox());

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(acctLabel);
        buttonPanel.add(accountList);
        buttonPanel.add(addAcctButton);
        buttonPanel.add(calcBttn);
        buttonPanel.add(deleteButton);
        buttonPanel.add(retireBttn);
        buttonPanel.add(acctInfoButton);
        buttonPanel.add(viewBalancesButton);
        buttonPanel.add(logoutBttn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        outterPanel.add(transactionPanel, BorderLayout.CENTER);
        outterPanel.add(headerPanel, BorderLayout.PAGE_START);
        outterPanel.add(Box.createRigidArea(new Dimension(0, 35)), BorderLayout.LINE_START);
        outterPanel.add(outterBalancePanel, BorderLayout.PAGE_END);

        transactionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        transactionPanel.add(addRemovePanel);
        transactionPanel.add(transactionPane);

        addRemovePanel.add(addButton);
        addRemovePanel.add(Box.createRigidArea(new Dimension(15, 0)));
        addRemovePanel.add(removeButton);
        addRemovePanel.add(Box.createRigidArea(new Dimension(15, 0)));
        addRemovePanel.add(codeButton);

        headerPanel.add(transactionLabel);

        outterBalancePanel.add(Box.createRigidArea(new Dimension(70, 0)));
        outterBalancePanel.add(grossBalancePanel);
        outterBalancePanel.add(Box.createRigidArea(new Dimension(90, 0)));
        outterBalancePanel.add(netBalancePanel);
        outterBalancePanel.add(Box.createRigidArea(new Dimension(0, 0)));
        outterBalancePanel.add(feesPanel);

        grossBalancePanel.add(new JLabel("Gross Balance:"));
        grossBalancePanel.add(grossBalanceLabel);

        netBalancePanel.add(new JLabel("Net Balance:"));
        netBalancePanel.add(netBalanceLabel);

        feesPanel.add(new JLabel("Fees total:"));
        feesPanel.add(feesLabel);

        radioGroup.add(bothButton);
        radioGroup.add(debitButton);
        radioGroup.add(creditButton);

        wrapperPanel.add(radioPanel);

        radioPanel.add(Box.createRigidArea(new Dimension(30, 250)));
        radioPanel.add(bothButton);
        radioPanel.add(Box.createRigidArea(new Dimension(30, 20)));
        radioPanel.add(debitButton);
        radioPanel.add(Box.createRigidArea(new Dimension(30, 20)));
        radioPanel.add(creditButton);

        // Setting up action listeners
        addAcctButton.addActionListener(new addAction());
        calcBttn.addActionListener(new calcAction());
        deleteButton.addActionListener(new deleteAction());
        retireBttn.addActionListener(new retireAction());
        codeButton.addActionListener(new codeAction());
        acctInfoButton.addActionListener(new acctInfoAction());
        viewBalancesButton.addActionListener(new acctBalancesAction());
        logoutBttn.addActionListener(new logoutAction());
        addButton.addActionListener(new addTransaction());
        removeButton.addActionListener(new removeTransaction());
        accountList.addActionListener(new accountListener());
        bothButton.addActionListener(new accountListener());
        debitButton.addActionListener(new accountListener());
        creditButton.addActionListener(new accountListener());
        save.addActionListener(new saveListener());
        userGuide.addActionListener(new guideListener());
        print.addActionListener(new printListener());
    }

    void initComboBox(String[] accountNames) {
        for (String name : accountNames) {
            accountList.addItem(name);
        }
    }

    void initTranscationTable(String[][] data) {

        inInit = true;
        for (String[] row : data) {
            addTableRow(row);
        }
        setCellsAlignment(transactionTable, SwingConstants.CENTER);
        updateBalance();
        inInit = false;
    }

    private void setTotal(double newBalance, JLabel labelToUpdate) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String newBalanceStr = formatter.format(newBalance);

        if(newBalanceStr.substring(newBalanceStr.length() - 1).equalsIgnoreCase(")")){
            newBalanceStr = newBalanceStr.replace("(", "");
            newBalanceStr = newBalanceStr.replace(")", "");
            newBalanceStr = "-" + newBalanceStr;
        }
        labelToUpdate.setText(newBalanceStr);
    }

    private void updateBalance() {
      
        double grossBalance = 0;
        double netBalance = 0;
        double feesTotal = 0;

        for(int i = 0; i < transactionTable.getRowCount(); i++) {

            // Get gross
            double grossValue = new Double((String)transactionTable.getValueAt(i, 2));

            // Get net
            double netValue =  new Double((String)transactionTable.getValueAt(i, 5));

            String isExpense = (String)transactionTable.getValueAt(i, 6);

            double fees = grossValue - netValue;

            // Convert to negative value if expense
            if(isExpense.startsWith("E")) {
                grossValue = -grossValue;
                netValue = -netValue;
            }

            grossBalance += grossValue;
            netBalance += netValue;
            feesTotal += fees;
        }
        setTotal(grossBalance, grossBalanceLabel);
        setTotal(netBalance, netBalanceLabel);
        setTotal(feesTotal, feesLabel);
    }

    private double getBalance(JLabel BalanceToGet) {

        String balanceStr = BalanceToGet.getText();
        balanceStr = balanceStr.replace("$", "");

        // If negative
        if(balanceStr.substring(balanceStr.length() - 1 ).equalsIgnoreCase(")")) {
            balanceStr = balanceStr.replaceAll("()", "");
        }
        return new Double(balanceStr);
    }

    private void increaseGrossBalance(double amount) {
        setTotal(getBalance(grossBalanceLabel) + amount, grossBalanceLabel);
    }

    private void decreaseGrossBalance(double amount) {
        setTotal(getBalance(grossBalanceLabel) - amount, grossBalanceLabel);
    }

    private void increaseNetBalance(double amount) {
        setTotal(getBalance(netBalanceLabel) + amount, netBalanceLabel);
    }

    private void decreaseNetBalance(double amount) {
        setTotal(getBalance(netBalanceLabel) - amount, netBalanceLabel);
    }

    private void updateFeesTotal(double amount) {
        setTotal(getBalance(feesLabel) + amount, feesLabel);
    }

    private void setCellsAlignment(JTable table, int alignment) {

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    // Uses JOptionPane to get user selected account
    private String getAcct(String optionMessage) {

        // Get all available accounts
        String[] tmp = controller.getAllAccounts();
        String[] accts;


        // Remove 'all' option
        accts = new String[tmp.length - 1];
        System.arraycopy(tmp, 1, accts, 0, accts.length);


        if (accts.length == 0) {
            JOptionPane.showMessageDialog(this, "There are no accounts to view");
        }

        return (String) JOptionPane.showInputDialog(
                this, optionMessage,
                Main.FRAME_STRING, JOptionPane.PLAIN_MESSAGE,
                null, accts, accts[0]);
    }

    // Opens user guide panel
    private void openGuide() { new GuidePanel(this, Main.FRAME_STRING, true); }
    
    private void print() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        BufferedWriter writer = new BufferedWriter(new FileWriter("transactions_" + timeStamp + ".txt"));

        // Add column names
        for(int i = 0; i < columnNames.length; i++) {
            if(i != columnNames.length - 1) {
                writer.append(columnNames[i] + ",");
            } else { // Don't append comma to end of line
                writer.append(columnNames[i]);
            }
        }
        writer.newLine();
          
        for(int i = 0; i < transactionTable.getRowCount(); i++) {

            for(int j = 0; j < transactionTable.getColumnCount(); j++) {
                String value = (String) transactionTable.getValueAt(i, j);

                if(j != transactionTable.getColumnCount() - 1) {
                    writer.append(value + ",");
                } else { // Don't append comma to end of line
                    writer.append(value);
                }
            }
            writer.newLine();
        }
        writer.close();
    }
    // Opens benefits calculator dialog
    private void openCalc() { new CalcPanel(this, Main.FRAME_STRING, true); }
    
    private void openCode() { new CodePanel(this, Main.FRAME_STRING, true); }
    
    private void createAccount() {

        // Opens dialog / form to get information about new account
        AccountForm accountForm = new AccountForm(this, Main.FRAME_STRING, true);

        // Grab information
        String newAcctName = accountForm.getName();
        String newAcctEmail = accountForm.getEmail();
        String newAcctDesc = accountForm.getDescription();
        String newAcctNumber = accountForm.getPhoneNumber();

        // Only attempt to create account if all information is present
        if ((!newAcctName.isEmpty()) && (!newAcctEmail.isEmpty()) && (!newAcctDesc.isEmpty())
                && (!newAcctNumber.isEmpty())) {

            for(String name : controller.getAllAccounts()) {

                // Check for account of the same name
                if(newAcctName.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(this, "An account with this name already exists");
                    return;
                }
            }

            accountList.addItem(newAcctName);
            controller.newAccount(newAcctName, newAcctEmail, newAcctDesc, newAcctNumber);
            // note that a change has been made
            changeCheck = true;
        }
    }

    private String[] createTransaction() {
        // Get accounts
        String[] tmp = controller.getAvailableAccts();
        String[] accts = new String[tmp.length - 1];

        // Remove 'All' option.
        System.arraycopy(tmp, 1, accts, 0, accts.length);


        // Open form and initialize it
        TransactionForm tForm = new TransactionForm(this, Main.FRAME_STRING, true, accts);

        String[] transactionData = new String[6];

        // Get transaction data
        transactionData[0] = tForm.getAcctName();
        transactionData[1] = tForm.getDate();
        transactionData[2] = tForm.getAmnt();
        transactionData[3] = tForm.getTransactionType();
        transactionData[4] = tForm.getCode();
        transactionData[5] = tForm.isDeposit();
        return transactionData;
    }

    private void addTableRow(String[] rowData) {

        // Get model and add data to it
        DefaultTableModel tmpModel = (DefaultTableModel) transactionTable.getModel();
        tmpModel.addRow(rowData);

        // Only note change if not initializing
        if(!inInit) {
            // note that a change has been made
            changeCheck = true;
        }
    }

    private void removeTableRow(int viewIndex) {
      
        // Convert index between view and model
        DefaultTableModel tmpModel = (DefaultTableModel) transactionTable.getModel();
        int modelIndex = transactionTable.convertRowIndexToModel(viewIndex);

        // Get transaction info
        String name = (String)tmpModel.getValueAt(modelIndex, 0);
        String date = (String)tmpModel.getValueAt(modelIndex, 1);
        String gross = (String)tmpModel.getValueAt(modelIndex, 2);
        String type = (String)tmpModel.getValueAt(modelIndex, 3);
        String expDep = (String)tmpModel.getValueAt(modelIndex, 6);
        Model_Transaction t;

        // Create transaction
        if(type.equalsIgnoreCase("Cash")) {

            t = new Model_Cash(type, name, 0, expDep, new Double(gross), date);
        }
        else if(type.equalsIgnoreCase("Check")) {

            t = new Model_Check(type, name, 0, expDep, new Double(gross), date);

        } else {

            t = new Model_Credit(type, name, 0, expDep, new Double(gross), date);

        }

        // Remove row
        tmpModel.removeRow(modelIndex);

        // Update model
        controller.removeTransaction(t);

        // Note that a change has been made
        changeCheck = true;
    }

    private void deleteAccount(String acctToRemove) {

        DefaultTableModel tmpModel = (DefaultTableModel)transactionTable.getModel();
        String nameStr;

        // Iterate through rows
        for(int i = 0; i < tmpModel.getRowCount(); i++) {

            nameStr = (String)tmpModel.getValueAt(i, 0);

            // If account has transaction associated with it, don't allow removal
            if(nameStr.equalsIgnoreCase(acctToRemove)) {
                JOptionPane.showMessageDialog(this, "You cannot delete an account that has transactions associated with it");
                return;
            }
        }

        accountList.removeItem(acctToRemove);
        controller.removeAccount(acctToRemove);
    }

    // Displays all the account information for acctToView
    private void viewAcct(String acctToView) {
        Account acct = controller.getAccountInfo(acctToView);
        new AcctInfoForm(this, Main.FRAME_STRING, true, acct);
    }

    private void viewBalances() {
        new AllBalancesForm(this, Main.FRAME_STRING, true, controller.NamesAndBalances());
    }

    private int getTransactionIndex() {

        DefaultTableModel tmpModel = (DefaultTableModel) transactionTable.getModel();
        int rowIndex = Integer.MIN_VALUE;

        if (tmpModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "There are no transactions to remove!",
                    "Warning!", JOptionPane.WARNING_MESSAGE);

            return rowIndex;
        }

        // Get row index from user
        String rowStr = JOptionPane.showInputDialog(this, "Please enter the row index of the transaction to be removed");

        // If user enters something, parse the index
        if (rowStr == null || rowStr.isEmpty()) {
            return rowIndex;
        } else {
            rowIndex = Integer.parseInt(rowStr);
        }

        // If user actually entered something
        if (rowIndex != Integer.MIN_VALUE) {
            while (rowIndex < 0 || rowIndex > tmpModel.getRowCount()) {

                JOptionPane.showMessageDialog(this, "Please select a valid index");
                rowStr = JOptionPane.showInputDialog(this, "Please enter the row index of the transaction to be removed");

                // If user decided to exit
                if (rowStr == null) {
                    rowIndex = Integer.MIN_VALUE;
                    return rowIndex;

                // Else parse integer and make sure it is valid
                } else {
                    rowIndex = Integer.parseInt(rowStr);
                }
            }
        }
        return rowIndex - 1;
    }

    // Creates popup warning
    private int showWarning(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Warning!", JOptionPane.YES_NO_OPTION);
    }
  
    // Method to save data
    private void save() {

        boolean saveAccountCheck = false;
        boolean saveDataCheck = false;

        try {
            controller.saveAccounts();
            saveAccountCheck = true;
        } catch(IOException e1) {
            System.out.println("Error saving file...");
            e1.printStackTrace();
        }

        try {
            controller.saveData();
            saveDataCheck = true;
        } catch(IOException e2) {
            System.out.println("Error saving file...");
            e2.printStackTrace();
        }

        if(saveAccountCheck && saveDataCheck) {
            saveDialog();
            changeCheck = false;
        }
    }

    // Create dialog box when there is a successful save
    private void saveDialog() { JOptionPane.showMessageDialog(this, "Save successful"); }

    void setController(Controller controller) {
        this.controller = controller;
    }

    class addAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createAccount();
        }
    }

    class calcAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            openCalc();
        }
    }

    class codeAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		openCode();
    	}
    }

    class deleteAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String acctToDelete = getAcct("Please select the account you want to delete");
            if ((acctToDelete != null) && (!acctToDelete.isEmpty())) {
                int option = showWarning("Are you sure you want to delete this account?");
                if (option == 0) {
                    deleteAccount(acctToDelete);
                    changeCheck = true;
                }
            }
        }
    }

    class retireAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String acct = getAcct("Please select the account you would like to retire");

            if(acct != null) {
                controller.retireAccount(acct);
                accountList.removeItem(acct);
            }
        }
    }

    class acctInfoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String acctToView = getAcct("Please select the account you would like to view");
            if ((acctToView != null) && (!acctToView.isEmpty())) {
                viewAcct(acctToView);
            }
        }
    }

    class acctBalancesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewBalances();
        }
    }

    class logoutAction implements ActionListener {
      
        @Override
        public void actionPerformed(ActionEvent e) {

        	// initialize save value
        	int save = -2;
        	
        	// Ask user if they want to save changes
        	if(changeCheck) {
        		save = showWarning("Changes have been made, would you like to save?");
        	}
        	
        	// If the user said yes, save before logging out
        	if(save == 0) {
        		save();
        	} else if(save == -1) {
        	    return;
            }
        	
            // Closes current frame and opens LoginPanel when logout button is pressed
            dispose();
            LoginPanel loginPanel = new LoginPanel();
            loginPanel.startPanel();
        }
    }

    class addTransaction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] newRowData = createTransaction();

            // Don't want to create a transaction if we have missing data
            for (String data : newRowData) {
                if (data == null || data.isEmpty()) {
                    return;
                }
            }

            Model_Transaction transaction;

            // Decide type of transaction
            if(newRowData[3].equalsIgnoreCase("Cash")) {

                transaction = new Model_Cash(newRowData[3], newRowData[0], new Integer(newRowData[4]), newRowData[5], new Double(newRowData[2]), newRowData[1]);

            } else if(newRowData[3].equalsIgnoreCase("Credit Card")) {

                transaction = new Model_Credit(newRowData[3], newRowData[0], new Integer(newRowData[4]), newRowData[5], new Double(newRowData[2]), newRowData[1]);

            } else { // Check

                transaction = new Model_Check(newRowData[3], newRowData[0], new Integer(newRowData[4]), newRowData[5], new Double(newRowData[2]), newRowData[1]);
            }

            double grossAmount = transaction.getGross();
            double netAmount = transaction.getNet();
            double fees = grossAmount - netAmount;

            // Add transaction to table
            addTableRow(transaction.getTransactionInfo());

            if(transaction.isDeposit().startsWith("E")) {
                grossAmount = -grossAmount;
                netAmount = -netAmount;
            }

            // Update balances
            increaseGrossBalance(grossAmount);
            increaseNetBalance(netAmount);
            updateFeesTotal(fees);

            // Update model
            controller.addTransaction(transaction);
        }
    }

    class removeTransaction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = getTransactionIndex();

            // If index IS MIN_VALUE nothing was selected, so we should not remove a transaction
            if (index != Integer.MIN_VALUE) {

                String grossAmountStr = (String)transactionTable.getValueAt(index, 2);
                double grossAmount = new Double(grossAmountStr);

                String netAmountStr = (String)transactionTable.getValueAt(index, 5);
                double netAmout = new Double(netAmountStr);

                double fees = grossAmount - netAmout;

                String isDeposit = (String) transactionTable.getValueAt(index, 6);

                // Ask if they would like to delete
                int option = showWarning("Are you sure you want to delete this transaction?");

                // If yes, delete
                if (option == 0) {
                    // If Expense, want to add amount back
                    if (isDeposit.startsWith("E")) {
                        grossAmount = -grossAmount;
                        netAmout = -netAmout;
                    }
                    // Update balances
                    decreaseGrossBalance(grossAmount);
                    decreaseNetBalance(netAmout);
                    updateFeesTotal(-fees);
                    removeTableRow(index);
                }
            }
        }
    }

    class accountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String filterString = (String) accountList.getSelectedItem();
            String filterString2 = radioGroup.getSelection().getActionCommand();

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(((DefaultTableModel) transactionTable.getModel()));

            RowFilter nameFilter = null;
            RowFilter typeFilter = null;

            ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>(2);

            if (filterString != null && !filterString.equalsIgnoreCase("All")) {

                nameFilter = RowFilter.regexFilter(filterString);
            }

            if (filterString2 != null && !filterString2.equalsIgnoreCase("Both")) {

                typeFilter = RowFilter.regexFilter(filterString2);
            }

            if (nameFilter == null && typeFilter == null) {

                transactionTable.setRowSorter(null);

            } else if (nameFilter == null) {

                typeFilter = RowFilter.regexFilter(filterString2);
                sorter.setRowFilter(typeFilter);

                transactionTable.setRowSorter(sorter);

            } else if (typeFilter == null) {

                nameFilter = RowFilter.regexFilter(filterString);
                sorter.setRowFilter(nameFilter);

                transactionTable.setRowSorter(sorter);

            } else {

                filters.add(nameFilter);
                filters.add(typeFilter);

                sorter.setRowFilter(RowFilter.andFilter(filters));

                transactionTable.setRowSorter(sorter);
            }

            // Update / calculate balance
            updateBalance();
        }
    }

    class saveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    class guideListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // User guide routine here
            openGuide();
        }
    }
  
    class printListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
				print();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
    }
}
