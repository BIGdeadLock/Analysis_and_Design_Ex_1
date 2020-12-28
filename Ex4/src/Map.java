public class Map {
    ParkSystem parkSystem;

    public Map(ParkSystem parkSystem) {
        this.setParkSystem(parkSystem);
    }

    //GET
    public ParkSystem getParkSystem() { return parkSystem; }

    //SET
    public void setParkSystem(ParkSystem parkSystem) {
        if (parkSystem==null || this.parkSystem!=null)
            return;
        if (parkSystem.getMap()!=this)
            if (parkSystem.getMap() == null) {
                this.parkSystem= parkSystem;
                parkSystem.setMap(this);
            }
    }
}
