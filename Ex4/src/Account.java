import java.util.ArrayList;

public class Account {
    ArrayList<Device> Orders = new ArrayList<Device>();
    ArrayList<Device> Refunds = new ArrayList<Device>();
    double MaxAmount;
    Guardian guardian;

    public Account(Guardian guardian,double maxamount) {
        this.MaxAmount=maxamount;
        setGuardian(guardian);
    }

    //GET
    public ArrayList<Device> getOrders() { return Orders; }
    public ArrayList<Device> getRefunds() { return Refunds; }
    public double getMaxAmount() { return MaxAmount; }
    public Guardian getGuardian() { return guardian; }

    //SET
    public void setOrders(ArrayList<Device> orders) { Orders = orders; }
    public void setRefunds(ArrayList<Device> refunds) { Refunds = refunds; }
    public void setMaxAmount(double maxAmount) { MaxAmount = maxAmount; }
    public void setGuardian(Guardian guardian) {
        if (guardian==null || this.guardian!=null)
            return;
        if (guardian.getAccount()!=this)
            if (guardian.getAccount() == null) {
                this.guardian = guardian;
                guardian.setAccount(this);
            }
    }
}
