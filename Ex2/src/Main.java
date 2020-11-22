import java.util.Date;


public class Main {
    public static void main(String[] args) {
        constraint4();
    }

    public static void constraint4() {
        Model model = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("a", "Dan", 4);
        Hotel h2 = new Hotel("b", "Gan", 10);
        HotelService hs1 = new HotelService(10, 20);
        HotelService hs2 = new HotelService(10, 20);
        Service s1 = new VipService("clean");
        Service s2 = new VipService("clean");
        model.addObjectToModel(g);
        model.addObjectToModel(h1);
        model.addObjectToModel(h2);
        model.addObjectToModel(hs1);
        model.addObjectToModel(hs2);
        model.addObjectToModel(s1);
//        model.addObjectToModel(s2);
        model.create_link_group_hotel(h1, g);
        model.create_link_group_hotel(h2, g);
        model.create_link_hotel_service_hotelService(h1, s1, hs1);
        model.create_link_hotel_service_hotelService(h2, s1, hs2);
        System.out.println(model.checkModelConstraints());
    }

    public static void constraint9() {
        Model model = new Model();
        Client client1 = new Client(1, 27, "Eden", "B");
        Client client2 = new Client(2, 27, "A", "B");
        Hotel h1 = new Hotel("a", "Dan", 4);
        HotelService hs1 = new HotelService(10, 20);
        HotelService hs2 = new HotelService(10, 20);
        Service s1 = new VipService("clean");
        Service s2 = new RegularService("clean");
        ReservationSet set1 = new ReservationSet();
        ReservationSet set2 = new ReservationSet();
        Reservation r1 = new Reservation(Model.getDateFromString("01-02-2010"),
                Model.getDateFromString("01-02-2010"), 1);
        Reservation r2 = new Reservation(Model.getDateFromString("01-02-2010"),
                Model.getDateFromString("01-02-2010"), 2);
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Booking b1 = new Booking(Model.getDateFromString("01-02-2010"), room1);
        Booking b2 = new Booking(Model.getDateFromString("01-02-2010"), room2);
        Review rev1 = new Review(10, "a", Model.getDateFromString("01-02-2010"));
        model.addObjectToModel(set1);
        model.addObjectToModel(set2);
        model.addObjectToModel(r1);
        model.addObjectToModel(r2);
        model.addObjectToModel(b1);
        model.addObjectToModel(b2);
        model.addObjectToModel(room1);
        model.addObjectToModel(room2);
        model.addObjectToModel(h1);
        model.addObjectToModel(hs1);
        model.addObjectToModel(hs2);
        model.addObjectToModel(s1);
        model.addObjectToModel(s2);
        model.addObjectToModel(client1);
        model.addObjectToModel(client2);
        model.addObjectToModel(rev1);

        model.create_link_client_hotel_reservationSet(client1, h1, set1);
        model.create_link_client_hotel_reservationSet(client2, h1, set2);
        model.create_link_reservationSet_reservation(set1, r1);
        model.create_link_reservationSet_reservation(set2, r2);
        model.create_link_hotel_service_hotelService(h1, s1, hs1);
        model.create_link_hotel_service_hotelService(h1, s2, hs2);
        model.create_link_hotelService_booking(hs1, b1);
        model.create_link_hotelService_booking(hs2, b2);
        model.create_link_reservation_booking(b1, r1);
        model.create_link_reservation_booking(b2, r2);
        model.create_link_booking_review(b1, rev1);
        System.out.println(model.checkModelConstraints());
    }

    public static void constraintR(){
        Model m = new Model();
        Hotel hotel1 = new Hotel("Las Vegas","Paris",5);
        Client client1 = new Client(1,22,"D","T");
        Room r1 = new Room(404);
        ReservationSet res1 = new ReservationSet();
        Date orDate = Model.getDateFromString("25-12-2019");
        Date reqDate = Model.getDateFromString("01-01-2020");
        Reservation re1 = new Reservation(orDate,reqDate,100);
        /*Reservation re2 = new Reservation(orDate,reqDate,100);
        Reservation re3 = new Reservation(orDate,reqDate,100);
        Reservation re4 = new Reservation(orDate,reqDate,100);
        Reservation re5 = new Reservation(orDate,reqDate,100);*/
        VipService vs1 = new VipService("food");
        HotelService hs1 = new HotelService(40,3);
        Booking b1 = new Booking(orDate,r1);

        VipService vs2 = new VipService("food");
        HotelService hs2 = new HotelService(20,1);
        Booking b2 = new Booking(reqDate,r1);

        RoomCategory rc1 = new RoomCategory(200,RoomCategory.RoomType.BASIC);
        //RoomCategory rc2 = new RoomCategory(200,RoomCategory.RoomType.VIP);
        m.addObjectToModel(client1);
        m.addObjectToModel(hotel1);
        m.addObjectToModel(res1);
        m.addObjectToModel(re1);
        m.addObjectToModel(r1);
        m.addObjectToModel(vs1);
        m.addObjectToModel(b1);
        m.addObjectToModel(hs1);
        m.addObjectToModel(vs2);
        m.addObjectToModel(b2);
        m.addObjectToModel(hs2);
        m.create_link_hotel_service_hotelService(hotel1,vs1,hs1);
        m.create_link_hotelService_booking(hs1,b1);
        m.create_link_hotel_service_hotelService(hotel1,vs2,hs2);
        m.create_link_hotelService_booking(hs2,b2);
        m.create_link_client_hotel_reservationSet(client1,hotel1,res1);
        //m.create_link_reservationSet_reservation(res1,re1);
        /*m.create_link_reservationSet_reservation(res1,re2);
        m.create_link_reservationSet_reservation(res1,re3);
        m.create_link_reservationSet_reservation(res1,re4);
        m.create_link_reservationSet_reservation(res1,re5);
        //m.create_link_reservation_roomCategory(re1,rc1);
        m.create_link_reservation_roomCategory(re2,rc1);
        m.create_link_reservation_roomCategory(re3,rc1);
        m.create_link_reservation_roomCategory(re4,rc1);
        m.create_link_reservation_roomCategory(re5,rc1);*/
        m.create_link_hotel_room(r1,hotel1);
        m.create_link_room_roomCategory(r1,rc1);
        System.out.println(m.checkModelConstraints());
    }
}
