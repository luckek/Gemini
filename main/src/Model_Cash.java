
public class Model_Cash extends Model_Transaction {

    Model_Cash(String type, String name, int code, String isDeposit, double grossAmt, String date) {
        super(name, type, code, grossAmt, isDeposit, date);
        percentage = 0.04;
        netAmt = grossAmt - (grossAmt * percentage);
    }
}
