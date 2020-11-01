import com.sun.javaws.exceptions.InvalidArgumentException;

public class Customer  {
    private String id;
    private String address;
    private String phone;
    private String Email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, String address, String phone, String email,Account account)throws InvalidArgumentException {
        this.id = id;
        this.address = address;
        this.phone = phone;
        Email = email;
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

    public void setWebUser(WebUser webUser){
        this.webUser = webUser;
    }
}
