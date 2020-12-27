import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ParkSystem {
    HashMap<String, Child> childUsers = new HashMap<String, Child>();
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>();
    HashMap<String, ETicket> childID_eTicket = new HashMap<String, ETicket>();
    Map map;

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
                if (ebracelet.getparkSystem() != null) {
                    return;
                } else {
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
        guardian.DeleteChild(child);
    }


    public void removeDeviceFromTicket(String rideName){

    }

    private void CalculatePayment(Guardian guardian, Child child){
        ETicket eticket = childID_eTicket.get(child.getID());


    }

    private boolean checkUserAccountLimit(Double priceToAdd){
        return true;
    }
}
