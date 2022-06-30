package additionaltools;

import accounts.Account;
import accounts.Customer;
import accounts.Vendor;
import exceptions.AccountAuthenticationException;
import exceptions.NameNotValidException;
import exceptions.PasswordValidationException;
import exceptions.UsernameValidationException;
import namazon.Namazon;
import products.Product;
import products.ProductCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.in;

public class MenuPrompt {

    private InputValidatorMenu inputValidatorMenu;
    private BufferedReader br;

    public MenuPrompt() {
        inputValidatorMenu = new InputValidatorMenu();
        br = new BufferedReader(new InputStreamReader(in));
    }

    public Account mainMenuLoop(Namazon namazon){
        MenuPrompt menu = new MenuPrompt();
        Boolean exit = false;

        Account user = null;
        while(!exit){
            user = menu.mainMenu(namazon);
            if(user != null){
                exit = true;
            }
        }
        return user;
    }

    public Account mainMenu(Namazon namazon){
        MenuPrompt menu = new MenuPrompt();
        Integer choice = menu.firstOptionsMenu();
        Account userAcc = null;
        switch(choice){
            case 2:
                userAcc = menu.signUpMenu(namazon);
                System.out.println("As part of this demo you have been assigned some mock orders and a mock inventory.\nYou can navigate options once you sign in.\n");
//                userAcc.addMockOrders();
//                userAcc.addMockInventory();
                System.out.println("Thank you for signing up! Please sign in now with your new account information :)\n");
                mainMenuLoop(namazon);
                break;
            case 1:
                userAcc = menu.signInMenu(namazon);
                //
                break;
            default:
                System.out.println("Thanks for using Namazon!");
                System.exit(0);
                break;
        }
        return userAcc;
    }

    public Integer firstOptionsMenu(){
        System.out.println("Welcome to Namazon!");
        String prompt = "What would you like to do?" +
                "\n2 - Sign Up" +
                "\n1 - Sign In" +
                "\n0 - Exit" +
                "\nEnter your choice now: ";
        String errorMssg = "Please enter a valid choice";
        return inputValidatorMenu.getIntegerMenu(prompt, errorMssg, 2);
    }

    public void vendorMenu(Vendor vendor){
        Integer choice = vendorMenuOptions();
        switch (choice){
            case 9:
                vendor.addProduct(productPrompt());
                break;
            case 8:
                //Remove Product form Inventory
                break;
            case 7:
                //Cancel Order
                break;
            case 6:
                //Mark Product as showcased
                break;
            case 5:
                //Track Shipping
                break;
            case 4:
                //View Selling Stats
                break;
            case 3:
                //View Search Products by Category
                break;
            case 2:
                //Search Products by Name
                break;
            case 1:
                //Display All Orders
                break;
            default:
                //main menu
                break;
        }
    }

    public Product pickProductToRemovePrompt(Vendor vendor){
        return null;
    }


    public String displayProductsInInventoryMenu(List<Product> productList){
        String str = "";
        for(int i = 0; i < productList.size(); i++){
            str += String.format("%d - %s", i, productList.get(i).toString()) + "\n";
        }
        str += "\nEnter your choice now: ";
        return str;
    }

