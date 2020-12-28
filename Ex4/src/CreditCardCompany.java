import java.util.HashMap;

public class CreditCardCompany {

    HashMap<String, Integer> creditCards;

    public CreditCardCompany() {
        this.creditCards = new HashMap<String, Integer>();
    }

    public void ChargeCard(CreditCard creditCard, double payment){

    }

    public boolean ApproveCard(CreditCard card){
        if (card==null)
            return false;
        return true;
    }
}
