import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private Supplier sup;
    private List<LineItem> items;
    private PremiumAccount premiumAccount;

    /**
     * Product constructor -> includes id and name and must have a supplier
     * @param id - string
     * @param name - string
     * @param sup - supplier
     * @throws InvalidArgumentException
     */
    public Product(String id, String name, Supplier sup) throws InvalidArgumentException{
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

    // SETTERS

    /**
     * supplier set -> product has to have exactly one supplier.
     * @param sup-Supplier
     * @throws InvalidArgumentException
     */
    public void setSupplier(Supplier sup) throws InvalidArgumentException {
        if(sup == null)
            throw new InvalidArgumentException(new String[]{"product must be related to one supplier"});
        this.sup = sup;
    }

    /**
     * product can have one premium account or none (no input validation is required)
     * @param premiumAccount -PremiumAccount
     */
    public void setPremiumAccount(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;
    }

    /**
     * add items to the LineItem list (initiated int the constructor)
     * @param item - LineItem
     */
    public void addLineItem(LineItem item){
        this.items.add(item);
    }
}
