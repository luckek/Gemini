
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InitialScreen extends JFrame {

    private JComboBox<String> accountList;
    private JTable transactionTable;
    private JLabel balanceLabel;
    private String[] columnNames = {"Name", "Date", "Amount", "Type"};
    private Account[] acctArray = new Account[10];

    public InitialScreen(String title, String[][] data) {

        super(title);

        // Configuring frame behavior
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
        JLabel devLabel = new JLabel(Main.DEV_STRING);
        balanceLabel = new JLabel("Current Balance: ");

        JButton addBttn = new JButton("Add Account");
        JButton calcBttn = new JButton("Benefits Calculator");
        JButton acctInfoButton = new JButton("View Account Info");
        JButton deleteButton = new JButton("Delete Account");
        JButton logoutBttn = new JButton("Logout");

        accountList = new JComboBox<>(); // This should be populated by a list of all accounts
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

    void initComboBox(String[] accountNames) {
        for(String name : accountNames) {
            accountList.addItem(name);
        }
    }

    void updateBalance(double newBalance) {

        String newBalanceStr =  Double.toString(newBalance);

        // This ensures that account balances with 0 cents display properly.
        if(newBalanceStr.substring(newBalanceStr.length() - 2).equalsIgnoreCase(".0")){
            newBalanceStr += "0";
        }
        balanceLabel.setText("Current Balance: " + newBalanceStr);
    }

    private void createAccount() {

        // Opens dialog / form to get information about new account
        AccountForm accountForm = new AccountForm(this, Main.FRAME_STRING, true);

        // Grab information
        String newAcctName = accountForm.getName();
        String newAcctAmnt = accountForm.getAmnt();
        String newAcctEmail = accountForm.getEmail();
        String newAcctDesc = accountForm.getDescription();

        // Don't create account if any of the fields are empty
        if((!newAcctName.isEmpty()) && (!newAcctAmnt.isEmpty()) && (!newAcctEmail.isEmpty()) && (!newAcctDesc.isEmpty())){
            accountList.addItem(newAcctName);
            String[] names = retrieveAccountNames();
            int position = names.length - 1;
            acctArray[position] = new Account(newAcctName, newAcctAmnt, newAcctEmail, newAcctDesc);
            // create new account object / update necessary data structures
        }
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
    private String[] retrieveAccountNames() {

        int numberOfNames = accountList.getItemCount();
        String[] names = new String[numberOfNames - 1];

        for(int i = 1; i < numberOfNames; i++) {
            names[i - 1] = accountList.getItemAt(i);
        }
        return names;
    }

    // Creates popup warning
    private int showWarning() {
        return JOptionPane.showOptionDialog(this, "Are you sure you want to delete this account?",
                                            "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                            null, null, null);
    }

    private String getAcct(String optionMessage) {

        Object[] possibilities = retrieveAccountNames();

        if(possibilities.length == 0) {
            possibilities = new Object[1];
            possibilities[0] = "None";
        }

        return (String)JOptionPane.showInputDialog(this, optionMessage, Main.FRAME_STRING,
                JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
    }

    private void viewAcct(String acctToView) {

        Account account = new Account("", "0", "", "This should not be here");

        for(Account currAcct : acctArray) {
            if((currAcct != null) && (currAcct.getName().equalsIgnoreCase(acctToView))){
                account.setName(currAcct.getName());
                account.setBalance(currAcct.getBalance());
                account.setEmail(currAcct.getEmail());
                account.setDesc(currAcct.getDescription());
            }
        }
        AcctInfoForm viewAcctDlg = new AcctInfoForm(this, Main.FRAME_STRING, true, account);
    }

    class addAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            createAccount();
        }
    }

    class calcAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {}
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
            LoginPanel main = new LoginPanel();
            main.startPanel();
        }
    }
}
