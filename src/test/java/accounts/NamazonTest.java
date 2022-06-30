package accounts;

import exceptions.AccountAuthenticationException;
import exceptions.PasswordValidationException;
import exceptions.UsernameValidationException;
import namazon.Namazon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NamazonTest {

    private Namazon namazon;
    private Vendor vendor;
    private Customer customer;

    @BeforeEach
    public void init(){
        namazon = new Namazon();

        String brandName = "Fenty";
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        vendor = new Vendor(brandName, firstName, lastName, email,password );

        String firstName1 = "Person";
        String lastName1  = "Human";
        String email1 = "person@person.com";
        String password1 = "Password123!";
        customer = new Customer(firstName, lastName, email,password );
    }

    @Test
    @DisplayName("signInTest")
    public void signInTest01(){
        String brandName = "Fenty";
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        Vendor vendor = new Vendor(brandName, firstName, lastName, email,password );
        List<Vendor> temp = new ArrayList<>();
        temp.add(vendor);
        namazon.setVendors(temp);

        String expected = "Rihanna Fenty Umbrella@fenty.com";
        String actual = "";
        try {
            actual = namazon.signIn(email, password).toString();
        } catch (UsernameValidationException e) {
            e.printStackTrace();
        } catch (PasswordValidationException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("signInTest Fail")
    public void signInTest02(){
        String brandName = "Fenty";
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        Vendor vendor = new Vendor(brandName, firstName, lastName, email,password );
        List<Vendor> temp = new ArrayList<>();
        temp.add(vendor);
        namazon.setVendors(temp);

        String expected = "Rihanna Fenty Umbrella@fenty.com";
        String actual = "";
        try {
            actual = namazon.signIn(email, password).toString();
        } catch (UsernameValidationException e) {
            e.printStackTrace();
        } catch (PasswordValidationException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("signUpAsVendor Test")
    public void signUpAsVendor() throws AccountAuthenticationException {
        String brandName = "Fenty";
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        Vendor expected = new Vendor(brandName, firstName, lastName, email,password );
        Vendor actual = namazon.signUpAsVendor(brandName, firstName, lastName, email, password);

        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("signUpAsVendorTest fail")
    public void signUpAsVendorFail(){
        Assertions.assertThrows(AccountAuthenticationException.class, ()->{
            String brandName = "Fenty";
            String firstName = "Rihanna";
            String lastName  = "Fenty";
            String email = "Umbrella@fenty.com";
            String password = "Password123!";
            namazon.signUpAsVendor(brandName, firstName, lastName, email, password);
            //fails after the first because that account email is already taken
            namazon.signUpAsVendor(brandName, firstName, lastName, email, password);
        });
    }

    @Test
    @DisplayName("signUpAsCustomer Test")
    public void signUpAsCustomer01() throws AccountAuthenticationException {
        String firstName = "Rihanna";
        String lastName  = "Fenty";
        String email = "Umbrella@fenty.com";
        String password = "Password123!";
        Customer expected = new Customer( firstName, lastName, email,password );
        Customer actual = namazon.signUpAsCustomer(firstName, lastName, email, password);

        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("signUpAsCustomer fail")
    public void signUpAsCustomer02(){
        Assertions.assertThrows(AccountAuthenticationException.class, ()->{
            String firstName = "Rihanna";
            String lastName  = "Fenty";
            String email = "Umbrella@fenty.com";
            String password = "Password123!";
            namazon.signUpAsCustomer(firstName, lastName, email, password);
            //fails after the first because that account email is already taken
            namazon.signUpAsCustomer(firstName, lastName, email, password);
        });
    }




}


