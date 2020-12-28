import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Date;

public class ParkSystem {
    int ChildSystemID =1;
    int Passwords = 1;
    HashMap<Integer, Child> childUsers = new HashMap<Integer, Child>();
    HashMap<Integer, Integer> ID_Password = new HashMap<Integer, Integer>();
    HashMap<String, Integer> childID_systemID = new HashMap<String, Integer>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>(); // TODO: check if to join as child: ticket, bracelet
    HashMap<String, ETicket> childID_eTicket = new HashMap<String, ETicket>();
    ParkMap parkMap;
    CreditCardCompany creditCardCompany = new CreditCardCompany();

    public ParkSystem(ParkMap parkMap) {
        this.parkMap = parkMap;
        this.creditCardCompany = new CreditCardCompany();
        Main.systemObjects.add(creditCardCompany);
    }

    //get
    public HashMap<Integer, Child> getChildUsers() { return childUsers; }
    public HashMap<Integer, Integer> getID_Password() {return ID_Password; }
    public ArrayList<Guardian> getGuardians() { return this.guardians; }
    public ArrayList<EBracelet> geteBracelets() { return eBracelets; }
    public Collection<ETicket> geteTickets() { return childID_eTicket.values(); }
    public ParkMap getMap() { return parkMap; }

    //set
    public void setChildUsers(HashMap<Integer, Child> childUsers) { this.childUsers = childUsers;}
    public void setID_Password(HashMap<Integer, Integer> ID_Password) { this.ID_Password = ID_Password; }
    public void setMap(ParkMap parkMap) {
        if (parkMap ==null || this.parkMap !=null)
            return;
        if (parkMap.getParkSystem()!=this)
            if (parkMap.getParkSystem() == null) {
                this.parkMap = parkMap;
                parkMap.setParkSystem(this);
            }
        else
            this.parkMap=parkMap;
    }
    public void addGuardians(Guardian guardian) {
        if (guardian == null)
            return;
        if (!(this.guardians.contains(guardian))) {
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
        int SystemId = childID_systemID.get(childID);
        int SystemPass = Integer.parseInt(childPASS);

        // check if password is correct
        if (ID_Password.containsKey(SystemId)){
            if(ID_Password.get(SystemId) == SystemPass)
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
    //Use Case 1
    /**
     * The function is used by Use 1.
     * Action 3 in Use Case 1.
     * @param ID - String | childs ID
     * @param age - int | childs age
     */
    public boolean checkIfDetailsValid(String ID, String age){
        if (childID_eTicket.containsKey(ID))
            return false;
        try {
            double d = Double.parseDouble(age);
        }
        catch (NumberFormatException nfe) {
        return false;
    }
        return true;
    }
    /**
     * The function is used by Use 1.
     * Action 6 in Use Case 1.
     * @param card - CreditCard | card to check for approve
     */
    public boolean companyApproval(CreditCard card){
        if(card==null)
            return false;
        return creditCardCompany.ApproveCard(card);
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

        for (Device ride :ticket.getDevicesAllowed()) {
            if (ride.getName() == rideName){
                // Action 2 in UC4
                if (checkUserAccountLimit(user, ride.getPrice())){
                    // TODO: Change Status to a list of devices
                    // Actions 3,4 in UC4
                    ticket.getDevicesAllowed().add(ride);
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
                     // Actions 3,4 in UC4
                    ticket.getDevicesAllowed().remove(ride);
                    user.account.Orders.remove(ride);
                    user.account.totalAmount-=ride.getPrice();
                }
            }
        }
    }

    public void CancelRegistration(Guardian guardian, Child child){
        if(child == null || guardian == null)
            return;
        int child_id = child.getSystemID();
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
            guardian.Delete();
        }
    }

    public ETicket CreateETicket(Child child){
        if (child==null)
            return null;
        Date today=new Date();
        ETicket eTicket=new ETicket(today,this,child);
        this.childID_eTicket.put(child.getID(),eTicket);
        return eTicket;
    }
    public EBracelet CreateEBracelet(Child child){
        if (child==null)
            return null;
        EBracelet eBracelet=new EBracelet("Entrance",child,this,child.geteTicket());
        this.eBracelets.add(eBracelet);
        return eBracelet;
    }
    public Account CreateAccount(Guardian guardian,double maxAmount){
        if (guardian==null)
            return null;
        Account account = new Account(guardian,maxAmount);
        return account;
    }
    public void AddChildUser(Child child){
        if(child==null)
            return;
        this.childUsers.put(ChildSystemID,child);
        child.getGuardian().addUserAndPassword(this.ChildSystemID, this.Passwords);
        child.setSystemID(this.ChildSystemID);
        this.childID_systemID.put(child.getID(),this.ChildSystemID);
        this.ID_Password.put(this.ChildSystemID,this.Passwords);
        this.ChildSystemID++;
        this.Passwords++;
    }

    public int getChildPassword(int childID){
        return this.ID_Password.get(childID);
    }


}
