
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

    public Order(String number, Date ordered, Date shipped, String ship_to, float total, Account account) {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = total;
        this.account = account;
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
     * @throws NullPointerException
     */
    public void setAccount(Account acc) throws NullPointerException {
        if(acc == null)
            throw new NullPointerException("Argument can't be null");

        // Before assigment need to check that the order is not related to any account
        // (composition relation)
        assert  (this.account == null);
        this.account = acc;
    }

    public void setLineItems(List<LineItem> lineItems) throws NullPointerException {
        if(lineItems == null){
            throw new NullPointerException("Argument can't be null");
        }
        this.lineItems = lineItems;
    }

    /**
     * add items to the LineItem list (initiated int the constructor)
     * @param item - LineItem
     */
    public void addLineItem(LineItem item) throws NullPointerException {
        if(item == null)
            throw new NullPointerException("Argument can't be null");
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
    public void addPayment(Payment pay) throws NullPointerException {
        if(pay == null)
            throw new NullPointerException("Argument can't be null");
        // Instances assertions - each order instance can't
        // Instances assertions - each order instance can't
        // be related to the same pay instance twice
        assert(!this.payments.contains(pay));
        this.payments.add(pay);
        pay.setOrder(this);

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

    public void PrintTheConnection(){
        String Print="";
        Print+="connected to:";
        if(this.account!=null) {//should have one the check is for us to know if its not working
            if (this.account.getClass() == PremiumAccount.class)
                Print +="PremiumAccount";
            else
                Print += "Account";
        }
        if(!this.payments.isEmpty()) {
            for (int i = 0; i < this.payments.size(); i++) {
                if (this.payments.get(i).getClass() == ImmediatePayment.class)
                    Print += ", ImmediatePayment";
                if (this.payments.get(i).getClass() == DelayedPayment.class)
                    Print += ", DelayedPayment";
            }
        }
        if(!this.lineItems.isEmpty()){
            for (int i = 0; i < this.lineItems.size(); i++)
                Print += ", lineItem";
        }
        System.out.println(Print);
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete() {
        List<LineItem> itemList = this.lineItems;
        List<Payment> paymentList = this.payments;
        Account acc = this.account;
        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if(acc!=null)
            this.account = null;

        if (itemList != null) {
            while (!(lineItems.isEmpty())) {
                LineItem temp = lineItems.get(0);
                this.removeLineItem(lineItems.get(0));
                temp.Delete();
            }
        }

        if(paymentList != null){
            while (!(payments.isEmpty())) {
                Payment temp = payments.get(0);
                this.removePayment(payments.get(0));
                temp.Delete();
            }
        }


    }

    /**
     * The function will delete a line item from the list of items
     */
    public void removeLineItem(LineItem item){
        if (item != null || this.lineItems.contains(item))
            this.lineItems.remove(item);
    }

    /**
     * The function will delete a payment from the list of payments
     */
    public void removePayment(Payment payment) {
        if (payment != null || this.payments.contains(payment))
            this.payments.remove(payment);

    }
}
