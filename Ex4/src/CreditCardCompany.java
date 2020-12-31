import java.util.HashMap;
import java.util.*;

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
        Date currentDate = new Date();
        if (card.Expiration.before(currentDate))
            return false;
        boolean isNumericNumber = card.getNumber().chars().allMatch( Character::isDigit );
        boolean isNumericCVV = card.getNumber().chars().allMatch( Character::isDigit );
        if (!isNumericNumber && !isNumericCVV){
            return false;
        }

        return true;
    }
}
