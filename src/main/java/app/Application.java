package app;

import accounts.Account;
import accounts.Vendor;
import additionaltools.InputValidatorMenu;
import additionaltools.MenuPrompt;
import namazon.Namazon;

public class Application {

    public static void main(String[] args) {
        MenuPrompt menu = new MenuPrompt();
        Namazon namazon = new Namazon();
        Account user = menu.mainMenuLoop(namazon);
    }
}
