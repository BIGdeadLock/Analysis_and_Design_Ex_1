import java.util.HashSet;

public class Group implements  ITestable{
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
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
        // TODO: Check with the team if the constraint is for type or
        // instance
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
        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        for (Group g: model.GroupAllInstances()){
            if (g == null)
                return true;
            if(!g.checkConstraints())
                return false;
        }
        return true;    }
}
