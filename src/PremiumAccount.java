import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PremiumAccount extends Account {
    private List<Product> products;


    public PremiumAccount(String id, String billing_address,int balance, Customer customer,ShoppingCart shoppingCart) throws InvalidArgumentException {
        super(id, billing_address,balance, customer, shoppingCart);
        this.products = new ArrayList<>();
    }

    public PremiumAccount(String id, String billing_address,int balance, Customer customer, int cartid) throws InvalidArgumentException {
        super(id, billing_address,balance, customer,cartid);
        this.products = new ArrayList<>();
    }


//    public PremiumAccount(String id, String billing_address, Customer costume, ShoppingCart shoppingCart) throws InvalidArgumentException {
//        super(id, billing_address, costume, shoppingCart);
//        this.products = new ArrayList<>();
//    }

    public void addProduct(Product product) throws InvalidArgumentException {
        if(product == null)
            throw new InvalidArgumentException(new String[]{"Product is null"});
        if(!this.products.contains(product)) {
            if (product.getPremiumAccount() != this) {
                if (product.getPremiumAccount() == null) {
                    this.products.add(product);
                    product.setPremiumAccount(this);
                } else {
                    throw new InvalidArgumentException(new String[]{"Can't add premium account because it has one"});
                }
            }
        }
        else{
            throw new InvalidArgumentException(new String[]{"premium account has this product alredy"});
        }
    }

    public void removeProduct(Product product) {
        if (product != null || this.products.contains(product))
            this.products.remove(product);

    }

    public String toString(){
        String print="";
        print="Premium Account ID: "+getId()+"\nBilling Address: "+getBilling_address()+
                " closed status: "+getIs_closed()+" Date Opened: "+getOpen()+
                " Date Closed: "+getClosed()+" Balance:"+getBalance()+"\nconnected to:";
        if(getCustomer()!=null) //should have one the check is for us to know if its not working
            print+="Customer";
        if(getShoppingCart()!=null) //should have one the check is for us to know if its not working
            print+=", ShoppingCart";
        if(!getPayments().isEmpty()){
            for (int i = 0; i < getPayments().size(); i++)
                print += ", Payment";
        }
        if(!getOrders().isEmpty()){
            for (int i = 0; i < getOrders().size(); i++)
                print += ", Order";
        }
        if(!getProducts().isEmpty()){
            for (int i = 0; i < getProducts().size(); i++)
                print += ", Product";
        }
        return print;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete(){

        ShoppingCart shp = this.shoppingCart;
        Customer customer = this.customer;
        List<Product> prs = this.products;

        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if(this.customer!=null)
            this.customer = null;
        if(this.shoppingCart!=null)
            this.shoppingCart = null;

        if (shp != null)
            shp.Delete();

        if (customer != null) {
            try {
                customer.Delete();
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
        for (Product p:
             prs) {
            try {
                p.setPremiumAccount(null);
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }

        }
    }
}

