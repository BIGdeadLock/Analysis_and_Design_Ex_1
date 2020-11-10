
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private Supplier sup;
    private List<LineItem> items;
    private PremiumAccount premiumAccount;
    private int quantity;
    private int price;

    /**
     * Product constructor -> includes id and name and must have a supplier
     * @param id - string
     * @param name - string
     * @param sup - supplier
     * @throws NullPointerException
     */
    public Product(String id, String name, Supplier sup) throws
            NullPointerException, UnknownError{
        this.id = id;
        this.name = name;
        setSupplier(sup);
        this.items = new ArrayList<>();
    }

    // GETTERS

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Supplier getSup() {
        return sup;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public int getQuantity() { return quantity; }

    public int getPrice() { return price; }

    // SETTERS

    /**
     * supplier set -> product has to have exactly one supplier.
     * @param supplier-Supplier
     * @throws NullPointerException
     */
    public void setSupplier(Supplier supplier) throws NullPointerException {
        if(supplier == null)
            throw new NullPointerException("Argument can't be null");
        if(this.sup != null)
            throw new UnknownError("Can't add more than one supplier");
        if(!supplier.getProducts().contains(this) )
            supplier.addProduct(this);
        this.sup = supplier;
    }

    /**
     * product can have one premium account or none
     * @param premiumAccount -PremiumAccount
     */
    public void setPremiumAccount(PremiumAccount premiumAccount)throws UnknownError {
        if(premiumAccount == null) {
            this.premiumAccount = null;
            this.price = 0;
            this.quantity = 0;
        }
        //product does not yet have a premiumAccount
        if(this.premiumAccount != null)
            throw new UnknownError("Can't add more than one premium account");
        this.premiumAccount = premiumAccount;
    }

    /**
     * add items to the LineItem list (initiated int the constructor)
     * @param item - LineItem
     */
    public void addLineItem(LineItem item) throws NullPointerException,UnknownError {
        if(item == null)
            throw new NullPointerException("Argument can't be null");
        if(items.contains(item))
            throw new UnknownError("Can't contain the same item more than once");
        this.items.add(item);
        item.setProduct(this);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * The function will delete a line item from the list of items
     */
    public void removeLineItem(LineItem item){
        if (item != null || this.items.contains(item))
            this.items.remove(item);
    }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete(){
        Supplier sup = this.sup;
        PremiumAccount pa = this.premiumAccount;
        /* Need to check if the delete was not activated twice
         * If this is the second time the Delete() was called -
         *  all class attributes will be set to null
         * */
        if(this.sup!=null)
            this.sup = null;
        if(this.premiumAccount!=null)
            this.premiumAccount = null;

        if(sup != null)
            sup.removeProduct(this);
        if (pa != null)
            pa.removeProduct(this);
        if(items!=null) {
            while (!(items.isEmpty())) {
                LineItem temp = items.get(0);
                this.removeLineItem(items.get(0));
                temp.Delete();
            }
        }
    }

    public String toString(){
        String print="";
        print="Product ID: "+this.id+"\nName: "+this.name+ "\nconnected to:";
        if(this.sup!=null) //should have one the check is for us to know if its not working
            print+="Supplier";
        if(this.premiumAccount!=null)
            print+=", PremiumAccount";
        if(!this.items.isEmpty()) {
            for (int i = 0; i < this.items.size(); i++)
                print += ", lineItem";
        }
        return print;
    }
}
