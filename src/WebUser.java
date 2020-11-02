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

    /**
     * WebUser has to have exactly one customer.
     * @param customer-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setCustomer(Customer customer) throws InvalidArgumentException {
        if(customer == null)
            throw new InvalidArgumentException(new String[]{"WebUser must be related to one customer"});
        this.customer = customer;
    }

    /**
     * WebUser has to have exactly one shoppingCart.
     * @param shoppingCart-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart =shoppingCart;
    }

    /**
     * This function is used to change the user state. For example if the user logged out
     * @param state - Enum type. Options: New, Active, Blocked, Banned
     */
    public void setState(UserState state) {
        this.state = state;
    }
}
