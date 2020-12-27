import java.util.ArrayList;
import java.util.HashMap;

public class Guardian {
    CreditCard creditCard;
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    Account account;
    System system;
    ArrayList<Child> children = new ArrayList<>();

    public Guardian(CreditCard creditCard, System system) {
        this.creditCard = creditCard;
        setSystem(system);
    }

    //get
    public CreditCard getCreditCard() { return creditCard; }
    public HashMap<String, String> getID_Password() { return ID_Password;}
    public Account getAccount() { return account; }
    public System getSystem() { return system; }
    public ArrayList<Child> getChildren() { return children;}

    //set
    public void setCreditCard(CreditCard creditCard) { this.creditCard = creditCard; }
    public void setAccount(Account account) {
        if (account==null || this.account!=null)
            return;
        if (account.getGuardian()!=this)
            if (account.getGuardian() == null) {
                this.account = account;
                account.setGuardian(this);
            }
    }
    public void setSystem(System system) {
        if (system==null || this.system!=null)
            return;
        if (!system.getGuardians().contains(this)){
            this.system = system;
            system.addGuardians(this);
        }
    }
    public void addChild(Child child) {
        if (child == null)
            return;
        if (!(children.contains(child))) {
            if (child.getGuardian() != this) {
                if (child.getGuardian() != null) {
                    return;
                } else {
                    this.children.add(child);
                    child.setGuardian(this);
                }
            }
        }
    }
}
