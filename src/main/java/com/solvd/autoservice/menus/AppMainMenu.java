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

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;
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

    // Greeting a customer
    static {
        MY_LOGGER.info(
                String.format("%sДобро пожаловать в автосервис '4 колеса'!%s", ANSI_GREEN, ANSI_RESET)
        );
    }

    // Main menu
    public void mainMenu() {
        int option;

        try (SCANNER) {
            while (true) {
                MY_LOGGER.info(
                        String.format("%sПожалуйста, выберите одну из предложенных операций: %s",
                                ANSI_GREEN, ANSI_RESET)
                );
                MY_LOGGER.info("[1]. " + AppMainMenuItems.AUTOSERVICE_SERVICES);
                MY_LOGGER.info("[2]. " + AppMainMenuItems.AUTOSERVICE_CLIENTS);
                MY_LOGGER.info("[3]. " + AppMainMenuItems.AUTOSERVICE_CARS);
                MY_LOGGER.info("[4]. " + AppMainMenuItems.AUTOSERVICE_SPARE_PARTS_SHOP);
                MY_LOGGER.info("[5]. " + AppMainMenuItems.AUTOSERVICE_AUTOMECHANICS);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

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
                        default -> MY_LOGGER.debug(
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
            MY_LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}