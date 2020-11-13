
import java.util.Date;

public class ImmediatePayment extends Payment {
    private Boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Order order, Account account, Boolean phoneConfirmation) throws
            NullPointerException, UnknownError{
        super(id, paid, total, details, order, account);
        this.phoneConfirmation = phoneConfirmation;
    }
    public String toString(){
        String print="";
        print="Payment ID: "+getId()+"\nDate: "+getPaid()+" Total: "+getTotal()+
                " Details: "+getDetails()+"Payment Confirmation: "+this.phoneConfirmation+"\nconnected to:";
        if(getAccount()!=null)  //should have one the check is for us to know if its not working
            print+="Account";
        if(getOrder()!=null)  //should have one the check is for us to know if its not working
            print+=", Order";
        return print;
    }
}
