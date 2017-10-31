import com.intellij.ui.components.panels.FlowLayoutWrapper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// TODO: make sure exiting w/all fields filled out does NOT make a new acct / transaction
// TODO: Update balance label upon filtering transactions

public class InitialScreen extends JFrame {

  private static String VERSION = "0.0.1";
  private static String SOFTWARE_NAME = "Astro Account Management Software";
  private static String COMPANY_NAME = "Gemini Corp.";
  private static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
  private static String DEV_STRING = "Developed By: " + COMPANY_NAME;

  private JComboBox<String> accountList;
  private static JTable transactionTable;
  private static JLabel balanceLabel;
  private ButtonGroup radioGroup;
  private static String[] columnNames = {"Name", "Date", "Gross Amt", "Type", "Code", "Dr / Cr",  "Net Amt"};
  private Account[] acctArray = new Account[10];

  public InitialScreen(String title) {

    super(title);

    // Configuring frame behavior
    setVisible(true);
    setSize(1000,700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    JLabel devLabel = new JLabel(DEV_STRING);
    balanceLabel = new JLabel("Current Balance: ");

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

    bothButton.setActionCommand("Both");
    creditButton.setActionCommand("Credit");
    debitButton.setActionCommand("Debit");

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

  }

  public void initComboBox(String[] accountNames) {
    for(int i = 0; i < accountNames.length; i++) {
      accountList.addItem(accountNames[i]);
    }
  }

  void initTranscationTable(String[][] data) {

    for(String[] row : data) {
      addTableRow(row);
    }

    setCellsAlignment(transactionTable, SwingConstants.CENTER);
  }

  public void updateBalance(double newBalance) {
    String newBalanceStr =  Double.toString(newBalance);

    // This ensures that account balances with 0 cents display properly.
    if(newBalanceStr.substring(newBalanceStr.length() - 2).equalsIgnoreCase(".0")){
      newBalanceStr += "0";
    }
    balanceLabel.setText("Current Balance: " + newBalanceStr);
  }

  private void setCellsAlignment(JTable table, int alignment) {

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(alignment);

    TableModel tableModel = table.getModel();

    for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
    {
      table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
    }
  }

  // Returns array of available Accounts.
  public String[] getAccountNames() {
    int numberOfNames = accountList.getItemCount();
    String[] names = new String[numberOfNames - 1];

    for(int i = 1; i < numberOfNames; i++) {
      names[i - 1] = accountList.getItemAt(i);
    }
    return names;
  }

  // Uses JOptionPane to get user selected account
  public String getAcct(String optionMessage) {

    Object[] possibilities = getAccountNames();

    if(possibilities.length == 0) {
      possibilities = new Object[1];
      possibilities[0] = "None";
    }

    return (String)JOptionPane.showInputDialog(
            this, optionMessage,
            FRAME_STRING, JOptionPane.PLAIN_MESSAGE,
            null, possibilities, possibilities[0]);
  }

  public void createAccount() {

    // Opens dialog / form to get information about new account
    AccountForm accountForm = new AccountForm(this, FRAME_STRING, true);

    // Grab information
    String newAcctName = accountForm.getName();
    String newAcctAmnt = accountForm.getAmnt();
    String newAcctEmail = accountForm.getEmail();
    String newAcctDesc = accountForm.getDescription();

    if((!newAcctName.isEmpty()) && (!newAcctAmnt.isEmpty()) && (!newAcctEmail.isEmpty()) && (!newAcctDesc.isEmpty())){
      accountList.addItem(newAcctName);

      String[] names = getAccountNames();
      int position = names.length - 1;
      acctArray[position] = new Account(newAcctName, newAcctAmnt, newAcctEmail, newAcctDesc);
    }

    // create new account object / update necessary data structures
  }

  private String[] createTransaction() {

    // Open form and initialize it
    TransactionForm tForm = new TransactionForm(this, FRAME_STRING, true, getAccountNames());

    String[] transactionData = new String[6];

    // Get transaction data (this will eventually be an account object)
    transactionData[0] = tForm.getAcctName();
    transactionData[1] = tForm.getDate();
    transactionData[2] = tForm.getAmnt();
    transactionData[3] = tForm.getTransactionType();
    transactionData[4] = tForm.getDescription();
    transactionData[5] = tForm.getIsDebit();

    // TODO: Update / calculate balance

    return transactionData;
  }

  private void addTableRow(String[] rowData) {



    DefaultTableModel tmpModel = (DefaultTableModel)transactionTable.getModel();
    tmpModel.addRow(rowData); // This is the method call that will add information to the table.
  }

  private void removeTableRow(int rowIndex) {
    // TODO: error checking (make sure row index is valid)
    DefaultTableModel tmpModel = (DefaultTableModel)transactionTable.getModel();
    tmpModel.removeRow(rowIndex);
  }

  // Displays all the account information for acctToView
  public void viewAcct(String acctToView) {

    Account currAccount = new Account("", "0", "", "This should not be here");
    for(int i = 0; i < acctArray.length; i++) {

      if((acctArray[i] != null) && (acctArray[i].getName().equalsIgnoreCase(acctToView))){
        currAccount.setName(acctArray[i].getName());
        currAccount.setBalance(acctArray[i].getBalance());
        currAccount.setEmail(acctArray[i].getEmail());
        currAccount.setDesc(acctArray[i].getDescription());
      }
    }
    AcctInfoForm viewAcctDlg = new AcctInfoForm(this, FRAME_STRING, true, currAccount);
  }

  private int getTransactionIndex() {

    DefaultTableModel tmpModel = (DefaultTableModel)transactionTable.getModel();
    int rowIndex = Integer.MIN_VALUE;

    if(tmpModel.getRowCount() == 0) {

      JOptionPane.showMessageDialog(this, "There are no transactions to remove!",
              "Warning!", JOptionPane.WARNING_MESSAGE);

      return rowIndex;
    }

    // Get row index from user
    String rowStr = JOptionPane.showInputDialog(this, "Please enter the row index of the transaction to be removed");

    // If user enters something, parse the index
    if(rowStr != null) {
      rowIndex = Integer.parseInt(rowStr) - 1;
    }

    // If user actually entered something
    if(rowIndex != Integer.MIN_VALUE) {
      while ( rowIndex < 0 || rowIndex > tmpModel.getRowCount()) {

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

  class addAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
    createAccount();
    }
  }

  static class calcAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
    }
  }

