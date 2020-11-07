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
    public Account(String id, String billing_address,int balance, Customer customer,ShoppingCart shoppingCart) throws InvalidArgumentException {
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

    public Account(String id, String billing_address,int balance, Customer customer) throws InvalidArgumentException {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.closed = null;
        this.balance = balance;
        setCustomer(customer);
        this.shoppingCart = new ShoppingCart(new Date(),customer.getWebUser());
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
        if(this.customer != null){
            throw new InvalidArgumentException(new String[]{"Can't add customer because account has one"});
        }
        if(customer.getAccount() != this ){
            if(customer.getAccount() == null){
                this.customer = customer;
                customer.setAccount(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add customer because account has customer"});
            }
        }

    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    /**
     * Account has to have exactly one shoppingCart.
     * @param shoppingCart - ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setShoppingCart(ShoppingCart shoppingCart) throws InvalidArgumentException {
        if(shoppingCart == null)
            throw new InvalidArgumentException(new String[]{"Account must be related to one shoppingCart"});
        if(this.shoppingCart != null){
            throw new InvalidArgumentException(new String[]{"Can't add shopping cart because it has one"});
        }
        if(shoppingCart.getAccount() != this ){
            if(shoppingCart.getAccount() == null){
                this.shoppingCart = shoppingCart;
                shoppingCart.setAccount(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add shoping cart because it has account"});
            }
        }

    }

    public void addPayment(Payment payment) throws InvalidArgumentException {
        if(payment == null){
            throw new InvalidArgumentException(new String[]{"Can't add a null"});
        }
        if(this.payments.contains(payment)){
            throw new InvalidArgumentException(new String[]{"already has this payment"});
        }
        if(payment.getAccount() != this) {
            if (payment.getAccount() != null) {
                throw new InvalidArgumentException(new String[]{"Can't add payment because it has account "});
            }
            else{
                this.payments.add(payment);
                payment.setAccount(this);
            }
        }

    }
    public void addOrder(Order order) throws InvalidArgumentException {
        if(order == null){
            throw new InvalidArgumentException(new String[]{"Can't add a null"});
        }
        if(this.orders.contains(order)){
            throw new InvalidArgumentException(new String[]{"already has this order"});
        }
        if(order.getAccount() != this) {
            if (order.getAccount() != null) {
                throw new InvalidArgumentException(new String[]{"Can't add order because it has account "});
            }
            else{
                this.orders.add(order);
                order.setAccount(this);
            }
        }
        this.orders.add(order);
    }

    public void Delete() throws InvalidArgumentException {
      //  this.shoppingCart = null;
     //   this.shoppingCart.Delete();
       // this.customer.Delete();
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
            for (int i = 0; i < this.payments.size() - 1; i++)
            {
                if(this.payments.get(i).getClass()==ImmediatePayment.class)
                print+=", ImmediatePayment";
                if(this.payments.get(i).getClass()==DelayedPayment.class)
                    print+=", DelayedPayment";
            }

        }
        if(!this.orders.isEmpty()){
            for (int i = 0; i < this.orders.size(); i++)
                print += ", Order";
        }
        return print;
    }

}
