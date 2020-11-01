import com.sun.javaws.exceptions.InvalidArgumentException;

public class Customer  {
    private String id;
    private String address;
    private String phone;
    private String Email;

    private Account account;
    private WebUser webUser;

    /**
     * create customer first and then add its account (avoid BIGDEADLOCK)
     */
    public Customer(String id, String address, String phone, String email){
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.Email = email;
    }

    /**
     * Customer has to have exactly one account (will be set after account is created).
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
