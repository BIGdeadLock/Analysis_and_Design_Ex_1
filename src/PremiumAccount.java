import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PremiumAccount extends Account {
    private List<Product> products;
    public PremiumAccount(String id, String billing_addrss, boolean is_closed, Date open, Date closed, int balance, Customer costume, ShoppingCart shoppingCart, List<Payment> payment, List<Order> order,List<Product> products) {
        super(id, billing_addrss, is_closed, open, closed, balance, costume, shoppingCart, payment, order);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}

