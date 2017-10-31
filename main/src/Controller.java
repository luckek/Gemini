import java.awt.event.*;

public class Controller {


	
    private static String VERSION = "0.0.1";
    private static String SOFTWARE_NAME = "Astro Account Management Software";
    private static String COMPANY_NAME = "Gemini Corp.";
    private static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
    private static String DEV_STRING = "Developed By: " + COMPANY_NAME;
    private static InitialScreen initialScreen;
    private static LoginPanel mypanel;
	  
    @SuppressWarnings("static-access")
    void initializeScreen() {
        mypanel = new LoginPanel();
        mypanel.startPanel();
     }

    static class loginAction implements ActionListener {
        public void actionPerformed (ActionEvent e) {

            String[] accountNames = {"All", "Michael", "Trish", "Rob"}; // This should be replaced with all acct names
            double accountBalance = 0.00; // This may stay hardcoded for now.

            // This should be populated by all of the transaction data.
            String data [][] = {};

	        initialScreen = new InitialScreen(FRAME_STRING);

            initialScreen.initComboBox(accountNames);
            initialScreen.updateBalance(accountBalance);

      }
    }
}