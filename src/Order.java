import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String number;
    private Date ordered;
    private Date shipped;
    private String ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    private List<Payment> payments;
    private List<LineItem> lineItems;

    /**
     * Order constructor -> includes number, ordered, shipped, ship_to, status and total. must have account.
     * @param number - string
     * @param ordered - Date
     * @param shipped - Date
     * @param ship_to - string
     * @param total - float
     */
    public Order(String number, Date ordered, Date shipped, String ship_to, float total)  {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = total;

        this.lineItems = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    // GETTERS

    public String getNumber() { return number; }

    public Date getOrdered() { return ordered; }

    public Date getShipped() { return shipped; }

    public String  getShip_to() { return ship_to; }

    public OrderStatus getStatus() { return status; }

    public float getTotal() { return total; }

    public Account getAccount() { return account; }

    public List<Payment> getPayments() { return payments; }

    public List<LineItem> getLineItems() { return lineItems; }

    // SETTERS

    /**
     * account set -> order has to have exactly one account.
     * @param acc-Account
     * @throws InvalidArgumentException
     */
    public void setAccount(Account acc) throws InvalidArgumentException {
        if(acc == null)
            throw new InvalidArgumentException(new String[]{"Argument can't be null"});

        // Before assigment need to check that the order is not related to any account
        // (composition relation)
        assert  (this.account == null);
        this.account = acc;
    }

    /**
     * add items to the LineItem list (initiated int the constructor)
     * @param item - LineItem
     */
    public void addLineItem(LineItem item) throws InvalidArgumentException {
        if(item == null)
            throw new InvalidArgumentException(new String[]{"Argument can't be null"});
        // Instances assertions - each order instance can't
        // be related to the same Lineitem instance twice
        assert(!this.lineItems.contains(item));
        item.setOrder(this);
        this.lineItems.add(item);
    }

    /**
     * add payment to the payment list (initiated int the constructor)
     * @param pay - Payment
     */
    public void addPayment(Payment pay) throws InvalidArgumentException {
        if(pay == null)
            throw new InvalidArgumentException(new String[]{"Argument can't be null"});
        // Instances assertions - each order instance can't
        // Instances assertions - each order instance can't
        // be related to the same pay instance twice
        assert(!this.payments.contains(pay));
        pay.setOrder(this);
        this.payments.add(pay);
    }


    /**
     * The print method of the class
     * @return String | String representation of the class.
     */
    public String toString(){
        String print="";
        print="Order Number:"+this.number+"\nDate Ordered:"+this.ordered+" Date Shipped: " +
                this.shipped+" Shipping Destination:"+this.ship_to+
                " Status:"+this.status+" Total:"+this.total;
        return print;
    }

}
