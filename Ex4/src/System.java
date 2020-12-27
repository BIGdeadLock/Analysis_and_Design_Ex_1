import com.sun.jndi.toolkit.url.GenericURLDirContext;
import sun.applet.resources.MsgAppletViewer;

import java.util.ArrayList;
import java.util.HashMap;

public class System {
    HashMap<String, Child> childUsers = new HashMap<String, Child>();
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>();
    ArrayList<ETicket> eTickets = new ArrayList<>();
    Map map;

    public System(Map map) {
        this.map = map;
    }

    //get
    public HashMap<String, Child> getChildUsers() { return childUsers; }
    public HashMap<String, String> getID_Password() {return ID_Password; }
    public ArrayList<Guardian> getGuardians() { return guardians; }
    public ArrayList<EBracelet> geteBracelets() { return eBracelets; }
    public ArrayList<ETicket> geteTickets() { return eTickets; }
    public Map getMap() { return map; }

    //set
    public void setChildUsers(HashMap<String, Child> childUsers) { this.childUsers = childUsers;}
    public void setID_Password(HashMap<String, String> ID_Password) { this.ID_Password = ID_Password; }
    public void setMap(Map map) { this.map = map;}
    public void addGuardians(Guardian guardian) {
        if (guardian == null)
            return;
        if (!(guardians.contains(guardian))) {
            if (guardian.getSystem() != this) {
                if (guardian.getSystem() != null) {
                    return;
                } else {
                    this.guardians.add(guardian);
                    guardian.setSystem(this);
                }
            }
        }
    }
    public void addeBracelets(EBracelet ebracelet) {
        if (ebracelet == null)
            return;
        if (!(eBracelets.contains(ebracelet))) {
            if (ebracelet.getSystem() != this) {
                if (ebracelet.getSystem() != null) {
                    return;
                } else {
                    this.eBracelets.add(ebracelet);
                    ebracelet.setSystem(this);
                }
            }
        }
    }
    public void addeTickets(ETicket eTicket) {
        if (eTicket == null)
            return;
        if (!(eTickets.contains(eTicket))) {
            if (eTicket.getSystem() != this) {
                if (eTicket.getSystem() != null) {
                    return;
                } else {
                    this.eTickets.add(eTicket);
                    eTicket.setSystem(this);
                }
            }
        }
    }


    public void addToTicket(String rideName){
        Double ridePrice = 0.0;
        for(ETicket eticket: eTickets){
            for(Device device: eticket.getDevicesAllowed())
        }
    }

    public void removeToTicket(String rideName){

    }

    private boolean checkUserAccountLimit(Double priceToAdd){

    }
}
