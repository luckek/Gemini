import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountForm extends JDialog {

  private JTextField nameField;
  private JTextField amntField;

  public AccountForm(Frame frame, String title, boolean modality) {

    super(frame, title, modality);

    setPreferredSize(new Dimension(300, 300));

    JLabel nameLabel  = new JLabel("Name: ");
    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    nameField = new JTextField(10);
    JLabel amntLabel = new JLabel("Initial amount: ");
    amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    amntField = new JTextField(10);
    JButton okButton = new JButton("Ok");

    okButton.addActionListener(new okAction());

    JPanel mainPanel = new JPanel();
    JPanel namePanel = new JPanel();
    JPanel amntPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JPanel centerPanel = new JPanel();

    centerPanel.setLayout(new GridLayout(3, 0, 90, 0));
    mainPanel.setPreferredSize(new Dimension(500, 500));
    mainPanel.setLayout(new BorderLayout());

    // make two panels, one for name and one for amout. add these to third panel? put third panel in center of main panel?

    add(mainPanel);

    mainPanel.add(okButton, BorderLayout.PAGE_END);
    mainPanel.add(centerPanel,BorderLayout.CENTER);

    centerPanel.add(infoPanel);
    centerPanel.add(namePanel);
    centerPanel.add(amntPanel);

    infoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
    infoPanel.add(new JLabel("<html><center><p>Please enter the name of the " +
    "account</p><p>and the initial amount of money in the account" +
    "</p></center></html>"));

    namePanel.add(nameLabel);
    namePanel.add(Box.createRigidArea(new Dimension(39, 10)));
    namePanel.add(nameField);

    amntPanel.add(amntLabel);
    amntPanel.add(amntField);

    pack();
    setVisible(true);
  }

  public String getName() { return nameField.getText(); }

  public String getAmnt() { return amntField.getText(); }

  public void close() { this.dispose(); }

   class okAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      System.out.println("okay button pressed");
      if(nameField.getText().isEmpty() || amntField.getText().isEmpty()){

        // warning
      }
      close();
    }
  }
}
