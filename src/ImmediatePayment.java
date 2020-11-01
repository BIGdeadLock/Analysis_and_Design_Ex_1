import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Date;

public class ImmediatePayment extends Payment {
    private Boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Order order, Account account, Boolean phoneConfirmation) throws InvalidArgumentException {
        super(id, paid, total, details, order, account);
        this.phoneConfirmation = phoneConfirmation;
    }
}
