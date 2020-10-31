import java.util.Date;

public class Account {
    private String id;
    private String billing_addrss;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private Customer costumer;
    private ShoppingCart shoppingCart;
    private List<Order> order;
    private List<Payment> payment;

    public Account(String id, String billing_addrss, boolean is_closed, Date open, Date closed, int balance) {
        this.id = id;
        this.billing_addrss = billing_addrss;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
    }
}
