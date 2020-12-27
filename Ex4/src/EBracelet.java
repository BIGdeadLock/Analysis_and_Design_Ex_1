public class EBracelet {
    String currentLocation;
    Child child;
    System system;
    ETicket eTicket;

    public EBracelet(String currentLocation, Child child, System system, ETicket eTicket) {
        this.currentLocation = currentLocation;
        setChild(child);
        setSystem(system);
        seteTicket(eTicket);
    }

    //GET
    public String getCurrentLocation() { return currentLocation; }
    public Child getChild() { return child; }
    public System getSystem() { return system; }
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
    public void setSystem(System system) { this.system = system; }
    public void seteTicket(ETicket eTicket) { this.eTicket = eTicket; }
}
