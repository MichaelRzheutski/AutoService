package com.solvd.autoservice.helpers.calcs;

import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.helpers.ObjectsCreator;
import com.solvd.autoservice.persons.Mechanic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

import static com.solvd.autoservice.helpers.ConsoleColors.*;

public final class RepTimeCalc {
    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    // Calculate car repairment time
    public static int calculateRepTime(Scanner scanner, boolean isExit) {
        LOGGER.info(ANSI_GREEN + "Выберите автомобиль" + ANSI_RESET);
        LOGGER.info("[1]. BMW X6");
        LOGGER.info("[2]. Toyota Land Cruiser");
        LOGGER.info("[3]. Mercedes Benz");
        LOGGER.info("[0]. Выход");

        int option = scanner.nextInt();
        scanner.nextLine();

        int bmwX6DiagnosticsTime;
        int toyotaLandCruiserDiagnosticsTime;
        int mercedesBenzDiagnosticsTime;
        int result = 0;

        while (!isExit) {
            switch (option) {
                case 0 -> isExit = true;
                case 1 -> {
                    bmwX6DiagnosticsTime = repTimeCalculator(
                            OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsTime(),
                            OBJECTS_CREATOR.bmwX6.getSpareParts(),
                            OBJECTS_CREATOR.mechanicMap
                    );
                    LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                            OBJECTS_CREATOR.bmwX6Diagnostics.getCarForDiagnostics().getCarMake()
                            + " в днях: " + ANSI_YELLOW + bmwX6DiagnosticsTime + "\n" + ANSI_RESET
                    );
                    result = bmwX6DiagnosticsTime;
                }
                case 2 -> {
                    toyotaLandCruiserDiagnosticsTime = repTimeCalculator(
                            OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getDiagnosticsTime(),
                            OBJECTS_CREATOR.toyotaLandCruiser.getSpareParts(),
                            OBJECTS_CREATOR.mechanicMap
                    );
                    LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                            OBJECTS_CREATOR.toyotaLandCruiserDiagnostics.getCarForDiagnostics()
                                    .getCarMake() + " в днях: " + ANSI_YELLOW
                            + toyotaLandCruiserDiagnosticsTime + "\n" + ANSI_RESET
                    );
                    result = toyotaLandCruiserDiagnosticsTime;
                }
                case 3 -> {
                    mercedesBenzDiagnosticsTime = repTimeCalculator(
                            OBJECTS_CREATOR.mercedesBenzDiagnostics.getDiagnosticsTime(),
                            OBJECTS_CREATOR.mercedesBenz.getSpareParts(),
                            OBJECTS_CREATOR.mechanicMap
                    );
                    LOGGER.info(ANSI_GREEN + "Общее время ремонта " +
                            OBJECTS_CREATOR.mercedesBenzDiagnostics.getCarForDiagnostics()
                                    .getCarMake() + " в днях: " + ANSI_YELLOW
                            + mercedesBenzDiagnosticsTime + "\n" + ANSI_RESET
                    );
                    result = mercedesBenzDiagnosticsTime;
                }
                default -> LOGGER.info(
                        ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET + "\n"
                );
            }
            break;
        }

        return result;
    }

    // Method calculates total cost of repairment including diagnostics result,
    // damages severity and term of spare parts delivery
    public static int repTimeCalculator(
            int diagnosticsTime, SparePart[] spareParts,
            Map<Integer, Mechanic> mechanics
    ) {
        RepCostCalc.checkDiagnosticsResult(
                OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsResult(),
                OBJECTS_CREATOR.alexeyPrivolnovInvoice.getTotalCost(),
                OBJECTS_CREATOR.alexeyPrivolnov.getName(),
                OBJECTS_CREATOR.alexeyPrivolnov.getSurname(),
                OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsTime()
        );

        RepCostCalc.checkDamagesSeverity(
                OBJECTS_CREATOR.bmwX6Diagnostics.getDamagesSeverity(),
                OBJECTS_CREATOR.alexeyPrivolnovInvoice.getTotalCost(),
                OBJECTS_CREATOR.alexeyPrivolnov.getName(),
                OBJECTS_CREATOR.alexeyPrivolnov.getSurname(),
                OBJECTS_CREATOR.bmwX6Diagnostics.getDiagnosticsTime()
        );

        int totalRepairmentTime = 0;
        int sparePartsDeliveryTime;
        for (SparePart sparePart : spareParts) {
            sparePartsDeliveryTime = sparePart.getDeliveryDays();
            totalRepairmentTime = diagnosticsTime + sparePartsDeliveryTime;
        }

        String mechanicExpertise;

        for (Map.Entry<Integer, Mechanic> mechanic : mechanics.entrySet()) {

            mechanicExpertise = mechanic.getValue().getExpertise();

            if (mechanicExpertise.equals("мастер")) {
                totalRepairmentTime += 1;
                break;
            }
            if (mechanicExpertise.equals("специалист")) {
                totalRepairmentTime += 2;
                break;
            }
            if (mechanicExpertise.equals("стажёр")) {
                totalRepairmentTime += 3;
                break;
            }

        }

//        for (Mechanic mechanic : mechanics) {
//            mechanicExpertise = mechanic.getExpertise();
//
//            if (mechanicExpertise.equals("мастер")) {
//                totalRepairmentTime += 1;
//                break;
//            }
//            if (mechanicExpertise.equals("специалист")) {
//                totalRepairmentTime += 2;
//                break;
//            }
//            if (mechanicExpertise.equals("стажёр")) {
//                totalRepairmentTime += 3;
//                break;
//            }
//        }

        return totalRepairmentTime;
    }
}