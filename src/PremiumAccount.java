import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class PremiumAccount extends Account {
    private List<Product> products;
    public PremiumAccount(String id, String billing_address, Customer costume, ShoppingCart shoppingCart) throws InvalidArgumentException {
        super(id, billing_address, costume, shoppingCart);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public String toString(){
        String print="";
        print="Premium Account ID: "+getId()+"\nBilling Address: "+getBilling_address()+
                " closed status: "+getIs_closed()+" Date Opened: "+getOpen()+
                " Date Closed: "+getClosed()+" Balance:"+getBalance()+"\nProducts: "+this.products;
        return print;
    }

    public List<Product> getProducts() {
        return products;
    }
}

