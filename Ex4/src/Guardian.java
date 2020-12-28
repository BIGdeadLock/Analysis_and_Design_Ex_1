import java.util.ArrayList;
import java.util.HashMap;

public class Guardian {
    CreditCard creditCard;
    HashMap<Integer, Integer> ID_Password = new HashMap<Integer, Integer>();
    int Weight;
    int Age;
    HashMap<String, Child> childID_Child = new HashMap<String, Child>();
    Account account;
    ParkSystem parkSystem;

    public Guardian(CreditCard creditCard, ParkSystem parkSystem) {
        this.creditCard = creditCard;
        setParkSystem(parkSystem);
    }

    public Guardian(ParkSystem parkSystem) {
        setParkSystem(parkSystem);
    }

    //get
    public CreditCard getCreditCard() { return creditCard; }
    public HashMap<Integer, Integer> getID_Password() { return ID_Password;}
    public Account getAccount() { return account; }
    public ParkSystem getParkSystem() { return parkSystem; }

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

    public void addUserAndPassword(int username, int password){
        this.ID_Password.put(username,password);
    }

    public Child getChildByID(String childID){
        if(childID == null)
            return null;
        int SystemId = Integer.parseInt(childID);
        if(this.childID_Child.containsKey(SystemId))
            return this.childID_Child.get(SystemId);
        return null;

    }
    public void addChild(Child child) {
        if (child == null)
            return;
        String childID = child.getID();
        if (!(childID_Child.containsKey(childID))) {
            if (child.getGuardian() != this) {
                if (child.getGuardian() != null) {
                    return;
                } else {
                    this.childID_Child.put(childID, child);
                    child.setGuardian(this);
                }
            }
        }
    }

    public int GetChildrenSize(){
        return this.childID_Child.size();
    }
    public EBracelet ReturnBracelet(Child child) {
        return child.geteBracelet();
    }

    public void DeleteChild(Child child) {
        if(child == null)
            return;
        String childID= child.getID();
        if(this.childID_Child.containsKey(childID)) {
            this.childID_Child.remove(childID);
            child.Delete();
        }
    }

    public void Delete() {
        this.account.Delete();
        account = null;
        ID_Password = null;
        childID_Child = null;
        parkSystem = null;
    }
    public void CreateChild(String ID,int Age){
        Child child=new Child(ID,Age,this);
        this.addChild(child);
    }

    public void UpdateHeightAndWeight(String ID,double Height, double Weight){
        for (String childID: childID_Child.keySet())
            if (childID.equals(ID)) {
                Child childTOUpdate = childID_Child.get(childID);
                childTOUpdate.setHeight(Height);
                childTOUpdate.setWeight(Weight);
                childTOUpdate.geteTicket().setChildHeight(Height);
                childTOUpdate.geteTicket().setChildWeight(Weight);
            }
    }
}
