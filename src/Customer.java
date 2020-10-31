public class Customer  {
    private String id;
    private Address adress;
    private String phone;
    private String Email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, Adress adress, String phone, String email) {
        this.id = id;
        this.adress = adress;
        this.phone = phone;
        Email = email;
    }
}
