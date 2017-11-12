import java.util.ArrayList;

public class Model_Transaction {

    private String type;
    public String name;
    private int code;
    private double netAmt;
    private boolean isDeposit;
    private double grossAmt;
    private String date;
    private ArrayList<String> myList = new ArrayList<>();
    private double percentage;
    //might need to change data types

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

    public void setED(boolean creditDebit) {
        this.isDeposit = creditDebit;
    }

    public void setGross(double grossAmt) {
        this.grossAmt = grossAmt;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
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

    public boolean isDeposit() {
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
