package com.solvd.autoservice.car;

import java.util.function.Function;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class CarDiagnostics extends Car {
    private Car carForDiagnostics;
    private String diagnosticsResult;
    private String damagesSeverity;
    private int diagnosticsTime;

    public CarDiagnostics(
            Car carForDiagnostics, String diagnosticsResult,
            String damagesSeverity, int diagnosticsTime) {
        this.carForDiagnostics = carForDiagnostics;
        this.diagnosticsResult = diagnosticsResult;
        this.damagesSeverity = damagesSeverity;
        this.diagnosticsTime = diagnosticsTime;
    }

    // Lambda expression which checks car
    public static Function<CarDiagnostics, CarDiagnostics> checkCar = (carDiagnostics) -> {
        MY_LOGGER.info(
                ANSI_GREEN + "Марка авто: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getCarMake() + ANSI_RESET
        );
        MY_LOGGER.info(
                ANSI_GREEN + "Год выпуска: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getCarManufactureYear() + ANSI_RESET
        );
        MY_LOGGER.info(
                ANSI_GREEN + "Пробег: " + ANSI_YELLOW +
                        carDiagnostics.getCarForDiagnostics().getMileage() + " км" + ANSI_RESET
        );
        MY_LOGGER.info(
                ANSI_GREEN + "Результат диагностики: " + ANSI_YELLOW +
                        carDiagnostics.getDiagnosticsResult() + ANSI_RESET
        );
        MY_LOGGER.info(
                ANSI_GREEN + "Степень повреждений: " + ANSI_YELLOW +
                        carDiagnostics.getDamagesSeverity() + ANSI_RESET
        );
        MY_LOGGER.info(
                ANSI_GREEN + "Диагностика проводилась дней: " + ANSI_YELLOW +
                        carDiagnostics.getDiagnosticsTime() + "\n" + ANSI_RESET
        );

        return carDiagnostics;
    };

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
