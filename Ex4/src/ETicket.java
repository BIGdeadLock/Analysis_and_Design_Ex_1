import java.util.ArrayList;
import java.util.Date;

public class ETicket {
    Date Entrace;
    Double timeLeft;
    ArrayList<Device> devicesAllowed = new ArrayList<Device>();
    String Status;
    Double childHeight;
    ArrayList<Device> extremeDevicesPermitted = new ArrayList<Device>();
    Double childWeight;


    public Date getEntrace() {
        return Entrace;
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
}
