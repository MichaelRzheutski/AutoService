package com.solvd.autoservice;

import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.helpers.menus.AppMainMenu;

// Main: Runner class
public class Main {
    public static void main(String[] args) {
        // Show the AutoService menu
        AppMainMenu appMainMenu = new AppMainMenu();
        try {
            appMainMenu.mainMenu();
        } catch (NotNumberException e) {
            throw new RuntimeException(e);
        }
    }
}
