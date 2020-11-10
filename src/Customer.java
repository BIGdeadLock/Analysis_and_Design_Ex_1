
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
    public Customer(String id, String address, String phone, String email,Account account, WebUser webuser) throws
            NullPointerException, UnknownError{
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.Email = email;
        setWebUser(webuser);
        setAccount(account);
    }


    public Customer(String id, String address, String phone, String email,String idAccount, String billing_address
    ,int balance, boolean isPremium, WebUser webuser,int cartid) throws
            NullPointerException, UnknownError{
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.Email = email;
        setWebUser(webuser);
        if(isPremium)
            this.account = new PremiumAccount(idAccount,billing_address,balance,this,cartid);
        else
            this.account = new Account(idAccount,billing_address,balance,this,cartid);
    }

    public Customer(String id, String address, String phone, String email, Account account) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        Email = email;
        this.account = account;
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
     * @throws NullPointerException
     */
    public void setAccount(Account account) throws NullPointerException,UnknownError {
        if(account == null)
            throw new NullPointerException("Customer must be related to one account");
        if(this.account != null){
            throw new UnknownError("Can't add account because customer has one");
        }
        if(account.getCustomer() != this ){
            if(account.getCustomer() == null){
                this.account = account;
                account.setCustomer(this);
            }
            else{
                throw new UnknownError("Can't add account because account has customer");
            }
        }
        this.account = account;
    }

    public void setWebUser(WebUser webUser) throws UnknownError {
        if (webUser == null){ //on delete --> reset the webuser to null
            this.webUser = null;
            return;
        }
        if(this.webUser != null){
            throw new UnknownError("Can't add web user because customer has one");
        }
        if(webUser.getCustomer() != this ){
            if(webUser.getCustomer() == null){
                this.webUser = webUser;
                webUser.setCustomer(this);
            }
            else{
                throw new UnknownError("Can't add web user because it has another customer");
            }
        }
        this.webUser = webUser;
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete()  {
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

        if (wb != null)
            wb.Delete();

        if (acc != null)
            acc.Delete();

    }

    public String toString(){
        String print="";
        print="Customer ID: "+this.id+"\nAddress: "+this.address+
                " Phone Number: "+this.phone+" Email: "+this.Email+"\nconnected to:";
        if(this.account!=null) {//should have one the check is for us to know if its not working
            if (this.account.getClass() == PremiumAccount.class)
                print +="PremiumAccount";
            else
                print += "Account";
        }
        if(this.webUser!=null)
            print+=", Webuser";

        return print;
    }
}
