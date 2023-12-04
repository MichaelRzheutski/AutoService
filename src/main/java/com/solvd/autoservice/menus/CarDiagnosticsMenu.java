package com.solvd.autoservice.menus;

import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.car.CarDiagnostics.checkCar;
import static com.solvd.autoservice.helpers.ConsoleColors.*;

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
                LOGGER.info(
                        String.format("%sВыберите авто для диагностики:%s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Диагностика BMW X6");
                LOGGER.info("[2]. Диагностика Toyota LandCruiser");
                LOGGER.info("[3]. Диагностика Mercedes Benz");
                LOGGER.info("[4]. Выйти в предыдущее меню");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> checkCar(OBJECTS_CREATOR.bmwX6Diagnostics);
                        case 2 -> checkCar(OBJECTS_CREATOR.toyotaLandCruiserDiagnostics);
                        case 3 -> checkCar(OBJECTS_CREATOR.mercedesBenzDiagnostics);
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