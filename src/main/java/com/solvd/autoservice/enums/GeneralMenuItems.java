package com.solvd.autoservice.enums;

public enum GeneralMenuItems {
    AUTOSERVICE_PREVIOUS_MENU("Выйти в предыдущее меню"),
    AUTOSERVICE_EXIT("Выйти из программы");

    private final String generalMenuItem;

    GeneralMenuItems(String generalMenuItem) {
        this.generalMenuItem = generalMenuItem;
    }

    public String getGeneralMenuItem() {
        return generalMenuItem;
    }

    @Override
    public String toString() {
        return generalMenuItem;
    }
}
