package accounts;

import exceptions.OrderNotFoundException;
import orders.Address;
import orders.Order;
import orders.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductCategory;
import products.ProductNotAvailableException;

import java.util.List;

public class VendorTest {

    private Vendor vendor;
    private Product product1;
    private Product product2;
    @BeforeEach
    public void init(){
        product1 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        String brandName = "Fenty";
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        vendor = new Vendor(brandName, firstName, lastName, email,password );

    }

    @Test
    @DisplayName("Vendor Constructor Test")
    public void vendorConstructorTest01(){
        Assertions.assertNotNull(vendor);
    }

    @Test
    @DisplayName("getQtyInInventoryTest empty")
    public void getQtyInInventoryTest01(){
        Integer expected = -1;
        Integer actual = vendor.getQtyInInventory(product1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("addProduct Test")
    public void getQtyInInventoryTest02(){
        vendor.addProduct(product1);
        Integer expected = 1;

        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        String checkerString = product2.toString();
        Integer actual = vendor.getQtyInInventory(product2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("addProduct Test")
    public void getQtyInInventoryTest03(){
        Integer expected = 2;

        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        Product product3 =  new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);

        vendor.addProduct(product1);
        vendor.addProduct(product2);

        Integer actual = vendor.getQtyInInventory(product3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("removeProduct Test Actually removes")
    public void removeProductTest(){
        Boolean expected = true;
        Product product3 =  new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        Boolean actual = vendor.removeProductByPurchase(product3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("removeProduct Test failed to remove")
    public void removeProductTest02(){
        Boolean expected = false;
        Boolean actual = vendor.removeProductByPurchase(product1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test non empty")
    public void searchByCategoryTest01(){
        ProductCategory expected = ProductCategory.ELECTRONICS;
        vendor.addProduct(product1);
        List<Product> list = vendor.searchByCategory(ProductCategory.ELECTRONICS);
        ProductCategory actual = list.get(0).getCategory();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test two Product")
    public void searchByCategoryTest02(){
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        ProductCategory expected = ProductCategory.ELECTRONICS;
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByCategory(ProductCategory.ELECTRONICS);
        ProductCategory actual = list.get(0).getCategory();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test two Product")
    public void searchByCategoryTest03(){
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        ProductCategory expected = ProductCategory.ELECTRONICS;
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByCategory(ProductCategory.ELECTRONICS);
        ProductCategory actual = list.get(1).getCategory();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test empty")
    public void searchByCategoryTest04(){
        Integer expected = 0;
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByCategory(ProductCategory.ATHLETICS);
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Category Test multiple matches")
    public void searchByCategoryTest05(){
        Integer expected = 2;
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByCategory(ProductCategory.ELECTRONICS);
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Test one match")
    public void searchByNameTest01(){
        Integer expected = 1;
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing1");
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Test one match multiple products added")
    public void searchByNameTest02(){
        Integer expected = 1;
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing1");
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Test no match")
    public void searchByNameTest03(){
        Integer expected = 0;
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing2");
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Testcheck Product")
    public void searchByNameTest04(){
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        String expected = product2.toString();
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing1");
        String actual = list.get(0).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Test no match")
    public void searchByNameTest05(){
        Integer expected = 0;
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing2");
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search by Name Test matches partial name")
    public void searchByNameTest06(){
        Integer expected = 2;
        product2 = new Product("Thing2", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product1);
        vendor.addProduct(product2);
        List<Product> list = vendor.searchByName("Thing");
        Integer actual = list.size();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("purchaseFromOrder Test")
    public void purchaseFromOrderTest01(){
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        String expected = product2.toString();

        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        Address address = new Address(street, unit, city, state);
        Order order1 = new Order(product1, address, OrderStatus.PENDING);
        String actual = order1.getProduct().toString();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("purchaseFromOrder Test")
    public void purchaseFromOrderTest02(){
        product2 = new Product("Thing", ProductCategory.ELECTRONICS, 101.01);
        String expected = product2.toString();

        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        Address address = new Address(street, unit, city, state);
        Order order1 = new Order(product1, address, OrderStatus.PENDING);
        String actual = order1.getProduct().toString();

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    @DisplayName("Add Order Test")
    public void addOrderTest01(){
        Integer expected = 1;
        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        Address address = new Address(street, unit, city, state);
        Order order1 = new Order(product1, address, OrderStatus.PENDING);
        vendor.addOrder(order1);
        Integer actual = vendor.displayAllOrders().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("displayAllOrders Test")
    public void displayAllOrdersTest01() throws ProductNotAvailableException {
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product2);
        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        Address address = new Address(street, unit, city, state);
        Order order1 = new Order(product1, address, OrderStatus.PENDING);
        vendor.addOrder(order1);
        List<Order> test = vendor.displayAllOrders();
        String productExpected = "Thing1 Electronics 101.01";
        String addressExpected = "Street 123, HomeTown, TX";
        String expected = String.format("Product: %s, Destination: %s, Order Status: %s", productExpected, addressExpected, OrderStatus.PENDING.getStatus());
        String actual = test.get(0).toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("displayALlOrders Test fail")
    public void displayAllOrdersTest02() {
        Assertions.assertThrows(ProductNotAvailableException.class, ()->{
            String street = "Street";
            String unit = "123";
            String city = "HomeTown";
            String state = "TX";
            Address address = new Address(street, unit, city, state);
            Order order1 = new Order(product1, address, OrderStatus.PENDING);
            vendor.addOrder(order1);
        });
    }

    @Test
    @DisplayName("cancelOrder Test")
    public void cancelOrderTest01() throws ProductNotAvailableException, OrderNotFoundException {
        Integer expected = 0;
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        vendor.addProduct(product2);
        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        Address address = new Address(street, unit, city, state);
        Order order1 = new Order(product1, address, OrderStatus.PENDING);
        vendor.addOrder(order1);
        List<Order> test = vendor.displayAllOrders();
        vendor.cancelOrder(order1);
        Integer actual = test.size();
        vendor.displayAllOrders();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("cancelOrder Test fail")
    public void cancelOrderTest02(){
        Assertions.assertThrows(OrderNotFoundException.class, ()->{
            String street = "Street";
            String unit = "123";
            String city = "HomeTown";
            String state = "TX";
            String state1 = "IL";
            Address address = new Address(street, unit, city, state);
            Address address1 =  new Address(street, unit, city, state1);
            Order order1 = new Order(product1, address, OrderStatus.PENDING);
            vendor.addOrder(order1);
            Order order2 = new Order(product1, address1, OrderStatus.PENDING);
            vendor.cancelOrder(order2);
        });
    }


    @Test
    @DisplayName("AddProductToShowcase Test")
    public void addProductToShowCaseTest01(){
        product2 = new Product("Thing1", ProductCategory.ELECTRONICS, 101.01);
        Product []arr = new Product[]{product2, null, null, null, null};
        Product expected = arr[0];

        vendor.addProductToShowcase(product1 , 0);
        Product []arr2 = vendor.displayShowcase();
        Product actual = arr2[0];

        Assertions.assertEquals(expected.toString(), actual.toString());
    }







}
