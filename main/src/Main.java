import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

  private static String VERSION = "0.0.1";
  private static String SOFTWARE_NAME = "Software name";
  private static String COMPANY_NAME = "Gemini Corp.";
  private static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
  private static String DEV_STRING = "Developed By: " + COMPANY_NAME;
  // private static JComboBox<String> accountList;
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
    button.addActionListener (new Action1());
  }

  public void initializeScreen() {}

  static class Action1 implements ActionListener {
    public void actionPerformed (ActionEvent e) {

      String[] accountNames = {"All", "Michael", "Trish", "Rob"};
      double accountBalance = 4200.00;
      boolean isSucessful = true; // Assume login works (For now)


      if(isSucessful == true) {

        // This should be populated by all of the transaction data.
        String data [][] = { {"Micheal Cassens", "10/7/2017", "2000.00", "Credit Card"},
                            {"Robert Smith", "10/5/2017", "200.00", "Check"},
                            {"Patricia Duce", "10/7/2017", "2000.00", "Cash"}};

        System.out.println("Login sucessfull"); // Debugging
        frame.dispose();

        initialScreen = new InitialScreen(FRAME_STRING, data);

        initialScreen.initComboBox(accountNames);
        initialScreen.updateBalance(accountBalance); //

        // End hardcoded test stuff

      } else {
        System.out.println("Login unsucessful");

        // Routine to retry logging in
      }
    }
  }
}
