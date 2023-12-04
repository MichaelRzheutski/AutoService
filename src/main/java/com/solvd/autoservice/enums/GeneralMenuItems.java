package com.solvd.autoservice.enums;

public enum GeneralMenuItems {
    AUTOSERVICE_PREVIOUS_MENU("Выйти в предыдущее меню"),
    AUTOSERVICE_EXIT("Выйти из программы");

    private final String menuItem;

    GeneralMenuItems(String menuItem) {
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
