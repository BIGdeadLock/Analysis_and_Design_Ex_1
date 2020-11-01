import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    private String id;
    private String billing_addrss;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private Customer custumer;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    private List<Payment> payments;

    public Account(String id, String billing_addrss, boolean is_closed, Date open, Date closed, int balance,Customer costume,ShoppingCart shoppingCart,List<Payment> payment,List<Order> order) throws InvalidArgumentException {
        this.id = id;
        this.billing_addrss = billing_addrss;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        setCustomr(custumer);
        setShoppingCart(shoppingCart);
        this.orders = new ArrayList<Order>();
        this.payments = new ArrayList<Payment>();
    }

    /**
     * Account has to have exactly one custumer.
     * @param custumer
     * @throws InvalidArgumentException
     */
    public void setCustomr(Customer custumer) throws InvalidArgumentException {
        if(custumer == null)
            throw new InvalidArgumentException(new String[]{"Account must be related to one custumer"});
        this.custumer = custumer;
    }

    /**
     * Account has to have exactly one shoppingCart.
     * @param shoppingCart
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
    public void addOrder(Order order){
        this.payments.add(order);
    }
}
