package com.solvd.autoservice.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.autoservice.helpers.ConsoleColors.*;

public class CarDiagnostics extends Car {
    private Car carForDiagnostics;
    private String diagnosticsResult;
    private String damagesSeverity;
    private int diagnosticsTime;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public CarDiagnostics(
            Car carForDiagnostics, String diagnosticsResult,
            String damagesSeverity, int diagnosticsTime) {
        this.carForDiagnostics = carForDiagnostics;
        this.diagnosticsResult = diagnosticsResult;
        this.damagesSeverity = damagesSeverity;
        this.diagnosticsTime = diagnosticsTime;
    }

    public static CarDiagnostics checkCar(CarDiagnostics carDiagnostics) {
        LOGGER.info(
                ANSI_GREEN + "Марка авто: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getCarMake() + ANSI_RESET
        );
        LOGGER.info(
                ANSI_GREEN + "Год выпуска: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getCarManufactureYear() + ANSI_RESET
        );
        LOGGER.info(
                ANSI_GREEN + "Пробег: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getMileage() + " км" + ANSI_RESET
        );
        LOGGER.info(
                ANSI_GREEN + "Результат диагностики: " + ANSI_YELLOW +
                        carDiagnostics.getDiagnosticsResult() + ANSI_RESET
        );
        LOGGER.info(
                ANSI_GREEN + "Степень повреждений: " + ANSI_YELLOW +
                        carDiagnostics.getDamagesSeverity() + ANSI_RESET
        );
        LOGGER.info(
                ANSI_GREEN + "Диагностика проводилась дней: " + ANSI_YELLOW +
                        carDiagnostics.getDiagnosticsTime() + "\n" + ANSI_RESET
        );

        return carDiagnostics;
    }

    public Car getCarForDiagnostics() {
        return carForDiagnostics;
    }

    public void setCarForDiagnostics(Car carForDiagnostics) {
        this.carForDiagnostics = carForDiagnostics;
    }

    public String getDiagnosticsResult() {
        return diagnosticsResult;
    }

    public void setDiagnosticsResult(String diagnosticsResult) {
        this.diagnosticsResult = diagnosticsResult;
    }

    public String getDamagesSeverity() {
        return damagesSeverity;
    }

    public void setDamagesSeverity(String damagesSeverity) {
        this.damagesSeverity = damagesSeverity;
    }

    public int getDiagnosticsTime() {
        return diagnosticsTime;
    }

    public void setDiagnosticsTime(int diagnosticsTime) {
        this.diagnosticsTime = diagnosticsTime;
    }

}
