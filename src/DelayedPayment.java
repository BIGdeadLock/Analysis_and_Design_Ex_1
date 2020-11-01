import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Date;

public class DelayedPayment extends Payment {
    private Date paymentDate;

    public DelayedPayment(String id, Date paid, float total, String details, Order order, Account account, Date paymentDate) throws InvalidArgumentException {
        super(id, paid, total, details, order, account);
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate() { return paymentDate; }
}
