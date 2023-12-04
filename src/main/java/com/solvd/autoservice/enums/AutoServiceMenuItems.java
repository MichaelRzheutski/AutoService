package com.solvd.autoservice.enums;

public enum AutoServiceMenuItems {
    AUTOSERVICE_CAR_DIAGNOSTICS("Диагностика автомобиля"),
    AUTOSERVICE_REPAIRMENT_TIME_CALCULATOR("Рассчитать время ремонта автомобиля"),
    AUTOSERVICE_REPAIRMENT_COST_CALCULATOR("Рассчитать стоимость ремонта автомобиля");

    private final String menuItem;

    AutoServiceMenuItems(String menuItem) {
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
