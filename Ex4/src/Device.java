import java.util.ArrayList;

public class Device {
    String name;
    Double minAge;
    Double minHeight;
    Double minWeight;
    boolean open;
    Double price;

    //Links
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
        this.minAge = 0.0;
        this.minHeight = 0.0;
        this.minWeight = 0.0;
        this.open = true;
        this.price=0.0;

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
        if (eBracelet == null || eBracelets.contains(eBracelet))
            return;
        this.eBracelets.add(eBracelet);
        eBracelet.addDevice(this);
    }

    public boolean checkExtreme() {
        try {
            ExtremeDevice DEV = (ExtremeDevice) this;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Double Delete(){
        Main.systemObjects.remove(this);
        for(EBracelet e:eBracelets){
            e.deleteDevice(this);
        }
        return this.price;

    }
}
