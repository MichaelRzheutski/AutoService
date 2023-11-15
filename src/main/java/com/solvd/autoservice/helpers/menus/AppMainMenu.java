package com.solvd.autoservice.helpers.menus;

import com.solvd.autoservice.car.Car;
import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.car.SparePart.calcSparePartCost;
import static com.solvd.autoservice.helpers.ConsoleColors.*;
import static com.solvd.autoservice.persons.Customer.showCustomers;
import static com.solvd.autoservice.persons.Mechanic.showMechanics;

public final class AppMainMenu {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();
    private static final AutoServMenu AUTO_SERV_MENU = new AutoServMenu();

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

    Car car = new Car();

    public void mainMenu() throws NotNumberException {
        try (Scanner scanner = new Scanner(System.in)) {
            // Main menu
            boolean isExit = false;
            int option;

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
                    case 1 -> AUTO_SERV_MENU.autoServMenu(scanner, isExit);
                    case 2 -> showCustomers(OBJECTS_CREATOR.createCustomersTreeSet());
                    case 3 -> car.showCars(OBJECTS_CREATOR.createCarList(),
                            SparePart.calcSparePartCost(OBJECTS_CREATOR.createCarList(), OBJECTS_CREATOR.createSpareParts()));
                    case 4 -> SparePart.showSpareParts(OBJECTS_CREATOR.createCarList(),
                            calcSparePartCost(OBJECTS_CREATOR.createCarList(), OBJECTS_CREATOR.createSpareParts()));
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