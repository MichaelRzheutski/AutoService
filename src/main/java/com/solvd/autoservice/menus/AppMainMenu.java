package com.solvd.autoservice.menus;

import com.solvd.autoservice.car.Car;
import com.solvd.autoservice.enums.AppMainMenuItems;
import com.solvd.autoservice.enums.GeneralMenuItems;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import com.solvd.autoservice.persons.Customer;
import com.solvd.autoservice.persons.Mechanic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.persons.Mechanic.showMechanics;

public final class AppMainMenu {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();
    private static final AutoServiceMenu AUTO_SERVICE_MENU = new AutoServiceMenu();
    private static final Car CAR = new Car();
    private static final Customer CUSTOMER = new Customer();
    private static final List<Car> CARS_LIST = OBJECTS_CREATOR.createCarList();
    static final Set<Customer> CUSTOMERS_SET = OBJECTS_CREATOR.createCustomersSet();
    static final Set<Mechanic> MECHANICS_SET = OBJECTS_CREATOR.createMechanicSet();
    private final Scanner SCANNER = new Scanner(System.in);

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

    // Main menu
    public void mainMenu() {
        int option;

        try (SCANNER) {
            while (true) {
                LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                LOGGER.info("[1]. " + AppMainMenuItems.AUTOSERVICE_SERVICES);
                LOGGER.info("[2]. " + AppMainMenuItems.AUTOSERVICE_CLIENTS);
                LOGGER.info("[3]. " + AppMainMenuItems.AUTOSERVICE_CARS);
                LOGGER.info("[4]. " + AppMainMenuItems.AUTOSERVICE_SPARE_PARTS_SHOP);
                LOGGER.info("[5]. " + AppMainMenuItems.AUTOSERVICE_AUTOMECHANICS);
                LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

                if (SCANNER.hasNextInt()) {
                    option = SCANNER.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> AUTO_SERVICE_MENU.showAutoServiceMenu(SCANNER);
                        case 2 -> CUSTOMER.showCustomers(CUSTOMERS_SET);
                        case 3 -> CAR.showFullCarInfo(CARS_LIST);
                        case 4 -> CAR.showSpareParts(CARS_LIST);
                        case 5 -> showMechanics(MECHANICS_SET);
                        case 6 -> throw new OutOfMenuBoundsException(
                                "Введён пункт меню " + option + " свыше доступных", option - 1);
                        case -1 -> throw new NegativeValueException("Введено негативное число", option);
                        default -> LOGGER.debug(
                                ANSI_RED + "Неверная операция, " +
                                        "попробуйте ещё раз!\n" + ANSI_RESET
                        );
                    }
                } else {
                    throw new NotNumberException(
                            "вместо числа введена строка "
                                    + ANSI_YELLOW + SCANNER.next() + ANSI_RESET);
                }
            }
        } catch (NegativeValueException | OutOfMenuBoundsException | NotNumberException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}