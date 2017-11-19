
public class Account {

    private String acctName;
    private String balance;
    private String email;
    private String description;
    private boolean isRetired;

    public Account (String name, String balance, String email, String description, boolean isRetired) {

        this.acctName = name;
        this.balance = balance;
        this.email = email;
        this.description = description;
        this.isRetired = isRetired;
    }

    public String getName() {
        return acctName;
    }

    public String getBalance() {
        return balance;
    }
    public String getEmail() {
        return email;
    }
    public String getDescription() {
        return description;
    }
    public boolean isRetired() { return isRetired; }

    public void setName(String name) {

        this.acctName = name;
    }
    public void setBalance(String balance) {

        this.balance = balance;
    }
    public void setEmail(String email) {

        this.email = email;
    }
    public void setDesc(String desc) {

        this.description = desc;
    }
    public void setIsRetired(boolean isRetired) { this.isRetired = isRetired; }

    void modifyBalance(double amount) {

        double balanceVal = new Double(balance);
        balanceVal += amount;
        this.balance = Double.toString(balanceVal);
    }
}
