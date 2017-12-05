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
    protected double fee;

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

        // This Converts the sting amt to a large decimal, rounds it with banker's rounding, and returns a double.
        netAmt = new BigDecimal(tmpAmt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        fee = new BigDecimal(grossAmt - netAmt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
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

    public String[] getTransactionInfo() {

        String[] info = new String[8];

        info[0] = name;
        info[1] = date;
        info[2] = Double.toString(grossAmt);
        info[3] = type;
        info[4] = Integer.toString(code);
        info[5] = Double.toString(netAmt);
        info[6] = isDeposit;
        info[7] = Double.toString(fee);

        return info;
    }
}
