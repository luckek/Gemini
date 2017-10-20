public class Main {

    private static Controller controlCentral;

    static String VERSION = "0.0.1";
    static String SOFTWARE_NAME = "Astro Account Management Software";
    static String COMPANY_NAME = "Gemini Corp.";
    static String FRAME_STRING = SOFTWARE_NAME + " Version: " + VERSION;
    static String DEV_STRING = "Developed By: " + COMPANY_NAME;

    public static void main (String[] args) {
        controlCentral = new Controller();
        controlCentral.initializeScreen();
    }
}