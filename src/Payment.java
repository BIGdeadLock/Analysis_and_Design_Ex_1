import com.sun.javaws.exceptions.InvalidArgumentException;

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
     * @throws InvalidArgumentException
     */
    public Payment(String id, Date paid, float total, String details, Order order, Account account) throws InvalidArgumentException {
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
     * @throws InvalidArgumentException
     */
    public void setOrder(Order ord) throws InvalidArgumentException {
        if(ord == null)
            throw new InvalidArgumentException(new String[]{"payment must be related to one order"});
        if(this.order != null){
            throw new InvalidArgumentException(new String[]{"Can't add Order because Payment has one"});
        }
        if(!ord.getPayments().contains(this))
            ord.addPayment(this);
        this.order = ord;


    }

    /**
     * account set -> payment has to have exactly one account.
     * @param acc-Account
     * @throws InvalidArgumentException
     */
    public void setAccount(Account acc) throws InvalidArgumentException {
        if(acc == null)
            throw new InvalidArgumentException(new String[]{"payment must be related to one account"});
        if(this.account != null){
            throw new InvalidArgumentException(new String[]{"Can't add Account because Payment has one"});
        }
        if(!acc.getPayments().contains(this))
            acc.addPayment(this);
        this.account = acc;


    }

    public String toString(){
        String print="";
        print="Payment ID: "+this.id+"\nDate: "+this.paid+" Total: "+this.total+
        " Details: "+this.details;
        return print;
    }

}

