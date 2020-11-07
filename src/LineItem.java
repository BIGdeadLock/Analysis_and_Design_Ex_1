import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;
    private String id;


    public LineItem(String id, int quantity, int price, ShoppingCart shoppingCart, Order order, Product product) throws InvalidArgumentException {
        this.quantity = quantity;
        this.price = price;
        setShoppingCart(shoppingCart);
        setOrder(order);
        setProduct(product);
        this.id=id;
    }

    public LineItem(String id,int quantity, int price,Product product) throws InvalidArgumentException {
        this.quantity = quantity;
        this.price = price;
        setProduct(product);
        this.id=id;
    }

    /**
     * LineItem has to have exactly one ShoppingCart.
     * @param sp-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setShoppingCart(ShoppingCart sp) throws InvalidArgumentException {
        if(sp == null)
            throw new InvalidArgumentException(new String[]{"LineItem must be related to one shopping cart"});
        if(this.shoppingCart != null){
            throw new InvalidArgumentException(new String[]{"Can't add shopping cart because LineItem has one"});
        }
        sp.addLineItem(this);
        this.shoppingCart = sp;
    }

    /**
     * LineItem has to have exactly one Order.
     * @param order-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setOrder(Order order) throws InvalidArgumentException {
        if(order == null)
            throw new InvalidArgumentException(new String[]{"LineItem must be related to one order"});
        if(this.order!=null){
            throw new InvalidArgumentException(new String[]{"Can't add order because LineItem has one"});
        }
        if(!order.getLineItems().contains(this))
            order.addLineItem(this);
        this.order = order;
    }

    /**
     * LineItem has to have exactly one Product.
     * @param product-ShoppingCart
     * @throws InvalidArgumentException
     */
    public void setProduct(Product product) throws InvalidArgumentException {
        if(product == null)
            throw new InvalidArgumentException(new String[]{"LineItem must be related to one product"});
        if(this.product!=null)
            throw new InvalidArgumentException(new String[]{"Can't add product because LineItem has one"});
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
}
