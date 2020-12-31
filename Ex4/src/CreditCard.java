import java.util.Date;

public class CreditCard {
    String Number;
    Date Expiration;
    String CVV;

    public CreditCard(String number, Date expiration, String cvv) {
        Number = number;
        Expiration = expiration;
        CVV= cvv;
    }

    //GET
    public String getNumber() { return Number; }
    public Date getExpiration() { return Expiration; }
    public String getCVV(){return CVV;}

    //SET
    public void setNumber(String number) { Number = number; }
    public void setExpiration(Date expiration) { Expiration = expiration; }
}


