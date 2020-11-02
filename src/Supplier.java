import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private String id;
    private String name;
    private List<Product> products;

    /**
     * supplier constructor -> includes id and name
     * @param id - string
     * @param name - string
     */
    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * add a product to the Products list (optional *)
     * @param product - Product
     */
    public void addProduct(Product product){
        this.products.add(product);
    }

    public String toString(){
        String print="";
        print="Supplier ID: "+this.id+"\nName: "+this.name+
                " Products: "+this.products;
        return print;
    }
}
