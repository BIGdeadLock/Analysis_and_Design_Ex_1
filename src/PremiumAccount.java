import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PremiumAccount extends Account {
    private List<Product> products;
    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer costume, ShoppingCart shoppingCart) throws InvalidArgumentException {
        super(id, billing_address, is_closed, open, closed, balance, costume, shoppingCart);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}

