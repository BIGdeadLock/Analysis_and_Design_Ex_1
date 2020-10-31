public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;

    public LineItem(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }
}
