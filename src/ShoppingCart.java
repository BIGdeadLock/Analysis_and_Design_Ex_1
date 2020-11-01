import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;
    private List<LineItem> lineItems;

    public ShoppingCart(Date created , Account account , WebUser webUser ) throws InvalidArgumentException {
        this.created = created;
        setAccount(account);
        setWebUser(webUser);
        this.lineItems = new ArrayList<>();
    }


    /**
     * Shopping cart has to have exactly one account.
     * @param account
     * @throws InvalidArgumentException
     */
    public void setAccount(Account account) throws InvalidArgumentException {
        if(account == null)
            throw new InvalidArgumentException(new String[]{"shopping cart must be related to one account"});
        this.account = account;
    }

    /**
     * Shopping has to have exactly one webUser.
     * @param webUser
     * @throws InvalidArgumentException
     */
    public void setWebUser(WebUser webUser) throws InvalidArgumentException {
        if(webUser == null)
            throw new InvalidArgumentException(new String[]{"shopping cart must be related to one shopping web user"});
        this.webUser = webUser;
    }

    public void addLineItem(LineItem lineItem){
        this.lineItems.add(lineItem);
    }
}
