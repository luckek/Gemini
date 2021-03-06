
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcctInfoForm extends JDialog {

    public AcctInfoForm(JFrame frame, String titleString, boolean modality, Account currAccount) {


        super(frame, titleString, modality);

        String name = currAccount.getName();
        String balance = Double.toString(currAccount.getBalance());
        String description = currAccount.getDescription();
        String email = currAccount.getEmail();
        String number = currAccount.getPhoneNumber();
        boolean isRetired = currAccount.isRetired();
        setPreferredSize(new Dimension(300, 350));

        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel nameLabel2 = new JLabel(name);
        JLabel emailLabel = new JLabel("E-mail: ");
        JLabel emailLabel2 = new JLabel(email);
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel descriptionLabel2 = new JLabel(description);
        JLabel numberLabel = new JLabel("Number: ");
        JLabel numberLabel2 = new JLabel(number);
        JLabel amntLabel = new JLabel("Account Balance: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel balanceLabel = new JLabel(balance);
        JLabel retiredLabel = new JLabel();

        JButton okButton = new JButton("Ok");

        if(isRetired) {
            retiredLabel.setText("This account is retired");
        }

        okButton.addActionListener(new okAction());
        getRootPane().setDefaultButton(okButton);

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel reitredPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(6, 0, 90, 0));
        mainPanel.setLayout(new BorderLayout());
        okButton.setPreferredSize(new Dimension(50, 25));

        add(mainPanel);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        bottomPanel.add(okButton);

        centerPanel.add(namePanel);
        centerPanel.add(balancePanel);
        centerPanel.add(descriptionPanel);
        centerPanel.add(emailPanel);
        centerPanel.add(numberPanel);
        centerPanel.add(reitredPanel);

        namePanel.add(nameLabel);
        namePanel.add(nameLabel2);

        balancePanel.add(amntLabel);
        balancePanel.add(balanceLabel);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionLabel2);

        emailPanel.add(emailLabel);
        emailPanel.add(emailLabel2);

        numberPanel.add(numberLabel);
        numberPanel.add(numberLabel2);

        reitredPanel.add(retiredLabel);

        pack();
        setVisible(true);
    }

    private void close() { this.dispose(); }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) { close(); }
    }
}
