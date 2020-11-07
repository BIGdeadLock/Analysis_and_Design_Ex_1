import com.sun.javaws.exceptions.InvalidArgumentException;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public WebUser(String login_id, String password,Customer customer) throws InvalidArgumentException {
        this.login_id = login_id;
        this.password = password;
        this.state = UserState.New;
        setCustomer(customer);
    }

    public WebUser(String login_id, String password, String id , String address, String phone, String email, String idAccount,
                   String billing_address,int balance, boolean isPremium, int cardid) throws InvalidArgumentException {
        this.login_id = login_id;
        this.password = password;
        this.state = UserState.New;
        this.customer  = new Customer(id ,address,phone, email, idAccount,
                 billing_address,balance, isPremium, this, cardid);
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserState getState() {
        return state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * WebUser has to have exactly one customer.
     * @param customer-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setCustomer(Customer customer) throws InvalidArgumentException {
        if(customer == null)
            throw new InvalidArgumentException(new String[]{"WebUser must be related to one customer"});
        if(this.customer != null){
            throw new InvalidArgumentException(new String[]{"Can't add costumer because web user has one"});
        }
        if(customer.getWebUser() != this ){
            if(customer.getWebUser() == null){
                customer.setWebUser(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add web user because customer has one"});
            }
        }
        this.customer = customer;
    }

    /**
     * WebUser has to have exactly one shoppingCart.
     * @param shoppingCart-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setShoppingCart(ShoppingCart shoppingCart) throws InvalidArgumentException {
        if(this.shoppingCart != null){
            throw new InvalidArgumentException(new String[]{"Can't add shopping cart because web user has one"});
        }
        if(shoppingCart.getWebUser() != this ){
            if(shoppingCart.getWebUser() == null){
                this.shoppingCart = shoppingCart;
                shoppingCart.setWebUser(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add shopping cart because it has web user"});
            }
        }
        this.shoppingCart = shoppingCart;
    }


    /**
     * This function is used to change the user state. For example if the user logged out
     * @param state - Enum type. Options: New, Active, Blocked, Banned
     */
    public void setState(UserState state) {
        this.state = state;
    }

    public void Delete() throws InvalidArgumentException{
        ShoppingCart sh = this.shoppingCart;
        Customer cust = this.customer;

        if(this.customer!=null)
            this.customer = null;
        if(this.shoppingCart!=null)
            this.shoppingCart = null;

        sh.Delete();
        cust.setWebUser(null);
    }

    public String toString(){
        String print="";
        print="WebUser ID: "+this.login_id+"\nPassword: "+this.password+
                " state: "+this.state+"\nconnected to:";
        if(this.customer!=null) //should have one the check is for us to know if its not working
            print+="customer";
        if(this.shoppingCart!=null){
            print+=", ShoppingCart";
        }
        return print;
    }
}
