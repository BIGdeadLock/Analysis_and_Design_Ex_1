
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
    public void addProduct(Product product)throws NullPointerException, UnknownError{
        if(product == null)
            throw new NullPointerException("Argument can't be null");
        if(product.getSup()!=null)
            throw new UnknownError("product can only have one supplier");
        if(products.contains(product))
            throw new UnknownError("Can't add the same product more than once ");
        this.products.add(product);
        product.setSupplier(this);
    }


    /**
     * The function will delete a product from the list of products
     */
    public void removeProduct(Product product) {
        if (product != null || this.products.contains(product))
            this.products.remove(product);

    }

    public String toString(){
        String print="";
        print="Supplier ID: "+this.id+"\nName: "+this.name+"\nconnected to:";
        if(!this.products.isEmpty()) {
            for (int i = 0; i < this.products.size()-1; i++)
                print += "Product, ";
            print += "Product";
        }
        return print;
    }
}
