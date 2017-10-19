<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcctInfoForm extends JDialog {

  public AcctInfoForm(JFrame frame, String titleString, boolean modality, Account currAccount) {

    super(frame, titleString, modality);

    String name = currAccount.getName();
    String balance = currAccount.getBalance();
    String description = currAccount.getDescription();
    String email = currAccount.getEmail();

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

  public void close() { this.dispose(); }

   class okAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      close();
    }
  }
}
=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcctInfoForm extends JDialog {

  public AcctInfoForm(JFrame frame, String titleString, boolean modality, String acctToView) {

    super(frame, titleString, modality);

    int balance = 0;
    String description = "description";
    String email = "email";

    setPreferredSize(new Dimension(300, 300));

    JLabel nameLabel  = new JLabel("Name: ");
    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    JLabel nameLabel2 = new JLabel(acctToView);
    JLabel amntLabel = new JLabel("Account Balance: ");
    amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    JLabel balanceLabel = new JLabel(Integer.toString(balance));
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

  public void close() { this.dispose(); }

   class okAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {

      close();
    }
  }
}
>>>>>>> da5b35e23044e84d99568370e53073496c52e3c5
