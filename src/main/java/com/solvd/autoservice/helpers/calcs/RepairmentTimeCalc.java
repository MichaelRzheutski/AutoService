package com.solvd.autoservice.helpers.calcs;

import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.customlinkedlist.CustomLinkedList;
import com.solvd.autoservice.enums.CarMenuItems;
import com.solvd.autoservice.enums.GeneralMenuItems;
import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import com.solvd.autoservice.persons.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.enums.ConsoleColors.*;

public final class RepairmentTimeCalc {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Calculate car repairment time
    public static int calcRepairmentTime(Scanner scanner) {
        int option;
        boolean isExit = false;

        int bmwX6DiagnosticsTime;
        int toyotaLandCruiserDiagnosticsTime;
        int mercedesBenzDiagnosticsTime;
        int result = 0;

        try {
            while (!isExit) {
                LOGGER.info(ANSI_GREEN + "Выберите авто для подсчёта времени ремонта:" + ANSI_RESET);
                LOGGER.info("[1]. " + CarMenuItems.AUTOSERVICE_BMWX6);
                LOGGER.info("[2]. " + CarMenuItems.AUTOSERVICE_TOYOTA_LAND_CRUISER);
                LOGGER.info("[3]. " + CarMenuItems.AUTOSERVICE_MERCEDES_BENZ);
                LOGGER.info("[4]. " + GeneralMenuItems.AUTOSERVICE_PREVIOUS_MENU);
                LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> {
                            bmwX6DiagnosticsTime = repairmentTimeCalculator(
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.bmwX6.getSpareParts(),
                                    OBJECTS_CREATOR.alexeyPrivolnov
                            );
                            LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getCarForDiagnostics().getCarMake()
                                    + " в днях: " + ANSI_YELLOW + bmwX6DiagnosticsTime + "\n" + ANSI_RESET
                            );
                            result = bmwX6DiagnosticsTime;
                        }
                        case 2 -> {
                            toyotaLandCruiserDiagnosticsTime = repairmentTimeCalculator(
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.toyotaLandCruiser.getSpareParts(),
                                    OBJECTS_CREATOR.sergeyVlasov
                            );
                            LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getCarForDiagnostics()
                                            .getCarMake() + " в днях: " + ANSI_YELLOW
                                    + toyotaLandCruiserDiagnosticsTime + "\n" + ANSI_RESET
                            );
                            result = toyotaLandCruiserDiagnosticsTime;
                        }
                        case 3 -> {
                            mercedesBenzDiagnosticsTime = repairmentTimeCalculator(
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.mercedesBenz.getSpareParts(),
                                    OBJECTS_CREATOR.vladimirDolgin
                            );
                            LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getCarForDiagnostics()
                                            .getCarMake() + " в днях: " + ANSI_YELLOW
                                    + mercedesBenzDiagnosticsTime + "\n" + ANSI_RESET
                            );
                            result = mercedesBenzDiagnosticsTime;
                        }
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
                break;
            }
        } catch (NegativeValueException | OutOfMenuBoundsException | NotNumberException e) {
            LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + RepairmentTimeCalc.class.getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }

        return result;
    }

    // Method calculates total cost of repairment including diagnostics result,
    // damages severity, term of spare parts delivery and mechanic qualification
    public static int repairmentTimeCalculator(
            int diagnosticsTime, CustomLinkedList<SparePart> spareParts,
            Customer customer
    ) {

        int totalRepairmentTime = 0;
        int sparePartsDeliveryTime;
        String mechanicExpertise;

        for (SparePart sparePart : spareParts) {
            sparePartsDeliveryTime = sparePart.getDeliveryDays();
            totalRepairmentTime += (diagnosticsTime + sparePartsDeliveryTime);
        }

        mechanicExpertise = customer.getMechanic().getExpertise();

        if (mechanicExpertise.equals("мастер")) {
            totalRepairmentTime += 1;
        }
        if (mechanicExpertise.equals("специалист")) {
            totalRepairmentTime += 2;
        }
        if (mechanicExpertise.equals("стажёр")) {
            totalRepairmentTime += 3;
        }

        return totalRepairmentTime;
    }
}