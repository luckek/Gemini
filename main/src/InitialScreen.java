
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

// TODO: make sure exiting w/all fields filled out does NOT make a new acct / transaction

public class InitialScreen extends JFrame {

    private JComboBox<String> accountList;
    private JTable transactionTable;
    private JLabel balanceLabel;
    private ButtonGroup radioGroup;
    private Controller controller;
    private String[] columnNames = {"Name", "Date", "Gross Amt", "Type", "Code", "Exp / Dep", "Net Amt"};
  
    public InitialScreen(String title) {

        super(title);

        // Configuring frame behavior
        setVisible(true);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Instantiating components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(12, 0, 20, 35));
        JPanel leftPanel = new JPanel();
        JPanel wrapperPanel = new JPanel();
        JPanel transactionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel outterPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel outterBalancePanel = new JPanel();
        JPanel addRemovePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel radioPanel = new JPanel();

        JLabel acctLabel = new JLabel("Account: ");
        JLabel transactionLabel = new JLabel("Transactions: ");
        JLabel devLabel = new JLabel(Main.DEV_STRING);
        balanceLabel = new JLabel();

        JButton addAcctButton = new JButton("Add Account");
        JButton calcBttn = new JButton("Benefits Calculator");
        JButton acctInfoButton = new JButton("View Account Info");
        JButton deleteButton = new JButton("Delete Account");
        JButton logoutBttn = new JButton("Logout");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        radioGroup = new ButtonGroup();
        JRadioButton bothButton = new JRadioButton("Both", true);
        JRadioButton debitButton = new JRadioButton("Debit");
        JRadioButton creditButton = new JRadioButton("Credit");

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem userGuide = new JMenuItem("User Guide");

        accountList = new JComboBox<>(); // This should be populated by a list of all accounts
        transactionTable = new JTable(new DefaultTableModel(columnNames, 0));

        // More components
        JScrollPane transactionPane = new JScrollPane(transactionTable);
        transactionTable.setFillsViewportHeight(true);

        // Layouts and sizing
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.LINE_AXIS));
        outterBalancePanel.setLayout(new BoxLayout(outterBalancePanel, BoxLayout.PAGE_AXIS));
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        wrapperPanel.setLayout(new GridLayout(0, 1));

        leftPanel.setMaximumSize(new Dimension(700, 200));
        transactionPane.setMaximumSize(new Dimension(600, 500));
        headerPanel.setPreferredSize(new Dimension(300, 25));
        outterBalancePanel.setPreferredSize(new Dimension(100, 100));
        addButton.setPreferredSize(new Dimension(100, 22));
        removeButton.setPreferredSize(new Dimension(100, 22));
        wrapperPanel.setPreferredSize(new Dimension(150, 700));
        wrapperPanel.setMaximumSize(new Dimension(150, 700));
        radioPanel.setMaximumSize(new Dimension(150, 700));
        balanceLabel.setPreferredSize(new Dimension(150, 20));

        bothButton.setActionCommand("Both");
        creditButton.setActionCommand("Credit");
        debitButton.setActionCommand("Debit");


        // Setting up Menu

        // File Menu
        fileMenu.add(save);
        menuBar.add(fileMenu);

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
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(acctLabel);
        buttonPanel.add(accountList);
        buttonPanel.add(addAcctButton);
        buttonPanel.add(calcBttn);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutBttn);
        buttonPanel.add(acctInfoButton);
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

        headerPanel.add(transactionLabel);

        outterBalancePanel.add(balancePanel);
        outterBalancePanel.add(Box.createRigidArea(new Dimension(0, 25)));

        balancePanel.add(new JLabel("Current Balance:"));
        balancePanel.add(balanceLabel);

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
        acctInfoButton.addActionListener(new acctInfoAction());
        logoutBttn.addActionListener(new logoutAction());
        addButton.addActionListener(new addTransaction());
        removeButton.addActionListener(new removeTransaction());
        accountList.addActionListener(new accountListener());
        bothButton.addActionListener(new accountListener());
        debitButton.addActionListener(new accountListener());
        creditButton.addActionListener(new accountListener());
