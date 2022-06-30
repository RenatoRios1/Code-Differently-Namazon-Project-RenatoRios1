package additionaltools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class inputValidatorMenuTest {

    private InputValidatorMenu menu;

    @BeforeEach
    public void init(){
        menu = new InputValidatorMenu();
    }

    @Test
    @DisplayName("Constructor Test")
    public void constructorTest01(){
        Assertions.assertNotNull(menu);
    }

    @Test
    @DisplayName("isIntegerValid Test True ")
    public void isIntegerValidTest01(){
        String input = "100";
        Boolean expected = true;
        Boolean actual = menu.isIntegerValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isIntegerValid Test False")
    public void isIntegerValidTest02(){
        String input = "100.1";
        Boolean expected = false;
        Boolean actual = menu.isIntegerValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isIntegerValid Test empty String")
    public void isIntegerValidTest03(){
        String input = "";
        Boolean expected = false;
        Boolean actual = menu.isIntegerValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDoubleValid Test True ")
    public void isDoubleValidTest01(){
        String input = "100";
        Boolean expected = true;
        Boolean actual = menu.isDoubleValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDoubleValid Test False")
    public void isDoubleValidTest02(){
        String input = "100.1";
        Boolean expected = true;
        Boolean actual = menu.isDoubleValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDoubleValid Test empty String")
    public void isDoubleValidTest03(){
        String input = "";
        Boolean expected = false;
        Boolean actual = menu.isDoubleValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isValidEmail Test Pass")
    public void isValidEmail01(){
        String input = "renato.rios@codedifferently.com";
        Boolean expected = true;
        Boolean actual = menu.isValidEmail(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isValidEmail Test Fail")
    public void isValidEmail02(){
        String input = "renato.rios@codedifferently..com";
        Boolean expected = false;
        Boolean actual = menu.isValidEmail(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isValidPassWord Test Pass")
    public void isValidPassword01(){
        String input = "Renato123$";
        Boolean expected = true;
        Boolean actual = menu.isValidPassword(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isValidPassword Test Fail")
    public void isValidPassword02(){
        String input = "Tomato!Potato";
        Boolean expected = false;
        Boolean actual = menu.isValidPassword(input);
        Assertions.assertEquals(expected, actual);
    }





}
