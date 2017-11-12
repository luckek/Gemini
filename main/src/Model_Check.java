
public class Model_Check extends Model_Transaction {

    Model_Check(String type, String name, int code, boolean isDeposit, float grossAmt, String date) {
        super(name, type, code, grossAmt, isDeposit, date);
        percentage = 0.04;
        netAmt = grossAmt - (grossAmt * percentage);
    }
}
