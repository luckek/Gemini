import java.lang.reflect.Array;

public class MgmtAccount {
	public static String accountName;
	public static String username;
	public static String password;
	public static Float accountBalance;
	public static float total;
	public static Array transactions; // Convert to array of transaction objects
	public Account[] subAccounts; // Use to hold information of sub-accounts
	//TODO - uhh...all of this...
	//need to tie into controller with view in mind, needs to pull control from controller
	//and inheritance 
	public static void createAccount() {}
	public static void updateBalance() {}
	public static void addTransactions() {}
	public static void deleteTransactions() {}
	public static void getBalance() {}
	// get acctNames
	// get acctNameBalance(String name)
}
