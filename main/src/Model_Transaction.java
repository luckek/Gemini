import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Model_Transaction {

    public String name;
    protected String type;
    protected int code;
    protected double grossAmt;
    protected String isDeposit;
    protected String date;
    protected double netAmt;
    protected double percentage;
    protected ArrayList<String> myList = new ArrayList<>();
    //might need to change data types

    public Model_Transaction(String name, String type, int code, double grossAmt, String isDeposit, String date) {

        this.name = name;
        this.type = type;
        this.code = code;
        this.grossAmt = grossAmt;
        this.isDeposit = isDeposit;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNet(double netAmt) {
        this.netAmt = netAmt;
    }

    public void setED(String isDeposit) {
        this.isDeposit = isDeposit;
    }

    public void setGross(double grossAmt) {
        this.grossAmt = grossAmt;

        double tmpAmt = grossAmt - (grossAmt * percentage);

        String amt = Double.toString(tmpAmt);

        // This Converts the sting amt to a large decimal, rounds it with banker's rounding, and returns a double.
        netAmt = new BigDecimal(amt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public double getNet() {
        return netAmt;
    }

    public String isDeposit() {
        return isDeposit;
    }

    public double getGross() {
        return grossAmt;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getAll(String type, String name, double code, float netAmt, boolean creditDebit, float grossAmt, String date, double percentage){
        myList.add(type);
        myList.add(name);
        myList.add(String.valueOf(code));
        myList.add(String.valueOf(netAmt));
        myList.add(String.valueOf(creditDebit));
        myList.add(String.valueOf(grossAmt));
        myList.add(date);
        myList.add(String.valueOf(percentage));
        return myList;
    }
}
