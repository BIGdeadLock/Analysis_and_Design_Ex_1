
import java.util.Date;

public class DelayedPayment extends Payment {
    private Date paymentDate;

    public DelayedPayment(String id, Date paid, float total, String details, Order order, Account account, Date paymentDate) throws
            NullPointerException, UnknownError{
        super(id, paid, total, details, order, account);
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate() { return paymentDate; }

    public String toString(){
        String print="";
        print="Payment ID: "+getId()+"\nDate: "+getPaid()+" Total: "+getTotal()+
                " Details: "+getDetails()+"Payment Date: "+this.paymentDate+"\nconnected to:";
        if(getAccount()!=null)  //should have one the check is for us to know if its not working
            print+="Account";
        if(getOrder()!=null)  //should have one the check is for us to know if its not working
            print+=", Order";
        return print;
    }
}
