import java.util.ArrayList;

public class Device {
    String name;
    Double minAge;
    Double minHeight;
    Double minWeight;
    boolean open;
    Double price;
    ArrayList<EBracelet> eBracelets = new ArrayList<EBracelet>();

    public Device(String name,Double minAge,Double minHeight,Double minWight,Double price, boolean open){
        this.name = name;
        this.minAge = minAge;
        this.minHeight = minHeight;
        this.minWeight = minWight;
        this.open = open;
        this.price=price;
    }
    public Device(String name){
        this.name = name;
    }

    //GET
    public String getName() {
        return name;
    }
    public Double getMinAge() {
        return minAge;
    }
    public Double getMinHeight() {
        return minHeight;
    }
    public Double getMinWeight() {
        return minWeight;
    }
    public Double getPrice() { return price; }

    //SET
    public void setName(String name) { this.name = name; }
    public void setMinAge(Double minAge) { this.minAge = minAge; }
    public void setMinHeight(Double minHeight) { this.minHeight = minHeight; }
    public void setMinWeight(Double minWeight) { this.minWeight = minWeight; }
    public void setOpen(boolean open) { this.open = open; }
    public void setPrice(Double price) { this.price = price; }


    public void addEBracelet(EBracelet eBracelet){
        if (eBracelet == null)
            return;
        if (!(eBracelets.contains(eBracelet))) {
            if (eTicket.getDevice() != this) {
                if (ebracelet.getSystem() != null) {
                    return;
                } else {
                    this.eBracelets.add(ebracelet);
                    ebracelet.setSystem(this);
                }
            }
        }
    }

    public boolean isOpen() {
        return open;
    }
}
