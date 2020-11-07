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
        if(this.account != null){
            throw new InvalidArgumentException(new String[]{"Can't add account because shopping cart has one"});
        }
        if(account.getShoppingCart() != this ){
            if(account.getShoppingCart() == null){
                this.account = account;
                account.setShoppingCart(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add shopping cart because account has one"});
            }
        }
        this.account = account;
    }

    /**
     * Shopping has to have exactly one webUser.
     * @param webUser
     * @throws InvalidArgumentException
     */
    public void setWebUser(WebUser webUser) throws InvalidArgumentException {
        if(webUser == null)
            throw new InvalidArgumentException(new String[]{"shopping cart must be related to one web user"});
        if(this.webUser != null){
            throw new InvalidArgumentException(new String[]{"Can't add web user because shopping cart has one"});
        }
        if(webUser.getShoppingCart() != this ){
            if(webUser.getShoppingCart() == null){
                this.webUser = webUser;
                webUser.setShoppingCart(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add shopping cart because web user has one"});
            }
        }
        this.webUser = webUser;
    }

    public void addLineItem(LineItem lineItem) throws InvalidArgumentException {
        if (lineItem == null)
            throw new InvalidArgumentException(new String[]{"Argument can't be null"});
        if (!(lineItems.contains(lineItem))) {
            if (lineItem.getShoppingCart() != this) {
                if (lineItem.getShoppingCart() != null) {
                    throw new InvalidArgumentException(new String[]{"Can't add line item because it has shopping cart "});
                } else {
                    this.lineItems.add(lineItem);
                    lineItem.setShoppingCart(this);
                }
            }
        }
    }

    public void Delete(){
        this.webUser=null;
     //   for (int i = 0; i < lineItems.size() ; i++) {
       //     lineItems.get(i).Delete(); //delete each line item (has to have one shopping cart)
        //}
   //     this.account.Delete();
    }

    public String toString(){
        String print="";
        print="Shopping Cart Date Created: "+this.created+"\nconnected to:";
        if(this.account!=null) //should have one the check is for us to know if its not working
            print+="Account";
        if(this.webUser!=null) //should have one the check is for us to know if its not working
            print+="webUser";
        if(!this.lineItems.isEmpty()) {
            for (int i = 0; i < this.lineItems.size(); i++)
                print += ", lineItem";
        }
        return print;
    }
}
