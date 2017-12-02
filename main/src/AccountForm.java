import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountForm extends JDialog {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField descriptionField;
    private JTextField numberField;

    public AccountForm(Frame frame, String title, boolean modality) {


        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 350));

        // Creating components
        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameField = new JTextField(10);
        JLabel emailLabel = new JLabel("Email: ");
        emailField = new JTextField(10);
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionField = new JTextField(10);
        JLabel numberLabel = new JLabel("Number: ");
        numberField = new JTextField(10);
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new okAction());

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel numberPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        // Setting layouts
        centerPanel.setLayout(new GridLayout(5, 0, 90, 0));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setLayout(new BorderLayout());

        // Adding components
        add(mainPanel);

        mainPanel.add(okButton, BorderLayout.PAGE_END);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        centerPanel.add(infoPanel);
        centerPanel.add(namePanel);
        centerPanel.add(emailPanel);
        centerPanel.add(descriptionPanel);
        centerPanel.add(numberPanel);

        infoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        infoPanel.add(new JLabel("<html><center><p>Please enter the name of the " +
        "account</p><p>and all other pertinent information" +
        "</p></center></html>"));

        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(39, 0)));
        namePanel.add(nameField);

        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(39, 0)));
        emailPanel.add(emailField);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        descriptionPanel.add(descriptionField);

        numberPanel.add(numberLabel);
        numberPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        numberPanel.add(numberField);

        pack();
        setVisible(true);
        }

    // Some getters that allow retrieval of entered information
    public String getName() { return nameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getDescription() { return descriptionField.getText(); }
    public String getPhoneNumber() { return numberField.getText(); }

    // Closes dialog
    private void close() { this.dispose(); }

    // Creates popup warning
    private void showWarning() {

        JOptionPane.showMessageDialog(this, "Please fill out all fields",
                                      "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            if(nameField.getText().isEmpty() || emailField.getText().isEmpty() || descriptionField.getText().isEmpty() ){

                showWarning();
                return;
            }
            close();
        }
    }
}
