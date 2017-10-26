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
    private static JTextField usernameTF;
    private static JPasswordField passwordPF;
    private static JLabel invalid;
    
    public static void main(String[] args)
    {
        // Create frame
        JFrame frame = new JFrame("Login");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        passwordPF.addActionListener(new LoginAction());
        
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
        JLabel develop = new JLabel("Devloped by Gemini Corp.");
        develop.setBounds(50,600, 200, 20);
        panel.add(develop);
        
    }
    
    // When login button is pushed
    static class LoginAction implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            // Variables
            boolean usernameCheck;
            boolean passwordCheck;
            String username = usernameTF.getText();
            String password = passwordPF.getText();
            
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
            if(password.equals("csci323"))
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
                
                // Create new frame (edit this with Kyle's main panel)
                JFrame frame = new JFrame();
                frame.pack();
                frame.setVisible(true);
                frame.setSize(700, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //MainPanel panel = new MainPanel();
                //frame.getContentPane().add(panel);
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