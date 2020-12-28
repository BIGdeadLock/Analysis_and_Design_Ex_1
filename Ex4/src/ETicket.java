import java.util.ArrayList;
import java.util.Date;

public class ETicket {
    Date Entrance;
    Double timeLeft;
    ArrayList<Device> devicesAllowed = new ArrayList<Device>();
    Double childHeight;
    Double childWeight;
    Child child;

    //Links
    EBracelet eBracelet;
    ParkSystem parkSystem;

    public ETicket(Date entrance, Double timeLeft, int status, ParkSystem parkSystem, Child child) {
        Entrance = entrance;
        this.timeLeft = timeLeft;
        this.setChild(child);
        this.setparkSystem(parkSystem);
    }

    public ETicket(Date entrance, ParkSystem parkSystem, Child child) { //timeleft +status optional
        Entrance = entrance;
        this.setChild(child);
        this.setparkSystem(parkSystem);
    }

    //GET
    public Date getEntrance() { return Entrance;}
    public Double getTimeLeft() { return timeLeft; }
    public ArrayList<Device> getDevicesAllowed() { return devicesAllowed; }
    public Double getChildHeight() { return childHeight; }
    public Double getChildWeight() { return childWeight; }
    public EBracelet geteBracelet() { return eBracelet; }
    public ParkSystem getparkSystem() { return parkSystem; }
    public Child getChild() { return child; }

    //SET
    public void setEntrance(Date entrance) { Entrance = entrance; }
    public void setTimeLeft(Double timeLeft) { this.timeLeft = timeLeft; }
    public void setChildWeight(Double childWeight) { this.childWeight = childWeight;}
    public void setChildHeight(Double childHeight) { this.childHeight = childHeight;}
    public void addRide(Device device){
        devicesAllowed.add(device);
    }
    public void setparkSystem(ParkSystem parkSystem) {
        if (parkSystem==null || this.parkSystem!=null)
            return;
        if (!parkSystem.geteTickets().contains(this)){
            this.parkSystem = parkSystem;
            parkSystem.addeTickets(child.ID,this);
        }
    }
    public void seteBracelet(EBracelet eBracelet) {
        if (eBracelet==null || this.eBracelet!=null)
            return;
        if (eBracelet.geteTicket()!=this)
            if (eBracelet.geteTicket() == null) {
                this.eBracelet= eBracelet;
                eBracelet.seteTicket(this);
            }
    }
    public void setChild(Child child) {
        if (child==null || this.child!=null)
            return;
        if (child.geteTicket()!=this)
            if (child.geteTicket() == null) {
                this.child= child;
                child.seteTicket(this);
            }
    }

    public void removeRide(String name){
        int flag = 0;
        for (Device dev : devicesAllowed) {
            if (dev.getName().equals(name.toLowerCase())){
                devicesAllowed.remove(dev);
                flag = 1;
                System.out.println("the " + name.toLowerCase() + " device was removed successfully");
            }
        }
        if (flag == 0){
            System.out.println("the " + name.toLowerCase() + " device is not on the list");
        }

    }

    public Double getPaymentByEntries(){
        Double total_price =0.0;
        for(Device device: devicesAllowed){
            total_price+=device.Delete();
        }
        return total_price;
    }


    public void Delete() {
        //Devices were deleted during payment calculation
        this.eBracelet = null;
        this.parkSystem = null;
        Main.systemObjects.remove(this);
    }

    public String toString(){
        String myStr = "";
        myStr += "these are ticket's details:\n";
        myStr += "the date: "+ this.Entrance+"\n";
        myStr += "the time you have left: " + this.timeLeft + ":\n";
        myStr += "the devices you have entrances for:\n";
        for (Device dev:this.devicesAllowed){
            myStr += dev + "\n";
        }
        myStr += "The child is :" + this.child + "\n";
        myStr += "Child's Height :" + this.childHeight + "\n";
        myStr += "Child's Weight :" + this.childWeight + "\n";
        //myStr += "status :" + this.Status + "\n";
        return myStr;
    }
}
