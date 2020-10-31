public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public WebUser(String login_id, String password, UserState state) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
    }
}
