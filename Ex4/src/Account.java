import java.util.ArrayList;

public class Account {
    ArrayList<Device> Orders = new ArrayList<Device>();
    ArrayList<Device> Refunds = new ArrayList<Device>();
    double MaxAmount;
    double totalAmount;
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
    public double getTotalAmount() {
        return totalAmount;
    }

    //SET
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

    public void Delete(){
        this.Orders = null;
        this.Refunds = null;
        this.guardian = null;
    }
}
