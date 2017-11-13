import java.math.BigDecimal;
import java.math.RoundingMode;

public class Model_Credit extends Model_Transaction {

    Model_Credit(String type, String name, int code, String isDeposit, double grossAmt, String date) {
        super(name, type, code, grossAmt, isDeposit, date);
        percentage = 0.12;
        double tmpAmt = grossAmt - (grossAmt * percentage);

        String amt = Double.toString(tmpAmt);

        // This Converts the sting amt to a large decimal, rounds it with banker's rounding, and returns a double.
        netAmt = new BigDecimal(amt).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
