import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ParkSystem {
    HashMap<String, Child> childUsers = new HashMap<String, Child>();
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>(); // TODO: check if to join as child: ticket, bracelet
    HashMap<String, ETicket> childID_eTicket = new HashMap<String, ETicket>();
    Map map;
    CreditCardCompany creditCardCompany = new CreditCardCompany();
    public ParkSystem(Map map) {
        this.map = map;
    }

    //get
    public HashMap<String, Child> getChildUsers() { return childUsers; }
    public HashMap<String, String> getID_Password() {return ID_Password; }
    public ArrayList<Guardian> getGuardians() { return guardians; }
    public ArrayList<EBracelet> geteBracelets() { return eBracelets; }
    public Collection<ETicket> geteTickets() { return childID_eTicket.values(); }
    public Map getMap() { return map; }

    //set
    public void setChildUsers(HashMap<String, Child> childUsers) { this.childUsers = childUsers;}
    public void setID_Password(HashMap<String, String> ID_Password) { this.ID_Password = ID_Password; }
    public void setMap(Map map) { this.map = map;}
    public void addGuardians(Guardian guardian) {
        if (guardian == null)
            return;
        if (!(guardians.contains(guardian))) {
            if (guardian.getParkSystem() != this) {
                if (guardian.getParkSystem() != null) {
                    return;
                } else {
                    this.guardians.add(guardian);
                    guardian.setParkSystem(this);
                }
            }
        }
    }
    public void addeBracelets(EBracelet ebracelet) {
        if (ebracelet == null)
            return;
        if (!(eBracelets.contains(ebracelet))) {
            if (ebracelet.getparkSystem() != this) {
                if (ebracelet.getparkSystem() != null)
                    return;
                else {
                    this.eBracelets.add(ebracelet);
                    ebracelet.setSystem(this);
                }
            }
        }
    }
    public void addeTickets(String childID, ETicket eTicket) {
        if (eTicket == null)
            return;
        if (!(childID_eTicket.containsValue(eTicket))) {
            if (eTicket.getparkSystem() != this) {
                if (eTicket.getparkSystem() != null) {
                    return;
                } else {
                    this.childID_eTicket.put(childID, eTicket);
                    eTicket.setparkSystem(this);
                }
            }
        }
    }


    public void addDeviceToTicket(String rideName){
        Double ridePrice = 0.0;
        for(ETicket eticket: childID_eTicket.values()){
            for(Device device: eticket.getDevicesAllowed()){}
        }
    }

    public void CancelRegistration(Guardian guardian, Child child){
        if(child == null || guardian == null)
            return;
        String child_id = child.getID();
        if(!childUsers.containsKey(child_id))
            return;
        childUsers.remove(child_id);
        guardian.DeleteChild(child);
    }


    public void removeDeviceFromTicket(String rideName){

    }

    private Double CalculatePayment(Child child){
        ETicket eticket = childID_eTicket.get(child.getID());
        Double child_total_payment = eticket.getPaymentByEntries();
        eticket.Delete();
        return child_total_payment;
    }

    public void MakePayment(Guardian guardian, Child child){
        if(!guardians.contains(guardian))
            return;
        Double payment = CalculatePayment(child);
        CreditCard creditCard = guardian.getCreditCard();
        creditCardCompany.ChargeCard(creditCard, payment);
        if(guardian.GetChildrenSize() == 0) {
            guardians.remove(guardian);
            guardian.Delete();
        }
    }
    private boolean checkUserAccountLimit(Double priceToAdd){
        return true;
    }
}
