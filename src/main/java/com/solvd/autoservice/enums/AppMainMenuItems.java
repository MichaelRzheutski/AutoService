package com.solvd.autoservice.enums;

public enum AppMainMenuItems {
    AUTOSERVICE_SERVICES("Показать список услуг автосервиса"),
    AUTOSERVICE_CLIENTS("Показать список клиентов автосервиса"),
    AUTOSERVICE_CARS("Показать список машин обслуживающихся в автосервисе"),
    AUTOSERVICE_SPARE_PARTS_SHOP("Магазин автозапчастей"),
    AUTOSERVICE_AUTOMECHANICS("Показать список автомехаников");

    private final String menuItem;

    AppMainMenuItems(String menuItem) {
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
