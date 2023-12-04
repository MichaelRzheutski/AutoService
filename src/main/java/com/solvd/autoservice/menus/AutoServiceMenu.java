package com.solvd.autoservice.menus;

import com.solvd.autoservice.enums.AutoServiceMenuItems;
import com.solvd.autoservice.enums.GeneralMenuItems;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.calcs.RepairmentCostCalc.calcRepairmentCost;
import static com.solvd.autoservice.helpers.calcs.RepairmentTimeCalc.calcRepairmentTime;

public final class AutoServiceMenu {
    private static final CarDiagnosticsMenu CAR_DIAGNOSTICS_MENU = new CarDiagnosticsMenu();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // AutoService menu
    public void showAutoServiceMenu(Scanner scanner) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                LOGGER.info(
                        String.format("%sУслуги доступные у нас в автосервисе:%s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. " + AutoServiceMenuItems.AUTOSERVICE_CAR_DIAGNOSTICS);
                LOGGER.info("[2]. " + AutoServiceMenuItems.AUTOSERVICE_REPAIRMENT_TIME_CALCULATOR);
                LOGGER.info("[3]. " + AutoServiceMenuItems.AUTOSERVICE_REPAIRMENT_COST_CALCULATOR);
                LOGGER.info("[4]. " + GeneralMenuItems.AUTOSERVICE_PREVIOUS_MENU);
                LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> CAR_DIAGNOSTICS_MENU.showDiagnosticsMenu(scanner);
                        case 2 -> calcRepairmentTime(scanner);
                        case 3 -> calcRepairmentCost(scanner);
                        case 4 -> isExit = true;
                        case 5 -> throw new OutOfMenuBoundsException(
                                "Введён пункт меню " + option + " свыше доступных", option - 1);
                        case -1 -> throw new NegativeValueException("Введено негативное число", option);
                        default -> LOGGER.info(
                                String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                        ANSI_RED, ANSI_RESET)
                        );
                    }
                } else {
                    throw new NotNumberException(
                            "вместо числа введена строка "
                                    + ANSI_YELLOW + scanner.next() + ANSI_RESET);
                }
            }
        } catch (NegativeValueException | OutOfMenuBoundsException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}