package com.solvd.autoservice.helpers.calcs;

import com.solvd.autoservice.exceptions.NegativeValueException;
import com.solvd.autoservice.exceptions.NotNumberException;
import com.solvd.autoservice.exceptions.OutOfMenuBoundsException;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.autoservice.enums.ConsoleColors.*;

public final class RepairmentCostCalc {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();


    // Calculate car repairment cost
    public static double calcRepairmentCost(Scanner scanner) {
        int option;
        boolean isExit = false;
        double alexeyPrivolnovInitialInvoice;
        double sergeyVlasovInitialInvoice;
        double vladimirDolginInitialInvoice;

        double result = 0;

        try {
            while (!isExit) {
                LOGGER.info(ANSI_GREEN + "Выберите автомобиль" + ANSI_RESET);
                LOGGER.info("[1]. BMW X6");
                LOGGER.info("[2]. Toyota Land Cruiser");
                LOGGER.info("[3]. Mercedes Benz");
                LOGGER.info("[4]. Выйти в предыдущее меню");
                LOGGER.info("[0]. Выйти из программы");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> {
                            alexeyPrivolnovInitialInvoice = repairmentCostCalculator(
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getCarForDiagnostics()
                                            .getCarManufactureYear(),
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsResult(),
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getDamagesSeverity(),
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.alexeyPrivolnovInvoice.getTotalCost()
                            );

                            LOGGER.info(ANSI_GREEN + "Общая стоимость ремонта " +
                                    OBJECTS_CREATOR.bmwX6Diagnostics.getCarForDiagnostics()
                                            .getCarMake() + ": " + ANSI_YELLOW +
                                    alexeyPrivolnovInitialInvoice + "$\n" + ANSI_RESET
                            );
                            result = alexeyPrivolnovInitialInvoice;
                        }
                        case 2 -> {
                            sergeyVlasovInitialInvoice = repairmentCostCalculator(
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getCarForDiagnostics()
                                            .getCarManufactureYear(),
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getDiagnosticsResult(),
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getDamagesSeverity(),
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.sergeyVlasovInvoice.getTotalCost()
                            );
                            LOGGER.info(ANSI_GREEN + "Общая стоимость ремонта " +
                                    OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getCarForDiagnostics()
                                            .getCarMake() + ": " + ANSI_YELLOW +
                                    sergeyVlasovInitialInvoice + "$\n" + ANSI_RESET
                            );
                            result = sergeyVlasovInitialInvoice;
                        }
                        case 3 -> {
                            vladimirDolginInitialInvoice = repairmentCostCalculator(
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getCarManufactureYear(),
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getDiagnosticsResult(),
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getDamagesSeverity(),
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getDiagnosticsTime(),
                                    OBJECTS_CREATOR.vladimirDolginInvoice.getTotalCost()
                            );
                            LOGGER.info(ANSI_GREEN + "Общая стоимость ремонта " +
                                    OBJECTS_CREATOR.mercedesBenzDiagnostics.getCarForDiagnostics()
                                            .getCarMake() + ": " + ANSI_YELLOW +
                                    vladimirDolginInitialInvoice + "$\n" + ANSI_RESET
                            );
                            result = vladimirDolginInitialInvoice;
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
                    + RepairmentCostCalc.class.getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }

        return result;
    }

    // Method checks car manufacture year and
    // returns repairment cost depends on year
    public static double checkCarManufactureYear(
            int carManufactureYear, double repairmentCost
    ) {

        if (carManufactureYear >= 2020 && carManufactureYear < 2025) {
            repairmentCost *= 1.5;
        }

        return repairmentCost;
    }

    // Method checks diagnostics result and adds additional price to repairment cost
    // depends on diagnostics result
    public static double checkDiagnosticsResult(
            String diagnosticsResult, double repairmentCost,
            int diagnosticsTime
    ) {
//        totalDiagnosticsTime = diagnosticsTime;

        if (diagnosticsResult.equals("Требуется замена моторного масла")) {
            repairmentCost += 20.00;
        }

        if (diagnosticsResult.equals("Требуется замена шин")) {
            repairmentCost += 50.00;
//            totalDiagnosticsTime += 1;
        }

        if (diagnosticsResult.equals("Требуется замена тормозных колодок")) {
            repairmentCost += 80.00;
//            totalDiagnosticsTime += 2;
        }

        return repairmentCost;
    }

    // Method checks car damages severity
    public static double checkDamagesSeverity(
            String damagesSeverity, double repairmentCost,
            int diagnosticsTime
    ) {
//        int totalDiagnosticsTime = diagnosticsTime;

        if (damagesSeverity.equals("Лёгкие повреждения")) {
            repairmentCost = repairmentCost + 30.00;
//            totalDiagnosticsTime += 1;
        }

        if (damagesSeverity.equals("Серьёзные повреждения")) {
            repairmentCost = repairmentCost + 50.00;
//            totalDiagnosticsTime += 2;
        }

        return repairmentCost;
    }

    // Method calculates total cost of repairment
    public static double repairmentCostCalculator(
            int carManufactureYear, String diagnosticsResult,
            String damagesSeverity, int diagnosticsTime, double repairmentCost
    ) {
        double tempCost;
        double totalRepairmentCost;
        tempCost = checkCarManufactureYear(carManufactureYear, repairmentCost);

        tempCost = checkDiagnosticsResult(
                diagnosticsResult, tempCost, diagnosticsTime
        );

        tempCost = checkDamagesSeverity(damagesSeverity, tempCost, diagnosticsTime);
        totalRepairmentCost = tempCost;

        return totalRepairmentCost;
    }

}


