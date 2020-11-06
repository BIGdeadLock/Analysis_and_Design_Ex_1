import java.util.HashMap;
import java.util.Map;

public class ObjectsFactory {
    HashMap<String, Object> objectMap = new HashMap<>();
    static int id = 0;

    public void addObject(Object o){
        assert (objectMap.containsKey(Integer.toString(id)));
        objectMap.put(Integer.toString(id), o);
        id ++;
    }

    public void removeObject(Object o1){
        for (Map.Entry<String,Object>entry: objectMap.entrySet()){
            if (o1.equals(entry.getValue()))
                objectMap.remove(entry.getKey());
        }

    }

    public String getObjectId(Object o1) {
        for (Map.Entry<String,Object>entry: objectMap.entrySet()){
            if (o1.equals(entry.getValue()))
                return entry.getKey();
        }
        return null;
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
