import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectsFactory {
    HashMap<String, Object> objectMap = new HashMap<>();
    HashMap<String, String> IdMap = new HashMap<>(); // Key - Object id, Value - System id
    static int id = 0;

    /**
     * The function will return the next free id in the system and will increment the id counter
     *
     * @return String type of id attribute
     */
    public String getNextFreeId() {
        String id_to_return = Integer.toString(id);
        return id_to_return;
    }

    public void addObject(Object o) {
        assert (objectMap.containsKey(Integer.toString(id)));
        objectMap.put(Integer.toString(id), o);
        id++;
    }

    /**
     * @param object_id - The object id UML attribute in string
     * @param o         - the object that was created
     */
    public void addObject(String object_id, Object o) {
        assert (objectMap.containsKey(Integer.toString(id)));
        objectMap.put(Integer.toString(id), o);
        IdMap.put(object_id, Integer.toString(id));
        id++;
    }

    public void removeObject(Object o1) {
        String object_id;
        if (o1 instanceof String) {
            object_id = (String) o1;
            o1 = objectMap.get(IdMap.get(object_id));
            objectMap.remove(IdMap.get(object_id));
            IdMap.remove(object_id);
            Main.objectsMap.remove(object_id);
        } else {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                if (o1.equals(entry.getValue()))
                    objectMap.remove(entry.getKey());
            }
        }
        // Switch case to use the Delete function of every class that implements it
        try {
            if (o1 instanceof Customer)
                ((Customer) o1).Delete();

            else if (o1 instanceof WebUser) {
                WebUser wb = ((WebUser) o1);
                ShoppingCart shp = wb.getShoppingCart();
                this.deleteObj(shp);
                Account acc = shp.getAccount();
                this.deleteObj(acc);
                Customer cust = wb.getCustomer();
                this.deleteObj(cust);

                List<LineItem> lineItems = shp.getLineItems();
                for (LineItem item : lineItems)
                    this.deleteObj(item);


                List<Payment> paymentList = acc.getPayments();
                for (Payment payment : paymentList)
                    this.deleteObj(payment);

                ((WebUser) o1).Delete();
            } else if (o1 instanceof Account)
                ((Account) o1).Delete();

            else if (o1 instanceof ShoppingCart)
                ((ShoppingCart) o1).Delete();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        //  else if (o1 instanceof LineItem)
        //       ((LineItem)o1).Delete();

        //  else if (o1 instanceof Order)
        //     ((Order)o1).Delete();

        //   else if (o1 instanceof Payment)
        //     ((Payment)o1).Delete();

        //  else if (o1 instanceof Product)
        //     ((Product)o1).Delete();

        //   else if (o1 instanceof Supplier)
        //  ((Supplier)o1).Delete();
    }

    public String getObjecSystemtId(Object o1) {

        // If o1 is String - the user intentions were get the system id
        // that corresponds to the object id and passed the object id.
        // Becuase String is an object as well we need to handle that special case
        // separately
        if (o1 instanceof String)
            return IdMap.get((String) o1);

        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (o1.equals(entry.getValue()))
                return entry.getKey();
        }
        return null;
    }

    public Object getObjectType(String id) {

        /* The are two options:
         * 1. The id that was given is the system id of the object
         * 2. The id that was given is an object's id. In that case we will need
         * to change the id that was given to point to the object's system id */

        if (!this.objectMap.containsKey(id) && !this.IdMap.containsKey(id))
            return null;

        else if (this.IdMap.containsKey(id))
            id = this.IdMap.get(id);

        if (this.objectMap.get(id) instanceof Account)
            return (Account) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof PremiumAccount)
            return (PremiumAccount) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Customer)
            return (Customer) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof DelayedPayment)
            return (DelayedPayment) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof ImmediatePayment)
            return (ImmediatePayment) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof LineItem)
            return (LineItem) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Order)
            return (Order) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Payment)
            return (Payment) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Product)
            return (Product) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof ShoppingCart)
            return (ShoppingCart) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Supplier)
            return (Supplier) this.objectMap.get(id);

        else
            return (WebUser) this.objectMap.get(id);
    }

    public void deleteObj(Object o1) {
        // Search for the object value (not the object key), in the the objectMap
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            if (o1.equals(entry.getValue())) {
                objectMap.remove(entry.getKey());
                // After finding it search for the object id in the Id Map
                for (Map.Entry<String, String> e : IdMap.entrySet()) {
                    if (e.getValue().equals(entry.getKey())) {
                        // Delete the object from the Main map
                        Main.objectsMap.remove(e.getKey());
                        IdMap.remove(e.getKey());
                        break;
                    }
                }
                break;
            }
        }
    }
}
