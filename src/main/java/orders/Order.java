package orders;

import orders.Address;
import products.Product;

public class Order {
    private static Long idCount = 1L;
    private Long id;
    private Product product;
    private Address destination;
    private OrderStatus status;

    public Order(Product product, Address destination, OrderStatus status) {
        this.product = product;
        this.destination = destination;
        this.status = status;
        this.id = idCount++;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return String.format("Product: %s, Destination: %s, Order Status: %s", product.toString(), destination.toString(), status.getStatus());
    }
}
