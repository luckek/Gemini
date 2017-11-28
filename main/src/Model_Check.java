import java.math.BigDecimal;
import java.math.RoundingMode;

public class Model_Check extends Model_Transaction {

    Model_Check(String type, String name, int code, String isDeposit, double grossAmt, String date) {
        super(name, type, code, grossAmt, isDeposit, date);
        percentage = 0.04;
        double tmpAmt = grossAmt - (grossAmt * percentage);

        // This Converts the sting amt to a large decimal, rounds it with banker's rounding, and returns a double.
        netAmt = new BigDecimal(tmpAmt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        fee = new BigDecimal(grossAmt - netAmt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
