import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;
    private List<LineItem> lineItems;

    public ShoppingCart(Date created) {
        this.created = created;
    }
}
