
public class Account {

    private String acctName;
    private String balance;
    private String email;
    private String description;

    public Account (String name, String balance, String email, String description) {

        this.acctName = name;
        this.balance = balance;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return this.acctName;
    }

    public String getBalance() {
        return this.balance;
    }
    public String getEmail() {
        return this.email;
    }
    public String getDescription() {
        return this.description;
    }

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
}
