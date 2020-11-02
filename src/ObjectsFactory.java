import java.util.HashMap;

public class ObjectsFactory {
    HashMap<String, Object> objectMap = new HashMap<>();

    public void addObject(String id, Object o){
        assert (objectMap.containsKey(id));
        objectMap.put(id, o);
    }

    public Object getObjectType (Object o) {

        if(o instanceof Account)
            return (Account)o;
        
        else if(o instanceof PremiumAccount)
            return (PremiumAccount) o;

        else if(o instanceof Customer)
            return (Customer) o;

        else if(o instanceof DelayedPayment)
            return (DelayedPayment) o;

        else if(o instanceof ImmediatePayment)
            return (ImmediatePayment) o;

        else if(o instanceof LineItem)
            return (LineItem) o;

        else if(o instanceof Order)
            return (Order) o;

        else if(o instanceof Payment)
            return (Payment) o;

        else if(o instanceof Product)
            return (Product) o;

        else if(o instanceof ShoppingCart)
            return (ShoppingCart) o;

        else if(o instanceof Supplier)
            return (Supplier) o;

        else
            return (WebUser) o;
    }
}
