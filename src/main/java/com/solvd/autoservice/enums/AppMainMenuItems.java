package com.solvd.autoservice.enums;

public enum AppMainMenuItems {
    AUTOSERVICE_SERVICES("Показать список услуг автосервиса"),
    AUTOSERVICE_CLIENTS("Показать список клиентов автосервиса"),
    AUTOSERVICE_CARS("Показать список машин обслуживающихся в автосервисе"),
    AUTOSERVICE_SPARE_PARTS_SHOP("Магазин автозапчастей"),
    AUTOSERVICE_AUTOMECHANICS("Показать список автомехаников");

    private final String appMainMenuItem;

    AppMainMenuItems(String appMainMenuItem) {
        this.appMainMenuItem = appMainMenuItem;
    }

    public String getAppMainMenuItem() {
        return appMainMenuItem;
    }

    @Override
    public String toString() {
        return appMainMenuItem;
    }

}
