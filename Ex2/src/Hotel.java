import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Hotel implements  ITestable{
    private String name;
    private HashMap<Client, ReservationSet> allReservation;
    private HashMap<Service, HotelService> services;
    private HashMap<Integer,Room> rooms;
    private String city;
    private Group group;
    private int rate;



    public Hotel(String city, String name,int rate){
        this.city = city;
        this.name = name;
        this.rate = rate;
        rooms = new HashMap<Integer,Room>();
        allReservation = new HashMap<Client, ReservationSet>();
        services = new HashMap<Service, HotelService>();

    }

    public void addReservationSet(Client client,ReservationSet reservationSet){
        allReservation.put(client,reservationSet);
    }

    public void addService(Service service, HotelService hotelService){
        services.put(service,hotelService);
    }

    public void addRoom(int roomNumber, Room room){
        rooms.put(roomNumber,room);
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public HashMap<Client, ReservationSet> getAllReservation(){return allReservation;}

    public HashMap<Service, HotelService> getServices(){return services;}

    public int getRate(){return rate;}

    @Override
    public boolean checkConstraints() {
        if(this.city.equals("LAS VEGAS")){
            for (HashMap.Entry client : allReservation.entrySet()) {
                if (((Client)client.getKey()).getAge() < 21) {
                    return false;
                }
            }
        }

        if(services.size() != 0){
            HashMap<Integer,Integer> incomes = new HashMap<>();
            for (HashMap.Entry service : services.entrySet()) {
                HotelService specifficServices = (HotelService)service.getValue();
                int price = specifficServices.getPrice();
                Iterator<Booking> bookings = specifficServices.getGivenServices().iterator();
                Booking booking;
                while(bookings.hasNext()){
                    booking = bookings.next();
                    int year = booking.getDate().getYear();
                    if(incomes.containsKey(year)){
                        incomes.replace(year,incomes.get(year) + price);
                    }
                    else{
                        incomes.put(year,price);
                    }
                }
            }

            if(incomes.size() != 0){
                Iterator<Integer> year = incomes.keySet().iterator();
                Integer currYear;
                while(year.hasNext()){
                    currYear = year.next();
                    if(incomes.containsKey(currYear-1)){
                        if(incomes.get(currYear)<incomes.get(currYear-1)){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
}
