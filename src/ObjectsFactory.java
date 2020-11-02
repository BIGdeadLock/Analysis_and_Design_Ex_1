import java.util.HashMap;

public class ObjectsFactory {
    HashMap<String, Object> objectMap = new HashMap<>();

    public void addObject(String id, Object o){
        assert (objectMap.containsKey(id));
        objectMap.put(id, o);
    }

    public Object getObjectType (String id) {

        if (!this.objectMap.containsKey(id))
            return null;

        if(this.objectMap.get(id) instanceof Account)
            return (Account)this.objectMap.get(id);
        
        else if(this.objectMap.get(id) instanceof PremiumAccount)
            return (PremiumAccount) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof Customer)
            return (Customer) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof DelayedPayment)
            return (DelayedPayment) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof ImmediatePayment)
            return (ImmediatePayment) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof LineItem)
            return (LineItem) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof Order)
            return (Order) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof Payment)
            return (Payment) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof Product)
            return (Product) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof ShoppingCart)
            return (ShoppingCart) this.objectMap.get(id);

        else if(this.objectMap.get(id) instanceof Supplier)
            return (Supplier) this.objectMap.get(id);

        else
            return (WebUser) this.objectMap.get(id);
    }
}