 class deleteAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      int option = -1;
      String acctToDelete = getAcct("Please select the account you want to delete");
      if((acctToDelete != null) && (!acctToDelete.isEmpty())){
        option = showWarning();
        if(option == 0) {
          accountList.removeItem(acctToDelete);
        }
      }
    }
  }

  class acctInfoAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {

      String acctToView = getAcct("Please select the account you would like to view");
      if((acctToView != null) && (!acctToView.isEmpty())) {
        viewAcct(acctToView);
      }
    }
  }

  class logoutAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      // Closes current frame and opens LoginPanel when logout button is pressed
      dispose();
      LoginPanel.startPanel();
    }
  }

  class addTransaction implements ActionListener {
    public void actionPerformed(ActionEvent e) {

      String[] newRowData = createTransaction();

      // Don't want to create a transaction if we have missing data
      for(String data : newRowData) {
        if(data == null || data.isEmpty()) {
          return;
        }
      }
      addTableRow(newRowData);
    }
  }

  class removeTransaction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int index = getTransactionIndex();

      // If index IS MIN_VALUE nothing was selected, so we should not remove a transaction
      if(index != Integer.MIN_VALUE) {
        removeTableRow(index);
      }
    }
  }

  class accountListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      String filterString = (String)accountList.getSelectedItem();
      String filterString2 = radioGroup.getSelection().getActionCommand();

      TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(((DefaultTableModel) transactionTable.getModel()));

      RowFilter nameFilter = null;
      RowFilter typeFilter = null;

      ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>(2);

      if(filterString != null && !filterString.equalsIgnoreCase("All")) {

        nameFilter = RowFilter.regexFilter(filterString);
      }

      if(filterString2 != null && !filterString2.equalsIgnoreCase("Both")) {

        typeFilter = RowFilter.regexFilter(filterString2);
      }

      if(nameFilter == null && typeFilter == null) {

        transactionTable.setRowSorter(null);

      } else if (nameFilter == null) {

        typeFilter = RowFilter.regexFilter(filterString2);
        sorter.setRowFilter(typeFilter);

        transactionTable.setRowSorter(sorter);

      } else if(typeFilter == null) {

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
    }
  }
}
