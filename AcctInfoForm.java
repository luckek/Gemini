import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcctInfoForm extends JDialog {


    public AcctInfoForm(JFrame frame, String titleString, boolean modality, Controller currAccount) {


        super(frame, titleString, modality);

        String [] info = new String[3];
        info = currAccount.getAccountInfo();
        String name = info[0];
        String balance = info[1];
        String description = info[2];
        String email = info[3];
        setPreferredSize(new Dimension(300, 300));

        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel nameLabel2 = new JLabel(name);
        JLabel amntLabel = new JLabel("Account Balance: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel balanceLabel = new JLabel(balance);
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel descriptionLabel2 = new JLabel(description);
        JLabel emailLabel = new JLabel("E-mail: ");
        JLabel emailLabel2 = new JLabel(email);
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new okAction());

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(4, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel);

        mainPanel.add(okButton, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        centerPanel.add(namePanel);
        centerPanel.add(balancePanel);
        centerPanel.add(descriptionPanel);
        centerPanel.add(emailPanel);

        namePanel.add(nameLabel);
        namePanel.add(nameLabel2);

        balancePanel.add(amntLabel);
        balancePanel.add(balanceLabel);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionLabel2);

        emailPanel.add(emailLabel);
        emailPanel.add(emailLabel2);

        pack();
        setVisible(true);
    }

    private void close() { this.dispose(); }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) { close(); }
    }
}
