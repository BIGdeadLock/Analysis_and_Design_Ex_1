import java.util.ArrayList;
import java.util.Date;

public class ETicket {
    Date Entrance;
    Double timeLeft;
    ArrayList<Device> devicesAllowed = new ArrayList<Device>();
    String Status;
    Double childHeight;
    ArrayList<Device> extremeDevicesPermitted = new ArrayList<Device>();
    Double childWeight;

    //Links
    EBracelet eBracelet;
    ParkSystem parkSystem;

    public Date getEntrance() {
        return Entrance;
    }

    public Double getTimeLeft() {
        return timeLeft;
    }

    public ArrayList<Device> getDevicesAllowed() {
        return devicesAllowed;
    }

    public String getStatus() {
        return Status;
    }

    public Double getChildHeight() {
        return childHeight;
    }

    public ArrayList<Device> getExtremeDevicesPermitted() {
        return extremeDevicesPermitted;
    }

    public Double getChildWeight() {
        return childWeight;
    }

    public void addRide(Device device){
        devicesAllowed.add(device);
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

    }

    public EBracelet geteBracelet() {
        return eBracelet;
    }

    public void seteBracelet(EBracelet eBracelet) {
        if(eBracelet == null)
            return;;
        this.eBracelet = eBracelet;
    }

    public Double getPaymentByEntries(){
        Double total_price =0.0;
        for(Device device: devicesAllowed){
            total_price+=device.Delete();
        }
        return total_price;
    }

    public ParkSystem getparkSystem() {
        return parkSystem;
    }

    public void setparkSystem(ParkSystem parkSystem) {
        if(parkSystem == null)
            return;
        this.parkSystem = parkSystem;
    }

    public void Delete() {
        //Devices were deleted during payment calculation
        this.extremeDevicesPermitted = null;
        this.eBracelet = null;
        this.parkSystem = null;
    }
}
