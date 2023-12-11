package com.solvd.autoservice.enums;

public enum CarMenuItems {
    AUTOSERVICE_BMWX6("BMW X6"),
    AUTOSERVICE_TOYOTA_LAND_CRUISER("Toyota LandCruiser"),
    AUTOSERVICE_MERCEDES_BENZ("Mercedes Benz");

    private final String carMenuItem;

    CarMenuItems(String carMenuItem) {
        this.carMenuItem = carMenuItem;
    }

    public String getCarMenuItem() {
        return carMenuItem;
    }

    @Override
    public String toString() {
        return carMenuItem;
    }
}
