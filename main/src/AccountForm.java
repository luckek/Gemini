import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountForm extends JDialog {


    private JTextField nameField;
    private JTextField amntField;
    private JTextField emailField;
    private JTextField descriptionField;

    public AccountForm(Frame frame, String title, boolean modality) {


        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 300));

        // Creating components
        JLabel nameLabel  = new JLabel("Name: ");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameField = new JTextField(10);
        JLabel amntLabel = new JLabel("Initial amount: ");
        amntLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        amntField = new JTextField(10);
        JLabel emailLabel = new JLabel("Email: ");
        emailField = new JTextField(10);
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionField = new JTextField(10);
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new okAction());

        JPanel mainPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel amntPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel emailPanel = new JPanel();
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
        centerPanel.add(amntPanel);
        centerPanel.add(emailPanel);
        centerPanel.add(descriptionPanel);

        infoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        infoPanel.add(new JLabel("<html><center><p>Please enter the name of the " +
        "account</p><p>and all other pertinent information" +
        "</p></center></html>"));

        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(39, 10)));
        namePanel.add(nameField);

        amntPanel.add(amntLabel);
        amntPanel.add(amntField);

        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(39, 10)));
        emailPanel.add(emailField);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        descriptionPanel.add(descriptionField);

        pack();
        setVisible(true);
        }

    // Some getters that allow retrival of entered information
    public String getName() { return nameField.getText(); }
    public String getAmnt() { return amntField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getDescription() { return descriptionField.getText(); }

    // Closes dialog
    private void close() { this.dispose(); }

    // Creaes popup warning
    private void showWarning() {

        JOptionPane.showMessageDialog(this, "Please fill out all fields",
                                      "Warning!", JOptionPane.WARNING_MESSAGE);
    }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            if(nameField.getText().isEmpty() || amntField.getText().isEmpty() ||
               emailField.getText().isEmpty() || descriptionField.getText().isEmpty() ){

                showWarning();
                return;
            }
            close();
        }
    }
}
