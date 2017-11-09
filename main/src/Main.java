
public class Main {

    private static Controller controlCentral;

    final static String VERSION = "0.0.1";
    final static String SOFTWARE_NAME = "Astro Account Management Software";
    final static String COMPANY_NAME = "Gemini Corp.";
    final static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
    final static String DEV_STRING = "Developed By: " + COMPANY_NAME;

    public static void main (String[] args) {
        controlCentral = new Controller();
        controlCentral.initializeScreen();
    }
}