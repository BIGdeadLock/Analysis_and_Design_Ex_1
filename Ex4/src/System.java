import java.util.ArrayList;
import java.util.HashMap;

public class System {
    HashMap<String, Child> childUsers = new HashMap<String, Child>();
    HashMap<String, String> ID_Password = new HashMap<String, String>();

    ArrayList<ETicket> tickets = new ArrayList<ETicket>();
    ArrayList<EBracelet> bracelets = new ArrayList<EBracelet>();
    ArrayList<Guardian> users = new ArrayList<Guardian>();

    public void addToTicket(String rideName){
        Double ridePrice = 0.0;
        for(ETicket eticket: tickets){
            for(Device device: eticket.getDevicesAllowed())
        }
    }

    public void removeToTicket(String rideName){

    }

    private boolean checkUserAccountLimit(Double priceToAdd){

    }
}
