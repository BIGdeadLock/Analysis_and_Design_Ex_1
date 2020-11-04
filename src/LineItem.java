import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;

    public LineItem(int quantity, int price,ShoppingCart shoppingCart,Order order, Product product) throws InvalidArgumentException {
        this.quantity = quantity;
        this.price = price;
        setShoppingCart(shoppingCart);
        setOrder(order);
        setProduct(product);

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
        if(this.product!=null){
            throw new InvalidArgumentException(new String[]{"Can't add product because LineItem has one"});
        }
        this.product = product;
    }

    public String toString(){
        String print="";
        print="LineItem Quantity: "+this.quantity+"\nPrice: "+this.price;
        return print;
    }
}
