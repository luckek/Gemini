import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAccountDialog  extends JDialog {

    private JTextField emailField;
    private JTextField descField;
    private JTextField numberField;
    private int okPressed = -1;

    public EditAccountDialog(Frame frame, String title, boolean modality, Account account) {

        super(frame, title, modality);

        setPreferredSize(new Dimension(300, 275));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(4, 0 ));
        JPanel titlePanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel descPanel = new JPanel();
        JPanel numberPanel = new JPanel();
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("<html><center><p>Fill out a field to change the information</p><p> associated with "
                                + account.getName() +"'s account</p></center></html>");


        JLabel emailLabel = new JLabel("Email:");
        JLabel descLabel = new JLabel("Description");
        JLabel numberLabel = new JLabel("Number:");

        emailField = new JTextField(10);
        descField = new JTextField(10);
        numberField = new JTextField(10);

        JButton okButton = new JButton("Ok");

        add(mainPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        centerPanel.add(titlePanel);
        centerPanel.add(emailPanel);
        centerPanel.add(descPanel);
        centerPanel.add(numberPanel);

        titlePanel.add(titleLabel);

        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        emailPanel.add(emailField);

        descPanel.add(descLabel);
        descPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        descPanel.add(descField);

        numberPanel.add(numberLabel);
        numberPanel.add(Box.createRigidArea(new Dimension(22, 0)));
        numberPanel.add(numberField);

        bottomPanel.add(okButton);

        okButton.addActionListener(new okAction());
        getRootPane().setDefaultButton(okButton);

        pack();
        setVisible(true);
    }

    public String getEmail() { return emailField.getText(); }
    public String getDesc() { return descField.getText(); }
    public String getNumber() { return numberField.getText(); }
    public int getOkPressed() { return okPressed; }

    private void close() { this.dispose(); }

    class okAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            close();
            okPressed = 1;
        }
    }
}
