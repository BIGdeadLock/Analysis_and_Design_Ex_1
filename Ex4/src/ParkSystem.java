import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Date;

public class ParkSystem {
    int ChildSystemID =1;
    int Passwords = 1;
    HashMap<Integer, Integer> ID_Password = new HashMap<Integer, Integer>();
    HashMap<String, Integer> childID_systemID = new HashMap<String, Integer>();
    ArrayList<Guardian> guardians = new ArrayList<>();
    ArrayList<EBracelet> eBracelets = new ArrayList<>();
    HashMap<String, ETicket> childID_eTicket = new HashMap<String, ETicket>();
    ParkMap parkMap;
    CreditCardCompany creditCardCompany;

    public ParkSystem(ParkMap parkMap) {
        this.parkMap = parkMap;
        this.creditCardCompany = new CreditCardCompany();
        Main.systemObjects.add(creditCardCompany);
    }

    //get
    public HashMap<Integer, Integer> getID_Password() {return ID_Password; }
    public ArrayList<Guardian> getGuardians() { return this.guardians; }
    public ArrayList<EBracelet> geteBracelets() { return eBracelets; }
    public Collection<ETicket> geteTickets() { return childID_eTicket.values(); }
    public ParkMap getMap() { return parkMap; }

    //set
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
            if (guardian.getParkSystem() == this) {
                this.guardians.add(guardian);
                guardian.setParkSystem(this);
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
        if(childID == null || childPASS == null || !childID_systemID.containsKey(childID))
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
        String childID = CancelRegistration(guardian, child);
        if(childID == null)
            return;
        MakePayment(guardian, childID);

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
     * @param deviceToAdd - Device | The name of the device
     */
    public void addDeviceToTicket(Guardian user, ETicket ticket, Device deviceToAdd){
        /**
         * Find the ride from the list of allowed rides for the child.
         * Get the price of the ride.
         * Validate that the user can pay for it (MaxAmount).
         * If yes add the new ride to the list of rides.
         */
        if (user == null || ticket == null || deviceToAdd == null) {
            System.out.println("There was a problem, can't add the device");
            return;
        }
        if (ticket.getDevicesAllowed().size() == 0){
            ticket.addRide(deviceToAdd);
            user.account.Orders.add(deviceToAdd);
            user.account.totalAmount+=deviceToAdd.getPrice();
            System.out.println("Updated the user account");
            System.out.println("the " + deviceToAdd.getName() + " device was added successfully\n");
        }
        else {
            if (checkUserAccountLimit(user, deviceToAdd.getPrice())) {
                    ticket.addRide(deviceToAdd);
                    user.account.Orders.add(deviceToAdd);
                    user.account.totalAmount += deviceToAdd.getPrice();
                    System.out.println("Updated the user account");
                    System.out.println("the " + deviceToAdd.getName() + " device was added successfully\n");
            }
            else
                    System.out.println("Device price is over the limit of the user's account");
        }

    }

    /**
     * The function is used by Use 4.
     * Action 1 in Use Case 4.
     * @param user - Guardian | The guardian that requested to add the ride
     * @param ticket - ETicket | the ticket to add the new ride to
     * @param deviceToAdd - String | The name of the device
     */
    public void removeDeviceFromTicket(Guardian user, ETicket ticket, Device deviceToAdd){
        /**
         * Find the ride from the list of allowed rides for the child.
         * Get the price of the ride.
         * remove the device from the account and update the total amount
         */
        if (user == null || ticket == null || deviceToAdd == null) {
            System.out.println("There was a problem, can't add the device");
            return;
        }

        ArrayList<Device> rides = ticket.getDevicesAllowed();
        if (rides == null || rides.size() == 0){
            System.out.println("No devices to remove from");
            return;
        }

        for (Device ride : ticket.getDevicesAllowed()) {
            if (ride.getName().toLowerCase().equals(deviceToAdd.getName().toLowerCase())) {
                    // Actions 3,4 in UC4
                    user.account.Orders.remove(ride);
                    if (user.account.totalAmount - ride.getPrice() <= 0)
                        user.account.totalAmount = 0;
                    else
                        user.account.totalAmount -= ride.getPrice();
                System.out.println("Updated the user account");
                System.out.println("the " + deviceToAdd.getName() + " device was removed successfully");
                break;
            }

        }
        ticket.removeRide(deviceToAdd.getName());


    }

    public String CancelRegistration(Guardian guardian, Child child){
        if(child == null || guardian == null)
            return null;
        EBracelet ebraceletToDelete = guardian.ReturnBracelet(child);
        eBracelets.remove(ebraceletToDelete);
        String childID = child.getID();
        guardian.DeleteChild(childID);
        return childID;
    }

    private Double CalculatePayment(Guardian guardian, String childID){
        ETicket eticket = childID_eTicket.get(childID);
        double child_total_payment = eticket.getPaymentByEntries();
        eticket.Delete();

        int child_id = this.childID_systemID.get(childID);
        if(!ID_Password.containsKey(child_id))
            return null;
        ID_Password.remove(child_id);
        return child_total_payment;
    }

    private boolean checkUserAccountLimit(Guardian user, double priceToAdd) {
        Account userAccount = user.getAccount();
        double newAmount = userAccount.getTotalAmount() + priceToAdd;
        if (newAmount > userAccount.getMaxAmount())
            return false;
        return true;
    }

    public void MakePayment(Guardian guardian, String childID){
        if(!guardians.contains(guardian))
            return;
        Double payment = CalculatePayment(guardian, childID);
        CreditCard creditCard = guardian.getCreditCard();
        creditCardCompany.ChargeCard(creditCard, payment);
        int childrenAmount = guardian.GetChildrenSize();
        if(childrenAmount == 0) {
            guardians.remove(guardian);
            guardian.Delete();
        }
        clearChlid(childID);
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

    public void clearChlid(String ChildID){
        if(!childID_systemID.containsKey(ChildID)) {
            if (childID_eTicket.containsKey(ChildID))
                childID_eTicket.remove(ChildID);
        }
        else {
            childID_eTicket.remove(ChildID);
            int sysmID=childID_systemID.get(ChildID);
            ID_Password.remove(sysmID);
            childID_systemID.remove(ChildID);
        }
    }


}
