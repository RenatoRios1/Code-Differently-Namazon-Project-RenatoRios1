package namazon;

import accounts.Account;
import exceptions.AccountAuthenticationException;
import accounts.Customer;
import accounts.Vendor;
import additionaltools.InputValidatorMenu;
import exceptions.PasswordValidationException;
import exceptions.UsernameValidationException;

import java.util.ArrayList;
import java.util.List;

public class Namazon {

    private List<Customer> customers;
    private List<Vendor> vendors;

    public Namazon() {
        customers = new ArrayList<>();
        vendors = new ArrayList<>();
    }

    public Account signIn(String username, String password) throws UsernameValidationException, PasswordValidationException {
        Account tempAccount = null;
        if(isRegisteredVendor(username)){
            if(getVendor(username).validatePassword(password)){
                tempAccount = getVendor(username);
            }else{
                throw new PasswordValidationException("Password does not match");
            }
        }else if(isRegisteredCustomer(username)){
            if(getCustomer(username).validatePassword(password)){
                tempAccount = getCustomer(username);
            }else{
                throw new PasswordValidationException("Password does not match");
            }
        } else{
            throw new UsernameValidationException("This email is not registered.");
        }
        return  tempAccount;
    }

    public Vendor signUpAsVendor(String brandname, String firstName, String lastName, String email, String password) throws  AccountAuthenticationException{
        Vendor vendor = null;
        if(!isRegisteredCustomer(email) && !isRegisteredVendor(email)){
            vendor = new Vendor(brandname, firstName, lastName, email, password);
            vendors.add(vendor);
        }else{
            throw new AccountAuthenticationException("This account has already been created");
        }
        return vendor;
    }

    public Customer signUpAsCustomer(String firstName, String lastName, String email, String password) throws AccountAuthenticationException{
        Customer customer = null;
        if(!isRegisteredCustomer(email) && !isRegisteredVendor(email)){
            customer = new Customer(firstName, lastName, email, password);
            customers.add(customer);
        }else{
            throw new AccountAuthenticationException("This account has already been created");
        }
        return customer;
    }

    //Little utility to the class atm
//    public Vendor selectVendor(Vendor vendor) {
//
//
//        return null;
//    }


    public Vendor getVendor(String username){
        Vendor outputVendor = null;
        for(Vendor vendor1: vendors){
            if(vendor1.getEmail().equals(username)){
                outputVendor = vendor1;
            }
        }
        return outputVendor;
    }

    public Customer getCustomer(String username){
        Customer outputCustomer = null;
        for(Customer customer1: customers){
            if(customer1.getEmail().equals(username)){
                outputCustomer = customer1;
            }
        }
        return outputCustomer;
    }


    public Boolean isRegisteredVendor(String username){
        for(Vendor vendor1: vendors){
            if(vendor1.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public Boolean isRegisteredCustomer(String username){
        for(Customer each: customers){
            if(each.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }
}
