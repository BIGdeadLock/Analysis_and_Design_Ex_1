import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking implements  ITestable{
    private Date date;
    private Room room;
    private ArrayList<HotelService> services;
    private Reservation reservation;
    private Review review;


    public Booking(Date a_date, Room a_room){
        date = a_date;
        room = a_room;
        services = new ArrayList<HotelService>();
    }

    public void addService(HotelService s){
        services.add(s);
    }

    public void addReview(Review a_review) {review  = a_review; }

    public void addReservation(Reservation r){
        reservation = r;
    }

    public void assignRoom(Room room){
        this.room = room;
    }


    // getters

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Review getReview() {
        return review;
    }


    @Override
    public boolean checkConstraints() {
        for(int i=0; i<this.services.size();i++)
            if(this.services.get(i).getHotel()!=this.reservation.getReservationSet().getHotel())
                return false;


        boolean result = true;
        for (HotelService s : this.services) {
            if (s.getService() instanceof VipService) {
                if(this.review == null)
                    return false;
            }
        }

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
}