//        save.addActionListener(new saveListener());
        userGuide.addActionListener(new guideListener());

    }

    public void initComboBox(String[] accountNames) {
        for (String name : accountNames) {
            accountList.addItem(name);
        }
    }

    void initTranscationTable(String[][] data) {

        for (String[] row : data) {
            addTableRow(row);
        }

        setCellsAlignment(transactionTable, SwingConstants.CENTER);
        updateBalance();
    }

    private void updateBalance(double newBalance) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String newBalanceStr = formatter.format(newBalance);
        balanceLabel.setText(newBalanceStr);
    }

    private void updateBalance() {

        double balance = 0;

        for(int i = 0; i < transactionTable.getRowCount(); i++) {

            String valueStr = (String)transactionTable.getValueAt(i, 2);

            // Removes trailing zero
            if(valueStr.substring(valueStr.length() - 2).equalsIgnoreCase("00")) {
                valueStr = valueStr.substring(0, valueStr.length() - 2);
            }

            String isExpense = (String)transactionTable.getValueAt(i, 5);
            double currentValue = Double.valueOf(valueStr);

            // Convert to negative value if expense
            if(isExpense.equalsIgnoreCase("Expense")) {
                currentValue = -currentValue;
            }
            balance += currentValue;
        }
        updateBalance(balance);
    }

    private void increaseBalance(double amount) {

        String balanceStr = balanceLabel.getText();

        if(balanceStr.substring(balanceStr.length() - 2).equalsIgnoreCase("00")) {
            balanceStr = balanceStr.substring(0, balanceStr.length() - 2);
        }

        double newBalance = Double.parseDouble(balanceStr) + amount;
        updateBalance(newBalance);
    }

    private void setCellsAlignment(JTable table, int alignment) {

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    // Returns array of available Accounts.
    public String[] getAccountNames() {
        int numberOfNames = accountList.getItemCount();
        String[] names = new String[numberOfNames - 1];

        for (int i = 1; i < numberOfNames; i++) {
            names[i - 1] = accountList.getItemAt(i);
        }
        return names;
    }

    // Uses JOptionPane to get user selected account
    public String getAcct(String optionMessage) {

        Object[] possibilities = getAccountNames();

        if (possibilities.length == 0) {
            possibilities = new Object[1];
            possibilities[0] = "None";
        }

        return (String) JOptionPane.showInputDialog(
                this, optionMessage,
                Main.FRAME_STRING, JOptionPane.PLAIN_MESSAGE,
                null, possibilities, possibilities[0]);
    }

    // Opens user guide panel
    public void openGuide() { GuidePanel guidePanel = new GuidePanel(this, Main.FRAME_STRING, true); }
    
    // Opens benefits calculator dialog
    public void openCalc() { CalcPanel calcPanel = new CalcPanel(this, Main.FRAME_STRING, true); }
    
    public void createAccount() {

        // Opens dialog / form to get information about new account
        AccountForm accountForm = new AccountForm(this, Main.FRAME_STRING, true);

        // Grab information
        String newAcctName = accountForm.getName();
        String newAcctAmnt = accountForm.getAmnt();
        String newAcctEmail = accountForm.getEmail();
        String newAcctDesc = accountForm.getDescription();

        if ((!newAcctName.isEmpty()) && (!newAcctAmnt.isEmpty()) && (!newAcctEmail.isEmpty()) && (!newAcctDesc.isEmpty())) {
            accountList.addItem(newAcctName);
            controller.newAccount(newAcctName, newAcctAmnt, newAcctEmail, newAcctDesc);
        }

        // create new account object / update necessary data structures
    }

    private String[] createTransaction() {

        // Open form and initialize it
        TransactionForm tForm = new TransactionForm(this, Main.FRAME_STRING, true, getAccountNames());

        String[] transactionData = new String[6];

        // Get transaction data (this will eventually be an account object)
        transactionData[0] = tForm.getAcctName();
        transactionData[1] = tForm.getDate();
        transactionData[2] = tForm.getAmnt();
        transactionData[3] = tForm.getTransactionType();
        transactionData[4] = tForm.getDescription();
        transactionData[5] = tForm.isDeposit();

        updateBalance();

        return transactionData;
    }

    private void addTableRow(String[] rowData) {

        DefaultTableModel tmpModel = (DefaultTableModel) transactionTable.getModel();
        tmpModel.addRow(rowData); // This is the method call that will add information to the table.
    }

    private void removeTableRow(int rowIndex) {
        // TODO: error checking (make sure row index is valid)
        DefaultTableModel tmpModel = (DefaultTableModel) transactionTable.getModel();
        tmpModel.removeRow(rowIndex);
    }

    // Displays all the account information for acctToView
    public void viewAcct(String acctToView) {

        Account acct = controller.getAccountInfo(acctToView);
        AcctInfoForm viewAcctDlg = new AcctInfoForm(this, Main.FRAME_STRING, true, acct);
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
        if (rowStr != null) {
            rowIndex = Integer.parseInt(rowStr) - 1;
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

                    // else parse integer and make sure it is valid
                } else {
                    rowIndex = Integer.parseInt(rowStr) - 1;
                }
            }
        }
        return rowIndex;
    }

    // Creates popup warning
    public int showWarning() {
        return JOptionPane.showOptionDialog(this, "Are you sure you want to delete this account?",
                "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    }

    public void setController(Controller controller) {
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

    class deleteAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int option = -1;
            String acctToDelete = getAcct("Please select the account you want to delete");
            if ((acctToDelete != null) && (!acctToDelete.isEmpty())) {
                option = showWarning();
                if (option == 0) {
                    accountList.removeItem(acctToDelete);
                }
            }
        }
    }

    class acctInfoAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String acctToView = getAcct("Please select the account you would like to view");
            if ((acctToView != null) && (!acctToView.isEmpty())) {
                viewAcct(acctToView);
            }
        }
    }

    class logoutAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Closes current frame and opens LoginPanel when logout button is pressed
            dispose();
            LoginPanel loginPanel = new LoginPanel();
            loginPanel.startPanel();
        }
    }

    class addTransaction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String[] newRowData = createTransaction();

            // Don't want to create a transaction if we have missing data
            for (String data : newRowData) {
                if (data == null || data.isEmpty()) {
                    return;
                }
            }
            addTableRow(newRowData);

            String balanceStr = newRowData[2];

            if(balanceStr.substring(balanceStr.length() - 2).equalsIgnoreCase("00")) {
                balanceStr = balanceStr.substring(0, balanceStr.length() - 2);
            }

            increaseBalance(Double.valueOf(balanceStr));
        }
    }

    class removeTransaction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = getTransactionIndex();

            // If index IS MIN_VALUE nothing was selected, so we should not remove a transaction
            if (index != Integer.MIN_VALUE) {
                removeTableRow(index);
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

//    class saveListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//         // Set up FileWriter
//            FileWriter writer = null;
//            try
//            {
//                writer = new FileWriter("Transactions.txt");
//
//                // Write out info for each account
//                for(int i = 0; i < getAccountNames().length; i++)
//                {
//                    // Don't want to access / write null accounts
//                    if (acctArray[i] != null) {
//
//                        // Write basic info to file.
//                        writer.write(acctArray[i].getName() + "," + acctArray[i].getBalance() + "," + acctArray[i].getEmail() + "," + acctArray[i].getDescription() + ",\n");
//                    }
//                    // Still need transactions and number of transactions
//                    // TODO: Popup message when save is complete
//                }
//            }
//            catch (IOException e1)
//            {
//                // TODO: Put messages into popup
//                System.out.println("Trouble writing file...");
//                System.out.println("Error: " + e1.getMessage());
//            }
//            finally
//            {
//                try
//                {
//                    writer.close();
//                }
//                catch(IOException e1)
//                {
//
//                }
//            }
//        }
//    }

    class guideListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // User guide routine here
        	openGuide();
        }
    }
}