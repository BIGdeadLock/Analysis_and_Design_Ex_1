import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectsFactory {
    HashMap<String, Object> objectMap = new HashMap<>();
    HashMap<String, String> IdMap = new HashMap<>(); // Key - Object id, Value - System id
    static int id = 0;

    /**
     * The function will return the next free id in the system
     *
     * @return String type of id attribute
     */
    public String getNextFreeId() {
        return Integer.toString(id);
    }

    /**
     * The function will add the object to all the class's data structures.
     * The object o will be given an id, and the id will be incremented to point to the next free id
     * in the system
     * @param o - Can be any object
     * Assert: If the o already exists in the system -> an assertion error will be thrown
     */
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

    /**
     * The function will get an object and will delete it from the system.
     * If the o1 is a string - meaning it is an id for another object, the function will search
     * for the object that corresponds to that id.
     * The function will delete it from all the class's data structure, from the Main and will activate
     * the objects Delete() function resulting in that function being remove from the system
     * @param o1 - Object to delete or a String representing an Id of some object in the hashmap
     */
    public void removeObject(Object o1) {
        String object_id;
        if (o1 instanceof String) {
            object_id = (String) o1;
            o1 = objectMap.get(IdMap.get(object_id));
            objectMap.remove(IdMap.get(object_id));
            IdMap.remove(object_id);
            Main.objectsMap.remove(object_id);
        }
        else
            this.deleteObj(o1);

        // Switch case to use the Delete function of every class that implements it
        try {
            /* When web user is deleted: Account, Customer, ShoppingCart, Lineitems, Orders, Payments
            * Are all deleted with the webuser*/
            if (o1 instanceof WebUser) {
                WebUser wb = ((WebUser) o1);
                ShoppingCart shp = wb.getShoppingCart();
                if (shp != null)
                    this.deleteObj(shp);
                Account acc = shp.getAccount();
                if (acc != null)
                    this.deleteObj(acc);
                Customer cust = wb.getCustomer();
                if (cust != null)
                    this.deleteObj(cust);

                List<Order> orderList = acc.getOrders();
                if (orderList != null) {
                    for (Order order : orderList)
                        this.deleteObj(order);
                }

                List<LineItem> lineItems = shp.getLineItems();
                if (lineItems != null) {
                    for (LineItem item : lineItems)
                        this.deleteObj(item);
                }

                List<Payment> paymentList = acc.getPayments();
                if (paymentList != null) {
                    for (Payment payment : paymentList)
                        this.deleteObj(payment);
                }
                ((WebUser) o1).Delete();

            }
            /* When a product is deleted: Supplier, Line Items
            * Are deleted with the product */
            else if (o1 instanceof Product) {
                Product ord = (Product) o1;
                Supplier supplier = ord.getSup();
                List<LineItem> lineItemList = ord.getItems();

                if (lineItemList != null) {
                    for (LineItem item : lineItemList)
                        this.deleteObj(item);
                }
                if (supplier != null)
                    this.deleteObj(supplier);

                this.deleteObj(ord);
                ord.Delete();
            }
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function will get an object, search for it in the data structures and
     * return the object's system id
     * @param o1 - The object to search for and return its id
     * @return - String | The system id that corresponds to that object
     */
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

    /**
     * The function will get an id, that can be a system id or an object id (for example the login id of a webuser),
     * and will search for the object that corresponds to that id.
     * It will cast the object so that it will be return as an concrete object (for example WebUser).
     * @param id - Object id or System id that belongs to an object
     * @return - Null if the object does not exists in the system | Concrete object (WebUser, Product etc)
     */
    public Object getObjectType(String id) {

        /* The are two options:
         * 1. The id that was given is the system id of the object
         * 2. The id that was given is an object's id. In that case we will need
         * to change the id that was given to point to the object's system id */

        if (!this.objectMap.containsKey(id) && !this.IdMap.containsKey(id))
            return null;

        else if (this.IdMap.containsKey(id))
            id = this.IdMap.get(id);

        if (this.objectMap.get(id) instanceof PremiumAccount)
            return (PremiumAccount) this.objectMap.get(id);

        else if (this.objectMap.get(id) instanceof Account)
            return (Account) this.objectMap.get(id);

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

    /**
     * The function will return if the id exists in a valid key
     * in the objectsMap
     * @param id - the system id that corresponds to an object
     * @return True if the id exists in the system
     */
    public boolean isSystemIdExists(String id){
        return this.objectMap.containsKey(id);
    }

}
