package products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product testProduct;

    @BeforeEach
    public void init(){
        String name = "Product";
        ProductCategory category = ProductCategory.ELECTRONICS ;
        Double price = 123.45;
        testProduct = new Product(name, category, price);
    }

    @Test
    @DisplayName("Constructor Test")
    public void contructorTest01(){
        Assertions.assertNotNull(testProduct);
    }
}
