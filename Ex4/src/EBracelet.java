public class EBracelet {
    String currentLocation;

    //Links
    Child child;
    ParkSystem parkSystem;
    ETicket eTicket;

    public EBracelet(String currentLocation, Child child, ParkSystem parkSystem, ETicket eTicket) {
        this.currentLocation = currentLocation;
        setChild(child);
        setSystem(parkSystem);
        seteTicket(eTicket);
    }

    //GET
    public String getCurrentLocation() { return currentLocation; }
    public Child getChild() { return child; }
    public ParkSystem getparkSystem() { return parkSystem; }
    public ETicket geteTicket() { return eTicket; }

    //SET
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
    public void setChild(Child child) {
        if (child==null || this.child!=null)
            return;
        if (child.geteBracelet()!=this)
            if (child.geteBracelet() == null) {
                this.child = child;
                child.seteBracelet(this);
            }
    }
    public void setSystem(ParkSystem parkSystem) {
        if (parkSystem ==null || this.parkSystem !=null)
            return;
        parkSystem.addeBracelets(this);
        this.parkSystem = parkSystem;
    }
    public void seteTicket(ETicket eTicket) {
        if (eTicket==null || this.eTicket!=null)
            return;
        if (eTicket.geteBracelet()!=this)
            if (eTicket.geteBracelet() == null) {
                this.eTicket= eTicket;
                eTicket.seteBracelet(this);
            }
    }

    public void Delete() {
        this.child = null;
        if(this.eTicket != null)
            eTicket= null;

    }

    //TODO: finish the f****n function
    public void addDevice(Device device) {
    }
}
