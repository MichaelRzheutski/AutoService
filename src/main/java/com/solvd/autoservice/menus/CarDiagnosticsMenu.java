package com.solvd.autoservice.menus;

import com.solvd.autoservice.enums.CarMenuItems;
import com.solvd.autoservice.enums.GeneralMenuItems;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.car.CarDiagnostics.checkCar;
import static com.solvd.autoservice.enums.ConsoleColors.*;

public final class CarDiagnosticsMenu {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Car diagnostics menu
    public void showDiagnosticsMenu(Scanner scanner) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                LOGGER.info(ANSI_GREEN + "Выберите авто для диагностики:" + ANSI_RESET);
                LOGGER.info("[1]. " + CarMenuItems.AUTOSERVICE_BMWX6);
                LOGGER.info("[2]. " + CarMenuItems.AUTOSERVICE_TOYOTA_LAND_CRUISER);
                LOGGER.info("[3]. " + CarMenuItems.AUTOSERVICE_MERCEDES_BENZ);
                LOGGER.info("[4]. " + GeneralMenuItems.AUTOSERVICE_PREVIOUS_MENU);
                LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> checkCar.apply(OBJECTS_CREATOR.bmwX6Diagnostics);
                        case 2 -> checkCar.apply(OBJECTS_CREATOR.toyotaLandCruiserDiagnostics);
                        case 3 -> checkCar.apply(OBJECTS_CREATOR.mercedesBenzDiagnostics);
                        case 4 -> isExit = true;
                        case 5 -> throw new OutOfMenuBoundsException(
                                "Введён пункт меню " + option + " свыше доступных", option - 1);
                        case -1 -> throw new NegativeValueException("Введено негативное число", option);
                        default -> LOGGER.info(String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
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