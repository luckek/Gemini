import java.util.ArrayList;

public class Model_Transaction {

    public String type;
    public String name;
    public double code;
    public float netAmt;
    public boolean creditDebit;
    public float grossAmt;
    public String date;
    public ArrayList<String> myList = new ArrayList<>();
    public double percentage;
    //might need to change data types

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public void setNet(float netAmt) {
        this.netAmt = netAmt;
    }

    public void setCD(boolean creditDebit) {
        this.creditDebit = creditDebit;
    }

    public void setGross(float grossAmt) {
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

    public double getCode() {
        return code;
    }

    public float getNet() {
        return netAmt;
    }

    public boolean getCD() {
        return creditDebit;
    }

    public float getGross() {
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