    public String getBrandPrompt() throws NameNotValidException {
        String mssg = "Enter your company brand name now: ";
        String errorMssg = "User only letters for your first name and do not include white spaces.";

        String inputStr = null;
        while(true){
            System.out.println(mssg);
            try {
                inputStr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(inputValidatorMenu.isSimpleStringValid(inputStr)){
                return inputStr;
            }else{
                throw new NameNotValidException("Name is not valid. Use only alphabetic characters.");
            }
        }
    }

    public String getNameOfProductPrompt() throws NameNotValidException {
        InputValidatorMenu inputValidatorMenu = new InputValidatorMenu();
        String mssg = "Enter your name of the Product to add now: ";
        String errorMssg = "User only letters for your first name and do not include white spaces.";

        String inputStr = null;
        while(true){
            System.out.println(mssg);
            try {
                inputStr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(inputValidatorMenu.isSimpleStringValid(inputStr)){
                return inputStr;
            }else{
                throw new NameNotValidException("Name is not valid. Use only alphabetic characters.");
            }
        }
    }

    public ProductCategory pickCategoryPrompt() {
        InputValidatorMenu inputValidatorMenu = new InputValidatorMenu();
        String mssg =   "Pick a category for the product" +
                "\n4 - Electronics" +
                "\n2 - Athletics" +
                "\n1 - Clothing" +
                "\n0 - Home-Applications" +
                "\n\nEnter your choice now: ";
        String errorMssg = "Pick a valid choice. Try again.";
        Integer choice = inputValidatorMenu.getIntegerMenu(mssg, errorMssg, 4);
        ProductCategory category = null;
        switch (choice){
            case 3:
                category = ProductCategory.ELECTRONICS;
                break;
            case 2:
                category = ProductCategory.ATHLETICS;
                break;
            case 1:
                category = ProductCategory.CLOTHING;
                break;
            case 0:
                category = ProductCategory.HOME_APPLIANCE;
                break;
            default:
                return pickCategoryPrompt();
        }
        return category;
    }
    public Product productPrompt(){
        //User Enter's Product information
        //Name
        String productName = null;
        try {
            productName = getNameOfProductPrompt();
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }
        //Category
        ProductCategory category = pickCategoryPrompt();
        //Price
        Double price = inputValidatorMenu.getDoubleMenu("Enter selling price of product: ", "Invalid value, Try again.");
        return new Product(productName, category, price);
    }


    public Integer vendorMenuOptions(){
        String prompt = "What would you like to do?" +
                "\n9 - Add Product to Inventory" +
                "\n8 - Remove Product from Inventory" +
                "\n7 - Cancel Order" +
                "\n6 - Mark Product as showcased" +
                "\n5 - Track Shipping" +
                "\n4 - View Selling Stats" +
                "\n3 - Search Products by Category" +
                "\n2 - Search Products by Name" +
                "\n1 - Display All Orders " +
                "\n0 - Return to main menu" +
                "\nEnter your choice now: ";
        String errorMssg = "Please enter a valid choice";
        return inputValidatorMenu.getIntegerMenu(prompt, errorMssg, 8);
    }



    public Account signInMenu(Namazon namazon){
        String email = inputValidatorMenu.signInPromptUsername("Enter Username/Email: ", "Email is not in a valid format. Please try again.");
        String password = inputValidatorMenu.signInPromptPassword("Enter Password: ", "");
        Account user = null;
        try {
            user = namazon.signIn(email, password);
        } catch (UsernameValidationException e) {
            e.printStackTrace();
        } catch (PasswordValidationException e) {
            e.printStackTrace();
        }
        return user;
    }


    public Account signUpMenu(Namazon namazon){
        if(vendorORCustomerMenu() == 2) {
            return getVendorInfoMenu(namazon);
        }else if(vendorORCustomerMenu() == 1){
            return getCustomerInfoMenu(namazon);
        }else{
            mainMenuLoop(namazon);
        }
        return null;
    }

    public Integer vendorORCustomerMenu(){
        String prompt = "What kind of account would you like?" +
                "\n2 - Vendor" +
                "\n1 - Customer" +
                "\n0 - Go Back to main Menu" +
                "\nEnter your choice now: ";
        String errorMssg = "Please enter a valid choice";
        return inputValidatorMenu.getIntegerMenu(prompt, errorMssg, 2);
    }


    public Customer getCustomerInfoMenu(Namazon namazon){
        String firstName = null;
        try {
            firstName = inputValidatorMenu.getNamePrompt("first");
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }

        String lastName = null;
        try {
            lastName = inputValidatorMenu.getNamePrompt("last");
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }

        String email = inputValidatorMenu.getUsernameMenu();

        String password = inputValidatorMenu.getPasswordMenu();

        Customer tempCustomer = null;
        try {
            tempCustomer  = namazon.signUpAsCustomer(firstName, lastName, email, password);
        } catch (AccountAuthenticationException e) {
            e.printStackTrace();
        }
        if(tempCustomer == null){
            return getCustomerInfoMenu(namazon);
        }
        return  tempCustomer;
    }


    public Vendor getVendorInfoMenu(Namazon namazon){
        String brandName = null;
        try {
            brandName = getBrandPrompt();
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }

        String firstName = null;
        try {
            firstName = inputValidatorMenu.getNamePrompt("first");
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }

        String lastName = null;
        try {
            lastName = inputValidatorMenu.getNamePrompt("last");
        } catch (NameNotValidException e) {
            e.printStackTrace();
        }

        String email = inputValidatorMenu.getUsernameMenu();

        String password = inputValidatorMenu.getPasswordMenu();

        Vendor tempVendor = null;
        try {
            tempVendor  = namazon.signUpAsVendor(brandName, firstName, lastName, email, password);
        } catch (AccountAuthenticationException e) {
            e.printStackTrace();
        }
        if(tempVendor == null){
            return getVendorInfoMenu(namazon);
        }
        return tempVendor;
    }
}
