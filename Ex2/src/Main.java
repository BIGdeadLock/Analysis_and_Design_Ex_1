import java.util.Date;

public class Main {
    public static void main(String[] args){
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
