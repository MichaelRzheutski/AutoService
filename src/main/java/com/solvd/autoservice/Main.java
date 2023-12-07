package com.solvd.autoservice;

import com.solvd.autoservice.reflection.InfoAboutClass;
import com.solvd.autoservice.reflection.Reflection;

// Main class
public class Main {
    public static void main(String[] args) {
        // Show the AutoService menu
//        new AppMainMenu().mainMenu();

        // Show info about class
        new InfoAboutClass().getInfoAboutClass();
        // Use Reflection
        new Reflection().runReflection();
    }
}
