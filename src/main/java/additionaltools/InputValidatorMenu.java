package additionaltools;

import exceptions.NameNotValidException;
import jdk.jfr.Category;
import products.Product;
import products.ProductCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.in;

public class InputValidatorMenu {

    private BufferedReader br;

    public InputValidatorMenu() {

    }

    public Integer getIntegerMenu(String mssg, String errorMssg, int bound){
        String strInput ="";
        Integer temp = 0;
        boolean exit = false;
        boolean isInbounds = true;
        while(!exit){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isIntegerValid(strInput)){
                temp = Integer.parseInt(strInput);
                if(temp>=0 && temp<=bound){
                    exit = true;
                    break;
                }else{
                    isInbounds = false;
                }
            }
            if(!isInbounds){
                System.out.println(String.format("Enter values between 0 - %d", bound));
                isInbounds = true;
            }else{
                System.out.println(errorMssg);
            }
        }
        return temp;
    }

    public Integer getIntegerMenu(String mssg, String errorMssg){
        String strInput ="";
        Integer temp = 0;
        boolean exit = false;
        while(!exit){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isIntegerValid(strInput)){
                temp = Integer.parseInt(strInput);
                if(temp>=0){
                    exit = true;
                    break;
                }else{
                    System.out.println("Please Enter a number greater than zero.");
                }
            }
            System.out.println(errorMssg);
        }
        return temp;
    }

    public Double getDoubleMenu(String mssg, String errorMssg, Double bound){
        String strInput ="";
        Double temp = 0.0;
        boolean exit = false;
        boolean isInbounds = true;

        while(!exit){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isDoubleValid(strInput)){
                temp = Double.parseDouble(strInput);
                if(temp>0 && temp <=bound){
                    exit = true;
                    break;
                }else{
                    isInbounds = false;
                }
            }
            if(!isInbounds){
                System.out.println(String.format("Enter values between 0.00 - %2.2f", bound));
                isInbounds = true;
            }else{
                System.out.println(errorMssg);
            }

        }
        return temp;
    }

    public Double getDoubleMenu(String mssg, String errorMssg){
        String strInput ="";
        Double temp = 0.0;
        boolean exit = false;
        while(!exit){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isDoubleValid(strInput)){
                temp = Double.parseDouble(strInput);
                if(temp>0){
                    exit = true;
                    break;
                }else{
                    System.out.println("Please Enter a number greater than zero.");
                }
            }
            System.out.println(errorMssg);
        }
        return temp;
    }

    public Boolean isIntegerValid(String str){
        if(str.equals("")){
            return false;
        }
        int validCharacters = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                validCharacters++;
            }
        }
        if(validCharacters == str.length()){
            return true;
        }
        return false;
    }

    public Boolean isDoubleValid(String str){
        if(str.equals("")){
            return false;
        }
        //isMoneyValue - only two decimals are allowed (ex: 1.00 & 3.52 is okay, but 1.023 is not)
        int counter, periodCounter, excessDecimalDigits, offset;
        counter = periodCounter = excessDecimalDigits = 0;
        offset = 2;
        int decimalLocation = 0; //USED TO SEE WHERE THE PERIOD '.' IN A FLOAT IS
        for(int i = 0; i < str.length(); i++){
            if( Character.isDigit(str.charAt(i))) {  //ALLOWED CHARACTERS 0-9 AND '.' IN CASE OF FLOATS
                counter += 1;
            }
            if (str.charAt(i) == '.') {         //IN CASE OF FLOATS, ONLY ONE DECIMAL WOULD BE ALLOWED (1.0 IS OKAY TO ENTER)
                periodCounter += 1;
                decimalLocation = i;
            }
            if (i > (decimalLocation + offset)) {
                if (decimalLocation > 0) {
                    excessDecimalDigits += ((str.charAt(i)) - '0');  //ADDS ANY VALUES TRAILING A '.' AND ADDS THOSE VALUES UP
                }
            }
        }
        //IF STRING LENGTH IS SATISFIED, AND IS A VALID FORMAT
        if (((counter+periodCounter == str.length()) && periodCounter<= 1 && excessDecimalDigits == 0)){
            return true;
        }else{
            return false;
        }
    }

    public String getNamePrompt(String firstOrLastName) throws NameNotValidException {
        String mssg = "Enter your "+ firstOrLastName + " name now: ";
        String errorMssg = "User only letters for your first name and do not include white spaces.";

        String inputStr = null;
        while(true){
            System.out.println(mssg);
            try {
                inputStr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isNameValid(inputStr)){
                return inputStr;
            }else{
                throw new NameNotValidException("Name is not valid. Use only alphabetic characters and no spaces.");
            }
        }
    }

    public Boolean isNameValid(String str) {
        Integer validLetters = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i))) {
                validLetters++;
            }
        }
        if (validLetters == str.length()) {
            return true;
        }
        return false;
    }

    public Boolean isSimpleStringValid(String str) {
        Integer validLetters = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isAlphabetic(str.charAt(i)) || Character.isDigit(str.charAt(i)) || str.charAt(i) == ' '){
                validLetters++;
            }
        }
        if(validLetters == str.length()){
            return true;
        }
        return false;
    }

    public String signInPromptPassword(String mssg, String errorMssg) {
        Boolean isValidEmail = false;
        String strInput = "";
        while(!isValidPassword(strInput)){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isValidPassword(strInput)){
                isValidEmail = true;
                break;
            }
            System.out.println(errorMssg);
        }
        return strInput;
    }

    public Boolean isValidPassword(String str){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%^&+=])"
                + "(?=\\S+$).{8,20}$"; ;
        Pattern pattern = Pattern.compile(regex);
        if(str == null){
            return false;
        }
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public String signInPromptUsername(String mssg, String errorMssg){
        Boolean isValidEmail = false;
        String strInput = "";
        while(!isValidEmail(strInput)){
            System.out.println(mssg);
            try {
                strInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isValidEmail(strInput)){
               isValidEmail = true;
               break;
            }
            System.out.println(errorMssg);
        }
        return strInput;
    }

    public Boolean isValidEmail(String str){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public String getUsernameMenu() {
        InputValidatorMenu menu = new InputValidatorMenu();
        String usernameMssg = "Please enter your username (email): ";
        String usernameErrorMssg = "Please enter a valid email format";

        return menu.signInPromptUsername(usernameMssg, usernameErrorMssg);
    }

    public String getPasswordMenu() {
        InputValidatorMenu menu = new InputValidatorMenu();
        String mssg =   "Valid Passwords contain at least 8 characters and at most 20 characters.\n" +
                        "at least one digit. \n" +
                        "at least one upper case alphabet. \n" +
                        "at least one lower case alphabet. \n" +
                        "at least one special character which includes !@#$%&*()-+=^. \n" +
                        "don't contain any white space. \n\n" +
                        "Please enter a password now: ";

        String errorMssg = "Please enter a valid email format";
        return signInPromptPassword(mssg, errorMssg);
    }






}
