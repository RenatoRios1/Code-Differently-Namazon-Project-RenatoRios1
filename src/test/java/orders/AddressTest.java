package orders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddressTest {

    private Address address;

    @BeforeEach
    public  void inti(){
        String street = "Street";
        String unit = "123";
        String city = "HomeTown";
        String state = "TX";
        address = new Address(street, unit, city, state);
    }

    @Test
    @DisplayName("Constructor Test")
    public void constructorTest01(){
        Assertions.assertNotNull(address);
    }

    @Test
    @DisplayName("To String Test")
    public void toStringTest01(){
        String expected = "Street 123, HomeTown, TX";
        String actual = address.toString();
        Assertions.assertEquals(expected, actual);
    }
}
