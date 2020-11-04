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

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return Email;
    }

    public Account getAccount() {
        return account;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    /**
     * Customer has to have exactly one account (will be set after account is created).
     * @param account
     * @throws InvalidArgumentException
     */
    public void setAccount(Account account) throws InvalidArgumentException {
        if(account == null)
            throw new InvalidArgumentException(new String[]{"Customer must be related to one account"});
        if(this.account != null){
            throw new InvalidArgumentException(new String[]{"Can't add account because customer has one"});
        }
        if(account.getCustomer() != this ){
            if(account.getCustomer() == null){
                account.setCustomer(this);
            }
            else{
                throw new InvalidArgumentException(new String[]{"Can't add account because account has customer"});
            }
        }
        this.account = account;
    }

    public void setWebUser(WebUser webUser){
        this.webUser = webUser;
    }

    public String toString(){
        String print="";
        print="Customer ID: "+this.id+"\nAddress: "+this.address+
                " Phone Number: "+this.phone+" Email: "+this.Email;
        return print;
    }
}
