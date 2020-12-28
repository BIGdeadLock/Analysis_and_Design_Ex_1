import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ParkSystem {
    HashMap<String, Child> childUsers = new HashMap<String, Child>();
    HashMap<String, String> ID_Password = new HashMap<String, String>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>(); // TODO: check if to join as child: ticket, bracelet
    HashMap<String, ETicket> childID_eTicket = new HashMap<String, ETicket>();
    ParkMap parkMap;
    CreditCardCompany creditCardCompany = new CreditCardCompany();

    public ParkSystem(ParkMap parkMap) {
        this.parkMap = parkMap;
        this.creditCardCompany = new CreditCardCompany();
    }

    //get
    public HashMap<String, Child> getChildUsers() { return childUsers; }
    public HashMap<String, String> getID_Password() {return ID_Password; }
    public ArrayList<Guardian> getGuardians() { return guardians; }
    public ArrayList<EBracelet> geteBracelets() { return eBracelets; }
    public Collection<ETicket> geteTickets() { return childID_eTicket.values(); }
    public ParkMap getMap() { return parkMap; }

    //set
    public void setChildUsers(HashMap<String, Child> childUsers) { this.childUsers = childUsers;}
    public void setID_Password(HashMap<String, String> ID_Password) { this.ID_Password = ID_Password; }
    public void setMap(ParkMap parkMap) {
        if (parkMap ==null || this.parkMap !=null)
            return;
        if (parkMap.getParkSystem()!=this)
            if (parkMap.getParkSystem() == null) {
                this.parkMap = parkMap;
                parkMap.setParkSystem(this);
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

    public boolean validateGuardian(String childID, String childPASS){
        if(childID == null || childPASS == null)
            return false;
        // check if password is correct
        if (ID_Password.containsKey(childID)){
            if(ID_Password.get(childID).equals(childPASS))
                return true;
        }
        return false;
    }

    public void ExitPark(Guardian guardian, Child child){
        if(guardian == null || child == null)
            return;
        EBracelet eBracelet = guardian.ReturnBracelet(child);
        CancelRegistration(guardian, child);
        MakePayment(guardian, child);

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
                // Action 2 in UC4
                if (checkUserAccountLimit(user, ride.getPrice())){
                    // TODO: Change Status to a list of devices
                    // Actions 3,4 in UC4
                    ticket.getStatus().add(ride);
                    user.account.Orders.add(ride);
                    user.account.totalAmount+=ride.getPrice();
                }
            }
        }
    }

    /**
     * The function is used by Use 4.
     * Action 1 in Use Case 4.
     * @param user - Guardian | The guardian that requested to add the ride
     * @param ticket - ETicket | the ticket to add the new ride to
     * @param rideName - String | The name of the device
     */
    public void removeDeviceFromTicket(Guardian user, ETicket ticket, String rideName){
        /**
         * Find the ride from the list of allowed rides for the child.
         * Get the price of the ride.
         * remove the device from the account and update the total amount
         */
        for (Device ride :ticket.getDevicesAllowed()) {
            if (ride.getName() == rideName){
                // Action 2 in UC4
                if (checkUserAccountLimit(user, ride.getPrice())){
                    // TODO: Change Status to a list of devices
                    // Actions 3,4 in UC4
                    ticket.getStatus().remove(ride);
                    user.account.Orders.remove(ride);
                    user.account.totalAmount-=ride.getPrice();
                }
            }
        }
    }

    public void CancelRegistration(Guardian guardian, Child child){
        if(child == null || guardian == null)
            return;
        String child_id = child.getID();
        if(!ID_Password.containsKey(child_id))
            return;
        ID_Password.remove(child_id);
        guardian.DeleteChild(child);
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
            guardian.CloseAccount();
            guardian.Delete();
        }
    }

}
