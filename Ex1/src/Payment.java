
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Payment  {
    private String id;
    private Date paid;
    private float total;
    private String details;
    private Order order;
    private Account account;

    /**
     * Payment constructor -> includes id, paid, total and details. must have a Order and Account
     * @param id - string
     * @param paid - date
     * @param total - float
     * @param details - string
     * @param order - Order
     * @param account - Account
     * @throws NullPointerException, UnknownError
     */
    public Payment(String id, Date paid, float total, String details, Order order, Account account) throws
            NullPointerException, UnknownError {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        setAccount(account);
        setOrder(order);
    }
    // GETTERS
    public String getId() { return id; }

    public Date getPaid() { return paid;    }

    public float getTotal() { return total; }

    public String getDetails() { return details; }

    public Order getOrder() { return order; }

    public Account getAccount() { return account; }

    // SETTERS

    /**
     * order set -> payment has to have exactly one order.
     * @param ord-Order
     * @throws NullPointerException
     */
    public void setOrder(Order ord) throws NullPointerException, UnknownError {
        if(ord == null)
            throw new NullPointerException("payment must be related to one order");
        if(this.order != null){
            throw new UnknownError("Can't add Order because Payment has one");
        }
        if(!ord.getPayments().contains(this))
            ord.addPayment(this);
        this.order = ord;


    }

    /**
     * account set -> payment has to have exactly one account.
     * @param acc-Account
     * @throws NullPointerException
     */
    public void setAccount(Account acc) throws NullPointerException, UnknownError {
        if(acc == null)
            throw new NullPointerException("payment must be related to one account");
        if(this.account != null){
            throw new UnknownError("Can't add Account because Payment has one");
        }
        if(!acc.getPayments().contains(this))
            acc.addPayment(this);
        this.account = acc;


    }

    public String toString(){
        String print="";
        print="Payment ID: "+this.id+"\nDate: "+this.paid+" Total: "+this.total+
        " Details: "+this.details+"\nconnected to:";
        if(this.account!=null){  //should have one the check is for us to know if its not working
            if (this.account.getClass() == PremiumAccount.class)
                print +="PremiumAccount";
            else
                print += "Account";
        }
        if(this.order!=null)  //should have one the check is for us to know if its not working
            print+=", Order";
        return print;
    }


    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete(){
        Order ord = this.order;
        Account acc = this.account;

        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if (ord != null) {
            this.order = null;
            ord.removePayment(this);
        }

        if (acc != null) {
            this.account = null;
            acc.removePayment(this);
        }
    }
}

