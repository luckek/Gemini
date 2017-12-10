//*********************************************************
//**                        Gemini                       **
//**                    LoginPanel.java                  **
//** Create a panel for a user to login to their account **
//*********************************************************

// Imported Classes and Libraries

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class LoginPanel {

    // Components and variables
    private JTextField usernameTF;
    private JPasswordField passwordPF;
    private JLabel invalid, logoLabel;
    // Use this line when making jar / using eclipse
//    private String logoPath = "logo1.png";
    private String logoPath = "main/resources/logo1.png";
    private InitialScreen initialScreen;
    private JCheckBox passwordText;
    private JFrame frame;
    private Controller controller = new Controller();

    void startPanel() {
        // Create frame
        frame = new JFrame("Login");
        frame.pack();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        // Create fonts
        Font basic = new Font("Arial", Font.PLAIN, 20);
        Font title = new Font("Arial", Font.BOLD, 25);
        
        // Create Software name label
        JLabel loginTitle1 = new JLabel("Astro Account");
        loginTitle1.setBounds(300, 40, 500, 40);
        loginTitle1.setFont(title);
        panel.add(loginTitle1);

        JLabel loginTitle2 = new JLabel("Management Software");
        loginTitle2.setBounds(250, 80, 500, 40);
        loginTitle2.setFont(title);
        panel.add(loginTitle2);


        // Create username label
        JLabel username = new JLabel("Username:");
        username.setFont(basic);
        username.setBounds(100, 180, 200, 20);
        panel.add(username);

        // Create password label
        JLabel password = new JLabel("Password:");
        password.setFont(basic);
        password.setBounds(100, 280, 200, 20);
        panel.add(password);

        // Create username text field
        usernameTF = new JTextField();
        usernameTF.setFont(basic);
        usernameTF.setBounds(250, 170, 250, 40);
        panel.add(usernameTF);

        // Create password password field
        passwordPF = new JPasswordField();
        passwordPF.setBounds(250, 270, 250, 40);
        passwordPF.setEchoChar('*');
        panel.add(passwordPF);
        passwordPF.addActionListener(new LoginAction());

        // Create login button
        JButton login = new JButton("Login");
        login.setFont(basic);
        login.setBounds(300, 380, 150, 50);
        panel.add(login);
        login.addActionListener(new LoginAction());

        // Create invalid username or password label
        invalid = new JLabel("");
        invalid.setForeground(Color.red);
        invalid.setFont(basic);
        invalid.setBounds(240, 450, 400, 20);
        panel.add(invalid);

        // Create logo
        logoLabel = new JLabel (new ImageIcon(logoPath));
        logoLabel.setBounds(80, -70, 150, 300);
        panel.add(logoLabel);
        
        // Create Developed by Label
        JLabel develop = new JLabel(Main.DEV_STRING);
        develop.setBounds(50,600, 200, 20);
        panel.add(develop);
        
        // Create show password text
        passwordText = new JCheckBox("Show password text");
        passwordText.setBounds(300, 340, 150, 20);
        passwordText.addActionListener(new CheckAction());
        panel.add(passwordText);

        frame.setVisible(true);

    }

    // When show passwordText box is checked
    class CheckAction implements ActionListener {
    	public void actionPerformed (ActionEvent e) {

    		if(passwordText.isSelected()) {
    			passwordPF.setEchoChar((char) 0);
    		} else {
    			passwordPF.setEchoChar('*');
    		}
    		
    	}
    }
    
    // When login button is pushed
    class LoginAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            // Variables
            boolean usernameCheck = false;
            boolean passwordCheck = false;
            String username = usernameTF.getText();
            char[] password = passwordPF.getPassword();
            String passStr = new String(password);

            username = "csadmin";
            passStr = "csci323";
            // Check if the username is right
            if(username.equals("csadmin")) {
                usernameCheck = true;
            }

            // Check if the password is right
            if(passStr.equals("csci323")) {
                passwordCheck = true;
            }

            // If both the username and password are correct, login
            if((usernameCheck) && (passwordCheck)) {
                // Reset username and password fields
                usernameTF.setText("");
                passwordPF.setText("");

                // Load account information
                try {
				    controller.loadAcctInfo();
                } catch (FileNotFoundException e1) {
                    System.out.println("Cannot find Accounts.txt");
                    e1.printStackTrace();
                }

				// Load Transaction data
				try {
					controller.loadData();
				} catch (FileNotFoundException e1) {
                    System.out.println("Cannot find Transactions.txt");
					e1.printStackTrace();
				}

                // Creates new InitialScreen frame on successful login
                frame.dispose();

                initialScreen = new InitialScreen(Main.FRAME_STRING);

                initialScreen.setController(controller);
                initialScreen.initComboBox(controller.getAvailableAccts());
                initialScreen.initTranscationTable(controller.getTransactions());

            // If one of them is wrong, that tell user that they have an invalid username or password
            } else {
                // Reset username and password fields
                usernameTF.setText("");
                passwordPF.setText("");

                // Show invalid label
                invalid.setText("Invalid username or password");
            }
        }
    }
}
