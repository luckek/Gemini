import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

  private static String VERSION = "0.0.1";
  private static String SOFTWARE_NAME = "Astro Account Management Software";
  private static String COMPANY_NAME = "Gemini Corp.";
  private static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
  private static String DEV_STRING = "Developed By: " + COMPANY_NAME;
  private static InitialScreen initialScreen;
  private static JFrame frame;

  public static void main (String[] args) {

    frame = new JFrame(FRAME_STRING);
    frame.setVisible(true);
    frame.setPreferredSize(new Dimension(600, 400));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();

    JButton button = new JButton("Login");
    frame.add(button);
    button.addActionListener (new loginAction());
  }

  public void initializeScreen() {}

  static class loginAction implements ActionListener {
    public void actionPerformed (ActionEvent e) {

      boolean isSucessful = true;


      if(isSucessful == true) { // Eveything inside this if statement must be copied together in order to work properly.

        String[] accountNames = {"All", "Michael", "Trish", "Rob"}; // This should be replaced with all acct names
        double accountBalance = 0.00; // This may stay hardcoded for now.

        // This should be populated by all of the transaction data.
        String data [][] = {};

        frame.dispose(); // This should be replaced with Jared's frame.

        initialScreen = new InitialScreen(FRAME_STRING, data);

        initialScreen.initComboBox(accountNames);
        initialScreen.updateBalance(accountBalance);

      } else {
      }
    }
  }
}
