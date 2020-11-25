import java.util.Date;
public class Review implements  ITestable{

    private Booking booking;
    private int rank;
    private String reviewContent;
    private Date date;

    public Review(int rank, String reviewContent,Date date){
        this.rank = rank;
        this.reviewContent = reviewContent;
        this.date = date;
    }

    public void addBooking(Booking booking){
        this.booking = booking;
    }

    public int getRank(){
        return rank;
    }

    public Booking getBooking() {
        return booking;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    @Override
    public boolean checkConstraints() {
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for (Review r: model.ReviewAllInstances()){
            if (r == null)
                return true;
            if(!r.checkConstraints())
                return false;
        }

        return true;
    }


}