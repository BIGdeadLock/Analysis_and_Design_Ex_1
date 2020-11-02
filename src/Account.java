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

    /**
     * account will be set with customer --> then customer will set its account (by function)
     * @throws InvalidArgumentException
     */
    public Account(String id, String billing_address, Customer customer,ShoppingCart shoppingCart) throws InvalidArgumentException {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.closed = null;
        this.balance = 0;
        setCustomer(customer);
        setShoppingCart(shoppingCart);
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public boolean getIs_closed() {
        return is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public Date getClosed() {
        return closed;
    }

    public int getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public int getOrderSize(){
        return orders.size();
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
