import java.util.HashSet;

public class Group implements  ITestable{
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
    }

    public boolean Constraint1() {
        HashSet<String> City = new HashSet<>();
        for (Hotel hotel : hotels) {
            if (City.contains(hotel.getCity()))
                return false;
            City.add(hotel.getCity());
        }
        return true;
    }

    public void addHotelToGroup(Hotel hotel){
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public boolean checkConstraints() {
        // Constraint 4
        if (this.hotels.size() != 0){
            for (Hotel h1: this.hotels) {
                for (Hotel h2: this.hotels) {
                    if (h1.getServices().size()!= 0 && h2.getServices().size() != 0){
                        if (!h1.getServices().keySet().containsAll(h2.getServices().keySet()))
                            return false;
                    }
                }
            }
        };

        if(!Constraint1())
            return false;

        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
}
