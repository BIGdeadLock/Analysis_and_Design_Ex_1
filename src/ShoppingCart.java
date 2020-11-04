import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;

    private Account account;
    private WebUser webUser;

    private List<LineItem> lineItems;

    /**
     * account related to shopping cart will be added by function(eliminate deadlock)
     * @param created
     * @param webUser
     * @throws InvalidArgumentException
     */
    public ShoppingCart(Date created,WebUser webUser) throws InvalidArgumentException {
        this.created = created;
        setWebUser(webUser);
        this.lineItems = new ArrayList<>();
    }

    public Date getCreated() {
        return created;
    }

    public Account getAccount() {
        return account;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
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

    public String toString(){
        String print="";
        print="Shopping Cart Date Created: "+this.created;
        return print;
    }
}
