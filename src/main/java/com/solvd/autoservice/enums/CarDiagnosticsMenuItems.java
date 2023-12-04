package com.solvd.autoservice.enums;

public enum CarDiagnosticsMenuItems {
    AUTOSERVICE_BMWX6_DIAGNOSTICS("Диагностика BMW X6"),
    AUTOSERVICE_TOYOTA_LAND_CRUISER_DIAGNOSTICS("Диагностика Toyota LandCruiser"),
    AUTOSERVICE_MERCEDES_BENZ_DIAGNOSTICS("Диагностика Mercedes Benz");

    private final String carDiagnosticsMenuItem;

    CarDiagnosticsMenuItems(String carDiagnosticsMenuItem) {
        this.carDiagnosticsMenuItem = carDiagnosticsMenuItem;
    }

    public String getCarDiagnosticsMenuItem() {
        return carDiagnosticsMenuItem;
    }

    @Override
    public String toString() {
        return carDiagnosticsMenuItem;
    }
}
