
import java.util.List;

public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;
    private String id;


    public LineItem(String id, int quantity, int price, ShoppingCart shoppingCart, Order order, Product product) throws
            NullPointerException, UnknownError{
        this.quantity = quantity;
        this.price = price;
        setShoppingCart(shoppingCart);
        setOrder(order);
        setProduct(product);
        this.id=id;
    }

    public LineItem(String id,int quantity, int price,Product product) throws NullPointerException, UnknownError {
        this.quantity = quantity;
        this.price = price;
        setProduct(product);
        this.id=id;
    }

    /**
     * LineItem has to have exactly one ShoppingCart.
     * @param sp-ShoppingCart
     * @throws NullPointerException
     */
    public void setShoppingCart(ShoppingCart sp) throws NullPointerException, UnknownError {
        if(sp == null)
            throw new NullPointerException("LineItem must be related to one shopping cart");
        if(this.shoppingCart != null){
            throw new UnknownError("Can't add shopping cart because LineItem has one");
        }
        sp.addLineItem(this);
        this.shoppingCart = sp;
    }

    /**
     * LineItem has to have exactly one Order.
     * @param order-ShoppingCart
     * @throws NullPointerException
     */
    public void setOrder(Order order) throws NullPointerException, UnknownError {
        if(order == null)
            throw new NullPointerException("LineItem must be related to one order");
        if(this.order!=null){
            throw new UnknownError("Can't add order because LineItem has one");
        }
        if(!order.getLineItems().contains(this))
            order.addLineItem(this);
        this.order = order;
    }

    /**
     * LineItem has to have exactly one Product.
     * @param product-ShoppingCart
     * @throws NullPointerException
     */
    public void setProduct(Product product) throws NullPointerException, UnknownError {
        if(product == null)
            throw new NullPointerException("LineItem must be related to one product");
        if(this.product!=null)
            throw new UnknownError("Can't add product because LineItem has one");
        if(!product.getItems().contains(this))
            product.addLineItem(this);
        this.product = product;

    }

    public String toString(){
        String print="";
        print="LineItem Quantity: "+this.quantity+"\nPrice: "+this.price+"\nconnected to:";
        if(this.shoppingCart!=null) //should have one the check is for us to know if its not working
            print+="ShoppingCart";
        if(this.product!=null) //should have one the check is for us to know if its not working
            print+=", product";
        if(this.order!=null) //should have one the check is for us to know if its not working
            print+=", Order";
        return print;
    }

    //GETTERS
    public int getQuantity() { return quantity; }

    public int getPrice() { return price; }

    public ShoppingCart getShoppingCart() { return shoppingCart; }

    public Order getOrder() { return order; }

    public Product getProduct() { return product; }

    public String getId() { return id; }

    /**
     * The function will start the deletion operation.
     * Each connection will be set to null and it will
     * remove it self from all one-to-many connections
     */
    public void Delete(){
        ShoppingCart shoppingCart = this.shoppingCart;
        this.shoppingCart = null;
        /* Need to check if the delete was not activated twice
        * If this is the second time the Delete() was called -
        *  all class attributes will be set to null
        * */
        if (shoppingCart != null)
            shoppingCart.removeLineItem(this);

        this.shoppingCart = null;
        Order ord = this.order;
        this.order = null;
        if (ord != null)
            ord.removeLineItem(this);

    }
}
