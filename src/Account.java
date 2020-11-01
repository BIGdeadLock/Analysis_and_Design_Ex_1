import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    private List<Payment> payments;

    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance,Customer customer,ShoppingCart shoppingCart) throws InvalidArgumentException {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        setCustomer(customer);
        setShoppingCart(shoppingCart);
        this.orders = new ArrayList<Order>();
        this.payments = new ArrayList<Payment>();
    }

    /**
     * Account has to have exactly one custumer.
     * @param customer
     * @throws InvalidArgumentException
     */
    public void setCustomer(Customer customer) throws InvalidArgumentException {
        if(customer == null)
            throw new InvalidArgumentException(new String[]{"Account must be related to one custumer"});
        this.customer = customer;
    }

    /**
     * Account has to have exactly one shoppingCart.
     * @param shoppingCart - ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setShoppingCart(ShoppingCart shoppingCart) throws InvalidArgumentException {
        if(shoppingCart == null)
            throw new InvalidArgumentException(new String[]{"Account must be related to one shoppingCart"});
        this.shoppingCart = shoppingCart;
    }

    public void addPayment(Payment payment){
        this.payments.add(payment);
    }
    public void addOrder(Order order){ this.orders.add(order); }
}
