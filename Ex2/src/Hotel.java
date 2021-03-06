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

    public boolean Constraint6(){
        int sumVIP=0;
        for (Room room:rooms.values()) {
            if (room.getRoomCategory().getType() == RoomCategory.RoomType.VIP)
                sumVIP++;
        }
        if(sumVIP>(rooms.size()/10))
            return false;
        return true;
    }

    public boolean Constraint11(){
        HashSet<String> service = new HashSet<>();
        for (Service ser:services.keySet()) {
            if(service.contains(ser.getServiceName()))
                return false;
            service.add(ser.getServiceName());
        }
        return true;
    }

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
        //constraint 7
        if(this.city.equals("LAS VEGAS") || this.city.equals("las vegas") || this.city.equals("Las Vegas")){
            for (HashMap.Entry client : allReservation.entrySet()) {
                if (((Client)client.getKey()).getAge() < 21) {
                    return false;
                }
            }
        }
        //constraint 12,14
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

        if(!Constraint6())
            return false;
        if(!Constraint11())
            return false;

        // Constraint10
        int sum_ranks_reviews = 0;
        int number_of_reviews = 0;
        //for each hotel if its total rank is 5 --> check all the reservations and for all the clients who
        //wrote a review check the avg is larger than 7.5
        if(this.getRate()==5){
            for(ReservationSet reservationSet: this.getAllReservation().values()){
                for (Reservation reservation: reservationSet.getReservations()){
                    //if a client with reservation made a booking and a wrote a review(can be none)
                    if(reservation.getBookings()!=null && reservation.getBookings().getReview()!=null){
                        number_of_reviews+=1;
                        sum_ranks_reviews+=reservation.getBookings().getReview().getRank();
                    }
                }
            }
            if (number_of_reviews>0 && sum_ranks_reviews/number_of_reviews<=7.5)
                return false;
            number_of_reviews = 0;
            sum_ranks_reviews =0;
        }


        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }

}
