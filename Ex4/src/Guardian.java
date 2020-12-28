import java.util.ArrayList;
import java.util.HashMap;

public class Guardian {
    CreditCard creditCard;
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    int Weight;
    int Age;
    ArrayList<Child>children = new ArrayList<Child>();
    Account account;
    ParkSystem parkSystem;

    public Guardian(CreditCard creditCard, ParkSystem parkSystem) {
        this.creditCard = creditCard;
        setParkSystem(parkSystem);
    }

    //get
    public CreditCard getCreditCard() { return creditCard; }
    public HashMap<String, String> getID_Password() { return ID_Password;}
    public Account getAccount() { return account; }
    public ParkSystem getParkSystem() { return parkSystem; }
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
    public void setParkSystem(ParkSystem parkSystem) {
        if (parkSystem==null || this.parkSystem!=null)
            return;
        if (!parkSystem.getGuardians().contains(this)){
            this.parkSystem = parkSystem;
            parkSystem.addGuardians(this);
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

    public int GetChildrenSize(){
        return this.children.size();
    }
    public EBracelet ReturnBracelet(Child child) {
        return child.geteBracelet();
    }

    public void DeleteChild(Child child) {
        if(child == null)
            return;
        if(this.children.contains(child)) {
            this.children.remove(child);
            child.Delete();
        }
    }

    public void CloseAccount() {
        this.account.Delete();
    }

    public void Delete() {
        ID_Password = null;
        children = null;
        account = null;
        parkSystem = null;
    }
}
