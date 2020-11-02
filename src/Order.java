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
     * @param account - Account
     * @throws InvalidArgumentException
     */
    public Order(String number, Date ordered, Date shipped, String ship_to, float total, Account account) throws InvalidArgumentException {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = total;
        setAccount(account);
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
            throw new InvalidArgumentException(new String[]{"order must be related to one account"});
        this.account = acc;
    }

    /**
     * add items to the LineItem list (initiated int the constructor)
     * @param item - LineItem
     */
    public void addLineItem(LineItem item){
        this.lineItems.add(item);
    }

    /**
     * add payment to the payment list (initiated int the constructor)
     * @param pay - Payment
     */
    public void addPayment(Payment pay){
        this.payments.add(pay);
    }

    // TODO: Check if need to add customer and shit

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
