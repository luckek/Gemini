
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

    private String acctName;
    private double balance;
    private String email;
    private String description;
    private boolean isRetired;
    private String phoneNumber;

    public Account(String name, String email, String description, boolean isRetired, String phoneNumber) {

        this.acctName = name;
        balance = 0;
        this.email = email;
        this.description = description;
        this.isRetired = isRetired;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return acctName; }
    public double getBalance() { return balance; }
    public String getEmail() { return email; }
    public String getDescription() { return description; }
    public boolean isRetired() { return isRetired; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setName(String name) { acctName = name; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setEmail(String email) { this.email = email; }
    public void setDesc(String desc) { this.description = desc; }
    public void setIsRetired(boolean isRetired) { this.isRetired = isRetired; }
    public void setPhoneNumber(String newNumber) { this.phoneNumber = phoneNumber; }

    void modifyBalance(double amount) {
        balance = new BigDecimal(balance + amount).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    String[] nameAndBalance() {
        return new String[]{acctName, Double.toString(balance)};
    }
}
