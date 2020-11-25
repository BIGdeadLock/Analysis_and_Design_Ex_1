import java.util.Date;
import java.util.ArrayList;

public class Reservation implements  ITestable {
    private int id;
    private RoomCategory roomCategory;
    private Date orderDate;
    private Date requestDate;
    private Booking booking;
    private ReservationSet reservationSet;


    public Reservation(Date ordDate, Date reqDate, int id) {
        this.id = id;
        orderDate = ordDate;
        requestDate = reqDate;
    }

    public void setReservationSet(ReservationSet reservationSet){
        this.reservationSet = reservationSet;
    }


    public void addRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking _booking) {
        booking = _booking;
    }


    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Booking getBookings() {
        return booking;
    }

    public int getId() {
        return id;
    }

    public ReservationSet getReservationSet(){return reservationSet;}

    @Override
    public boolean checkConstraints() {
        if(this.booking!=null && this.booking.getRoom()!=null)
            if(this.reservationSet.getHotel()!=this.booking.getRoom().getHotel())
                return false;
        if(this.roomCategory.getType()==RoomCategory.RoomType.VIP)
            if(this.booking.getRoom().getRoomCategory().getType()!=RoomCategory.RoomType.VIP)
                return false;
        if(this.roomCategory.getType()==RoomCategory.RoomType.SUITE)
            if(this.booking.getRoom().getRoomCategory().getType()==RoomCategory.RoomType.BASIC)
                return false;
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for (Reservation rs: model.ReservationAllInstances()){
            if (rs == null)
                return true;
            if(!rs.checkConstraints())
                return false;
        }

        return true;
    }


}
    