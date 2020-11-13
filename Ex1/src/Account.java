
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    protected  String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;

    protected  Customer customer;
    protected   ShoppingCart shoppingCart;

    protected   List<Order> orders;
    protected   List<Payment> payments;

    /**
     * account will be set with customer --> then customer will set its account (by function)
     * @throws NullPointerException, ConnectException
     */
    public Account(String id, String billing_address,int balance, Customer customer,ShoppingCart shoppingCart) throws
            NullPointerException, UnknownError {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.closed = null;
        this.balance = balance;
        setCustomer(customer);
        setShoppingCart(shoppingCart);
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public Account(String id, String billing_address,int balance, Customer customer, int cartid) throws
            UnknownError, NullPointerException {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.closed = null;
        this.balance = balance;
        setCustomer(customer);
        this.shoppingCart = new ShoppingCart(cartid,new Date(),customer.getWebUser());
        shoppingCart.setAccount(this);
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
     * @throws NullPointerException
     */
    public void setCustomer(Customer customer) throws NullPointerException, UnknownError {
        if(customer == null)
            throw new NullPointerException("Account must be related to one custumer");
        if(this.customer != null){
            throw new UnknownError("Can't add customer because account has one");
        }
        if(customer.getAccount() != this ){
            if(customer.getAccount() == null){
                this.customer = customer;
                customer.setAccount(this);
            }
            else{
                throw new UnknownError("Can't add customer because account has customer");
            }
        }

    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    /**
     * Account has to have exactly one shoppingCart.
     * @param shoppingCart - ShoppingCart
     * @throws NullPointerException, UnknownError
     */
    public void setShoppingCart(ShoppingCart shoppingCart) throws NullPointerException, UnknownError {
        if(shoppingCart == null)
            throw new NullPointerException("Account must be related to one shoppingCart");
        if(this.shoppingCart != null){
            throw new UnknownError("Can't add shopping cart because it has one");
        }
        if(shoppingCart.getAccount() != this ){
            if(shoppingCart.getAccount() == null){
                this.shoppingCart = shoppingCart;
                shoppingCart.setAccount(this);
            }
            else{
                throw new UnknownError("Can't add shoping cart because it has account");
            }
        }
        this.shoppingCart = shoppingCart;
    }

    public void addPayment(Payment payment) throws NullPointerException, UnknownError {
        if(payment == null){
            throw new NullPointerException("Can't add a null");
        }
        if(this.payments.contains(payment)){
            throw new UnknownError("already has this payment");
        }
        if(payment.getAccount() != this) {
            if (payment.getAccount() != null) {
                throw new UnknownError("Can't add payment because it has account ");
            }
            else{
                this.payments.add(payment);
                payment.setAccount(this);
            }
        }
    }

    public void addOrder(Order order) throws NullPointerException, UnknownError {
        if(order == null){
            throw new NullPointerException("Can't add a null");
        }
        if(this.orders.contains(order)){
            throw new UnknownError("already has this order");
        }
        if(order.getAccount() != this) {
            if (order.getAccount() != null) {
                throw new UnknownError("Can't add order because it has account ");
            }
            else{
                this.orders.add(order);
                order.setAccount(this);
            }
        }
        this.orders.add(order);
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete() {
        ShoppingCart shp = this.shoppingCart;
        Customer customer = this.customer;
        List<Order> orderList = this.orders;
        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if(this.customer!=null)
            this.customer = null;
        if(this.shoppingCart!=null)
            this.shoppingCart = null;

        if (shp != null)
            shp.Delete();

        if (customer != null) {
            try {
                customer.Delete();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (UnknownError c){
                c.printStackTrace();
            }

        }

        if (orderList != null){
            while (!(orderList.isEmpty())) {
                Order temp = orderList.get(0);
                this.removeOrder(orderList.get(0));
                temp.Delete();
            }
        }
    }

    /**
     * The function will delete an order from the list of orders
     */
    public void removeOrder(Order order) {
        if (order != null || this.orders.contains(order))
            this.orders.remove(order);

    }

    /**
     * The function will delete an payment from the list of payments
     */
    public void removePayment(Payment payment){
        if (payment != null || this.payments.contains(payment))
            this.payments.remove(payment);
    }

    public String toString(){
        String print="";
        print="Account ID: "+this.id+"\nBilling Address: "+this.billing_address+
                " closed status: "+this.is_closed+" Date Opened: "+this.open+
                " Date Closed: "+this.closed+" Balance:"+this.balance+"\nconnected to:";
        if(this.customer!=null) //should have one the check is for us to know if its not working
            print+="Customer";
        if(this.shoppingCart!=null) //should have one the check is for us to know if its not working
            print+=", ShoppingCart";
        if(!this.payments.isEmpty()){
            for (int i = 0; i < this.payments.size(); i++)
            {
                if(this.payments.get(i).getClass()==ImmediatePayment.class)
                print+=", ImmediatePayment";
                if(this.payments.get(i).getClass()==DelayedPayment.class)
                    print+=", DelayedPayment";
            } }
        if(!this.orders.isEmpty()){
            for (int i = 0; i < this.orders.size(); i++)
                print += ", Order";
        }
        return print;
    }
}
