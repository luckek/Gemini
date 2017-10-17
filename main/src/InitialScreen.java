import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InitialScreen extends JFrame {

  private static String VERSION = "0.0.1";
  private static String SOFTWARE_NAME = "Software name";
  private static String COMPANY_NAME = "Gemini Corp.";
  private static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
  private static String DEV_STRING = "Developed By: " + COMPANY_NAME;

  private static JComboBox<String> accountList;
  private static JTable transactionTable;
  private static JLabel balanceLabel;
  private static String[] columnNames = {"Name", "Date", "Amount", "Type"};

  public InitialScreen(String title, String[][] data) {

    super(title);

    // Configuring frame behavior
    setVisible(true);
    setSize(700,700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Inistaniating components
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new GridLayout(12, 0, 20, 35));
    JPanel leftPanel = new JPanel();
    JPanel fillerPanel = new JPanel();
    JPanel transactionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel outterPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel outterBalancePanel = new JPanel();

    JLabel acctLabel = new JLabel("Account: ");
    JLabel transactionLabel = new JLabel("Transactions: ");
    JLabel devLabel = new JLabel(DEV_STRING);
    balanceLabel = new JLabel("Current Balance: ");

    JButton addBttn = new JButton("Add Account");
    JButton calcBttn = new JButton("Benefits Calculator");
    JButton acctInfoButton = new JButton("View Account Info");
    JButton deleteButton = new JButton("Delete Account");
    JButton logoutBttn = new JButton("Logout");

    accountList = new JComboBox<>(); // This shold be populated by a list of all accounts
    transactionTable = new JTable(data, columnNames); // Could not be avoided...

    // More components
    JScrollPane transactionPane = new JScrollPane(transactionTable);
    transactionTable.setFillsViewportHeight(true);

    // Layouts and sizing
    transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS));
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.LINE_AXIS));
    outterBalancePanel.setLayout(new BoxLayout(outterBalancePanel, BoxLayout.PAGE_AXIS));

    leftPanel.setMaximumSize(new Dimension(700, 200));
    transactionPane.setMaximumSize(new Dimension(400, 500));
    headerPanel.setPreferredSize(new Dimension(300, 25));
    outterBalancePanel.setPreferredSize(new Dimension(100, 100));

    // Populating panels / content pane
    add(mainPanel);

    mainPanel.add(leftPanel, BorderLayout.LINE_START);
    mainPanel.add(outterPanel, BorderLayout.CENTER);
    mainPanel.add(devLabel, BorderLayout.PAGE_END);

    leftPanel.add(Box.createRigidArea(new Dimension(50, 200)));
    leftPanel.add(buttonPanel);
    leftPanel.add(Box.createVerticalBox());

    buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    buttonPanel.add(acctLabel);
    buttonPanel.add(accountList);
    buttonPanel.add(addBttn);
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
    transactionPanel.add(transactionPane);
    // transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    // transactionPanel.add(balanceLabel);

    headerPanel.add(transactionLabel);

    outterBalancePanel.add(balancePanel);
    outterBalancePanel.add(Box.createRigidArea(new Dimension(0, 25)));

    balancePanel.add(balanceLabel);

    addBttn.addActionListener(new addAction());
    calcBttn.addActionListener(new calcAction());
    deleteButton.addActionListener(new deleteAction());
    acctInfoButton.addActionListener(new acctInfoAction());
    logoutBttn.addActionListener(new logoutAction());
  }

  public void initComboBox(String[] accountNames) {
    for(int i = 0; i < accountNames.length; i++) {
      accountList.addItem(accountNames[i]);
    }
  }

  public void updateBalance(double newBalance) {

    String newBalanceStr =  Double.toString(newBalance);

    // This ensures that account balances with 0 cents display properly.
    if(newBalanceStr.substring(newBalanceStr.length() - 2).equalsIgnoreCase(".0")){
      newBalanceStr += "0";
    }
    balanceLabel.setText("Current Balance: " + newBalanceStr);
  }

  public void createAccount() {

    // Opens dialog / form to get information about new account
    AccountForm accountForm = new AccountForm(this, FRAME_STRING, true);

    // Grab information
    String newAccountName = accountForm.getName();
    String newAccountAmnt = accountForm.getAmnt();

    accountList.addItem(newAccountName);
    // create new account object / update necessary data structures
  }

  // Currently not used (table will not display)
  public void initTranscationTable(String[][] data, String[] columnNames) {

    transactionTable = new JTable(data, columnNames);
    this.invalidate();
    this.repaint();
    this.validate();
    setVisible(true);

  }

  // Returns list of available Accounts.
  public String[] retrieveAccountNames() {
    int numberOfNames = accountList.getItemCount();
    String[] names = new String[numberOfNames - 1];

    for(int i = 1; i < numberOfNames; i++) {
      names[i - 1] = accountList.getItemAt(i);
    }
    return names;
  }

  public String getAcct() {

    Object[] possibilities = retrieveAccountNames();


    String acctName = (String)JOptionPane.showInputDialog(
                    this, "Please select an account to view:",
                    FRAME_STRING, JOptionPane.PLAIN_MESSAGE,
                    null, possibilities, possibilities[0]);

      System.out.println(acctName);
      return acctName;

  }

  public void viewAcct(String acctToView) {
    AcctInfoForm viewAcctDlg = new AcctInfoForm(this, FRAME_STRING, true, acctToView);
  }

  class addAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
    createAccount();
    }
  }

  static class calcAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      System.out.println("Benefits calculator button pressed");
    }
  }

  static class deleteAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      System.out.println("Delete accounts button pressed");
      // Delete account - need warning

      String acctName = "Michael";
      accountList.removeItem(acctName);
    }
  }

  class acctInfoAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {

    String acctToView = getAcct();
    viewAcct(acctToView);
    }
  }

  static class logoutAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      System.out.println("logout button pressed");
      // this.dispose();
    }
  }
}
