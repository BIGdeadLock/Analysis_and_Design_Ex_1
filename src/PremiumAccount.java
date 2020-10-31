import java.util.Date;

public class PremiumAccount extends Account {
    public PremiumAccount(String id, String billing_addrss, boolean is_closed, Date open, Date closed, int balance) {
        super(id, billing_addrss, is_closed, open, closed, balance);
    }
}
