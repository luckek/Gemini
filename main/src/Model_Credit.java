
public class Model_Credit extends Model_Transaction {

    Model_Credit(String type, String name, int code, boolean isDeposit, float grossAmt, String date) {
        super(name, type, code, grossAmt, isDeposit, date);
        percentage = 0.12;
        netAmt = grossAmt - (grossAmt * percentage);
    }
}
