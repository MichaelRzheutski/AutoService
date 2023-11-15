package com.solvd.autoservice.helpers.menus;

import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.car.CarDiagnostics.checkCar;
import static com.solvd.autoservice.helpers.ConsoleColors.*;

public final class CarDiagMenu {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Car diagnostics menu
    public void diagServiceMenu(Scanner scanner, boolean isExit) throws NotNumberException {
        int option;

        try {
            while (!isExit) {
                LOGGER.info(
                        String.format("%sВыберите авто для диагностики:%s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Диагностика BMW X6");
                LOGGER.info("[2]. Диагностика Toyota LandCruiser");
                LOGGER.info("[3]. Диагностика Mercedes Benz");
                LOGGER.info("[0]. Выход");

                while (true) {
                    if (scanner.hasNextInt()) {
                        option = scanner.nextInt();
                        break;
                    } else {
                        throw new NotNumberException("Вместо числа введена строка", scanner.next());
                    }
                }

                // Check value in accepted range, if out of range
                // throw the OutOfMenuBoundsException
                if (option > 3) {
                    option = 4;
                }

                switch (option) {
                    case 0 -> isExit = true;
                    case 1 -> checkCar(OBJECTS_CREATOR.bmwX6Diagnostics);
                    case 2 -> checkCar(OBJECTS_CREATOR.toyotaLandCruiserDiagnostics);
                    case 3 -> checkCar(OBJECTS_CREATOR.mercedesBenzDiagnostics);
                    case 4 -> throw new OutOfMenuBoundsException(
                            "Введён пункт меню " + option + " свыше доступных", option - 1);
                    case -1 -> throw new NegativeValueException("Введено негативное число", option);
                    default -> LOGGER.info(String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                            ANSI_RED, ANSI_RESET)
                    );
                }
            }
        } catch (NegativeValueException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, ошибочка! " + e + " " + e.getNumber() + "."
                    + " В классе: " + getClass() + ANSI_RESET);
        } catch (OutOfMenuBoundsException e) {
            LOGGER.debug(ANSI_RED +
                    "Ой, ошибочка! " + e + " " + e.getNumber() + "."
                    + " В классе: " + getClass());
        }
    }
}