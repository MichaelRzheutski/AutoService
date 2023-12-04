package com.solvd.autoservice.enums;

public enum CarDiagnosticsMenuItems {
    AUTOSERVICE_BMWX6_DIAGNOSTICS("Диагностика BMW X6"),
    AUTOSERVICE_TOYOTA_LAND_CRUISER_DIAGNOSTICS("Диагностика Toyota LandCruiser"),
    AUTOSERVICE_MERCEDES_BENZ_DIAGNOSTICS("Диагностика Mercedes Benz");

    private final String menuItem;

    CarDiagnosticsMenuItems(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }

    @Override
    public String toString() {
        return menuItem;
    }
}
