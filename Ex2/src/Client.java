import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Client implements  ITestable {
    private HashMap<Hotel,ReservationSet> reservationsHistory;
    private int id;
    private int age;
    private String name;
    private String city;

    public HashMap<Hotel, ReservationSet> getReservationsHistory() {
        return reservationsHistory;
    }

    public Client(int a_id, int a_age, String a_name, String a_city){
        reservationsHistory = new HashMap<Hotel,ReservationSet>();
        id = a_id;
        age = a_age;
        city = a_city;
        name = a_name;
    }

    public void addReservationSet(Hotel hotel, ReservationSet reset){
        reservationsHistory.put(hotel,reset);
    }

    // getters

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean checkConstraints() {
        //constraint 2
        for (HashMap.Entry hotel: reservationsHistory.entrySet()) {
            int size = ((ReservationSet) hotel.getValue()).getReservations().size();
            if (size >= 5) {
                boolean flag = false;
                List<Reservation> Reservations = ((ReservationSet) hotel.getValue()).getReservations();
                for (int i = 0; i < size; i++) {
                    if (Reservations.get(i).getBookings() !=null) {
                        if (Reservations.get(i).getBookings().getRoom() != null) {
                            if (Reservations.get(i).getBookings().getRoom().getRoomCategory().getType().equals(RoomCategory.RoomType.VIP)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (!flag) {
                    return flag;
                }
            }

        }
        System.out.println("inClien I am true");
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){

        for (Client c: model.ClientAllInstances()){
            if (c == null)
                return true;
            if(!c.checkConstraints())
                return false;
        }

        return true;
    }
}
