package com.solvd.autoservice.helpers.menus;

import com.solvd.autoservice.car.Car;
import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

import static com.solvd.autoservice.car.SparePart.calcSparePartCost;
import static com.solvd.autoservice.helpers.ConsoleColors.*;
import static com.solvd.autoservice.persons.Customer.showCustomers;
import static com.solvd.autoservice.persons.Mechanic.showMechanics;

public final class AppMainMenu {
    public static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();
    private static final Car CAR = new Car();
    private static final SparePart SPARE_PART = new SparePart();
    public static final List<Car> CAR_LIST = OBJECTS_CREATOR.createCarList();
    private static final AutoServiceMenu AUTO_SERVICE_MENU = new AutoServiceMenu();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Greeting a customer
    static {
        LOGGER.info(
                String.format("%sДобро пожаловать в автосервис '4 колеса'!%s", ANSI_GREEN, ANSI_RESET)
        );
    }

    public void mainMenu() throws NotNumberException {
        try (Scanner scanner = new Scanner(System.in)) {
            // Main menu
            boolean isExit = false;
            int option;

            // TOTAL_SPARE_PART_COST
            List<Car> TOTAL_SPARE_PART_COST = calcSparePartCost(CAR_LIST);

            while (!isExit) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. Показать список услуг автосервиса");
                LOGGER.info("[2]. Показать список клиентов автосервиса");
                LOGGER.info("[3]. Показать список машин обслуживающихся в автосервисе");
                LOGGER.info("[4]. Магазин автозапчастей");
                LOGGER.info("[5]. Показать список автомехаников");
                LOGGER.info("[0]. Выйти из программы");

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
                if (option > 5) {
                    option = 6;
                }

                switch (option) {
                    case 0 -> isExit = true;
                    case 1 -> AUTO_SERVICE_MENU.showAutoServiceMenu(scanner, isExit);
                    case 2 -> showCustomers(OBJECTS_CREATOR.createCustomersTreeSet());
                    case 3 -> CAR.showFullCarInfo(OBJECTS_CREATOR.carList);
                    case 4 -> SPARE_PART.showSpareParts(TOTAL_SPARE_PART_COST);
                    case 5 -> showMechanics(OBJECTS_CREATOR.createMechanicMap());
                    case 6 -> throw new OutOfMenuBoundsException(
                            "Введён пункт меню " + option + " свыше доступных", option - 1);
                    case -1 -> throw new NegativeValueException("Введено негативное число", option);
                    default -> LOGGER.info(
                            String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
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