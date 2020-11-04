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
                shoppingCart.setWebUser(this); //TODO: need to check if it worked fine?
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

    public String toString(){
        String print="";
        print="WebUser ID: "+this.login_id+"\nPassword: "+this.password+
                " state: "+this.state;
        return print;
    }
}
