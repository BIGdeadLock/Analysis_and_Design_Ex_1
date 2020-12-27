public class Device {
    String name;
    Double minAge;
    Double minHeight;
    Double minWeight;
    boolean open;

    public Device(String name,Double minAge,Double minHeight,Double minWight, boolean open){
        this.name = name;
        this.minAge = minAge;
        this.minHeight = minHeight;
        this.minWeight = minWight;
        this.open = open;
    }
    public Device(String name){
        this.name = name;
    }

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

    public boolean isOpen() {
        return open;
    }
}
