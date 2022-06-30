package orders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductCategory;

public class OrderTest {

    private Order testOrder;
    private Product testProduct;
    private Address testAddress;

    @BeforeEach
    public void init(){
        //Product
        String name = "Product";
        ProductCategory category = ProductCategory.ELECTRONICS ;
        Double price = 123.45;
        testProduct = new Product(name, category, price);
        //Address
        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        testAddress = new Address(street, unit, city, state);
        //Order
        testOrder = new Order(testProduct, testAddress, OrderStatus.PENDING);
    }

    @Test
    @DisplayName("Constructor Test")
    public void constructorTest01(){
        Assertions.assertNotNull(testOrder);
    }

}
