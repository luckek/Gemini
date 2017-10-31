//*********************************************************
//**                        Gemini                       **
//**                    LoginPanel.java                  **
//** Create a panel for a user to login to their account **
//*********************************************************

// Imported Classes and Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel
{

    // Components and variables
    private JTextField usernameTF;
    private JPasswordField passwordPF;
    private JLabel invalid;
    private InitialScreen initialScreen;
    private JFrame frame;

    void startPanel()
    {
        // Create frame
        frame = new JFrame("Login");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // Create Software name label
        JLabel loginTitle1 = new JLabel("Astro Account");
        loginTitle1.setBounds(300, 40, 500, 40);
        loginTitle1.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(loginTitle1);

        JLabel loginTitle2 = new JLabel("Management Software");
        loginTitle2.setBounds(250, 80, 500, 40);
        loginTitle2.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(loginTitle2);


        // Create username label
        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setBounds(100, 180, 200, 20);
        panel.add(username);

        // Create password label
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setBounds(100, 280, 200, 20);
        panel.add(password);

        // Create username text field
        usernameTF = new JTextField();
        usernameTF.setBounds(250, 170, 250, 40);
        panel.add(usernameTF);

        // Create password password field
        passwordPF = new JPasswordField();
        passwordPF.setBounds(250, 270, 250, 40);
        panel.add(passwordPF);
        passwordPF.addActionListener(new LoginAction()); // Why is this needed?

        // Create login button
        JButton login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setBounds(300, 380, 150, 50);
        panel.add(login);
        login.addActionListener(new LoginAction());

        // Create invalid username or password label
        invalid = new JLabel("");
        invalid.setForeground(Color.red);
        invalid.setFont(new Font("Arial", Font.PLAIN, 20));
        invalid.setBounds(240, 330, 400, 20);
        panel.add(invalid);

        // Create Developed by Label
        JLabel develop = new JLabel(Main.DEV_STRING);
        develop.setBounds(50,600, 200, 20);
        panel.add(develop);

        // Should fix content update issue
        frame.revalidate();
        frame.repaint();

    }

    // When login button is pushed
    class LoginAction implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            // Variables
            boolean usernameCheck;
            boolean passwordCheck;
            String username = usernameTF.getText();
            char[] password = passwordPF.getPassword(); // getText has been depreciated
            String passStr = new String(password); // convert char[] to string

            // Check if the username is right
            if(username.equals("csadmin"))
            {
                usernameCheck = true;
            }
            else
            {
                usernameCheck = false;
            }

            // Check if the password is right
            if(passStr.equals("csci323"))
            {
                passwordCheck = true;
            }
            else
            {
                passwordCheck = false;
            }

            // If both the username and password are correct, login
            if((usernameCheck == true) && (passwordCheck == true))
            {
                // Reset username and password fields
                usernameTF.setText("");
                passwordPF.setText("");

                String[] accountNames = {"All"}; // This should be replaced with all acct names
                double accountBalance = 0.00; // This may stay hardcoded for now.

                // This should be populated by all of the transaction data.
                String data [][] = {};

                // Creates new InitialScreen frame on successful login
                frame.dispose();

                initialScreen = new InitialScreen(FRAME_STRING);

                initialScreen.initComboBox(accountNames);
                initialScreen.updateBalance(accountBalance);
                initialScreen.initTranscationTable(data);

            }
            // If one of them is wrong, that tell user that they have an invalid username or password
            else
            {
                // Reset username and password fields
                usernameTF.setText("");
                passwordPF.setText("");

                // Show invalid label
                invalid.setText("Invalid username or password");
            }
        }
    }
}
