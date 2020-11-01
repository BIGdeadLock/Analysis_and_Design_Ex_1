import com.sun.javaws.exceptions.InvalidArgumentException;

public class Customer  {
    private String id;
    private Address adress;
    private String phone;
    private String Email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, Adress adress, String phone, String email,Account account,WebUser webUser) {
        this.id = id;
        this.adress = adress;
        this.phone = phone;
        Email = email;
        this.webUser = webUser;
        setAccount(account);

    }

    /**
     * Customer has to have exactly one account.
     * @param account
     * @throws InvalidArgumentException
     */
    public void setAccount(Account account) throws InvalidArgumentException {
        if(account == null)
            throw new InvalidArgumentException(new String[]{"Customer must be related to one account"});
        this.account = account;
    }
}
