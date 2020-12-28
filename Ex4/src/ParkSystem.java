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
        this.creditCardCompany = new CreditCardCompany();
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
    public void setMap(Map map) {
        if (map==null || this.map!=null)
            return;
        if (map.getParkSystem()!=this)
            if (map.getParkSystem() == null) {
                this.map= map;
                map.setParkSystem(this);
            }
    }
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

    // Use Case 4

    /**
     * The function is used by Use 4.
     * Action 1 in Use Case 4.
     * @param user - Guardian | The guardian that requested to add the ride
     * @param ticket - ETicket | the ticket to add the new ride to
     * @param rideName - String | The name of the device
     */
    public void addDeviceToTicket(Guardian user, ETicket ticket, String rideName){
        Double ridePrice = 0.0;
        /**
         * Find the ride from the list of allowed rides for the child.
         * Get the price of the ride.
         * Validate that the user can pay for it (MaxAmount).
         * If yes add the new ride to the list of rides.
         */

        // TODO: Check what it means to add an entrance: add a new device or add to the
        // status in the guardian
        for (Device ride :ticket.getDevicesAllowed()) {
            if (ride.getName() == rideName){

            }

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
        double child_total_payment = eticket.getPaymentByEntries();
        eticket.Delete();
        return child_total_payment;
    }

    private boolean checkUserAccountLimit(Guardian user, double priceToAdd) {
        Account userAccount = user.getAccount();
        double newAmount = userAccount.getTotalAmount() + priceToAdd;
        if (newAmount > userAccount.getMaxAmount())
            return false;

        return true;
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
