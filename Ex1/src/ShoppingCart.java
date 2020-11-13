
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private int id;

    private Account account;
    private WebUser webUser;

    private List<LineItem> lineItems;


    /**
     * account related to shopping cart will be added by function(eliminate deadlock)
     * @param created
     * @param webUser
     * @throws UnknownError
     */
    public ShoppingCart(int id,Date created,WebUser webUser) throws
            NullPointerException, UnknownError {
        this.created = created;
        setWebUser(webUser);
        this.id = id;
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

    public int getId() { return id; }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Shopping cart has to have exactly one account.
     * @param account
     * @throws UnknownError
     */
    public void setAccount(Account account) throws UnknownError {
        if(account == null)
            throw new NullPointerException("shopping cart must be related to one account");
        if(this.account != null){
            throw new UnknownError("Can't add account because shopping cart has one");
        }
        if(account.getShoppingCart() != this ){
            if(account.getShoppingCart() == null){
                this.account = account;
                account.setShoppingCart(this);
            }
            else{
                throw new UnknownError("Can't add shopping cart because account has one");
            }
        }
        this.account = account;
    }

    /**
     * Shopping has to have exactly one webUser.
     * @param webUser
     * @throws NullPointerException
     */
    public void setWebUser(WebUser webUser) throws NullPointerException {
        if(webUser == null)
            throw new NullPointerException("shopping cart must be related to one web user");
        if(this.webUser != null){
            throw new UnknownError("Can't add web user because shopping cart has one");
        }
        if(webUser.getShoppingCart() != this ){
            if(webUser.getShoppingCart() == null){
                this.webUser = webUser;
                webUser.setShoppingCart(this);
            }
            else{
                throw new UnknownError("Can't add shopping cart because web user has one");
            }
        }
        this.webUser = webUser;
    }

    public void addLineItem(LineItem lineItem) throws NullPointerException, UnknownError {
        if (lineItem == null)
            throw new NullPointerException("Argument can't be null");
        if (!(lineItems.contains(lineItem))) {
            if (lineItem.getShoppingCart() != this) {
                if (lineItem.getShoppingCart() != null) {
                    throw new UnknownError("Can't add line item because it has shopping cart");
                } else {
                    this.lineItems.add(lineItem);
                    lineItem.setShoppingCart(this);
                }
            }
        }
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete(){
        WebUser wb = this.webUser;
        Account acc = this.account;

        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if(this.webUser!=null)
            this.webUser = null;
        if(this.account!=null)
            this.account = null;

        if (acc != null)
            acc.Delete();

        while (!(lineItems.isEmpty())){
            LineItem temp = lineItems.get(0);
            this.lineItems.remove(lineItems.get(0));
            temp.Delete();
        }
    }

    /**
     * The function will delete a line item from the list of items
     */
    public void removeLineItem(LineItem item){
        if (item != null || this.lineItems.contains(item))
            this.lineItems.remove(item);
    }

    public String toString(){
        String print="";
        print="Shopping Cart Date Created: "+this.created+"\nconnected to:";
        if(this.account!=null) { //should have one the check is for us to know if its not working
            if (this.account.getClass() == PremiumAccount.class)
                print +="PremiumAccount";
            else
                print += "Account";
        }
        if(this.webUser!=null) //should have one the check is for us to know if its not working
            print+=", webUser";
        if(!this.lineItems.isEmpty()) {
            for (int i = 0; i < this.lineItems.size(); i++)
                print += ", lineItem";
        }
        return print;
    }

}
