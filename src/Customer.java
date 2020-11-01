import com.sun.javaws.exceptions.InvalidArgumentException;

public class Customer  {
    private String id;
    private String address;
    private String phone;
    private String Email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, String address, String phone, String email)throws InvalidArgumentException {
        this.id = id;
        this.address = address;
        this.phone = phone;
        Email = email;
    }

    /*public Customer(String customer_id, String customer_address, String customer_phone_number, String customer_email) {
    }*/

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
