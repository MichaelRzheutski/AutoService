package com.solvd.autoservice.car;

import com.solvd.autoservice.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.solvd.autoservice.helpers.ConsoleColors.*;

// Car: Represents make, model, manufacture year, mileage and spare parts
public class Car implements Washable, Paintable, BodyRepairable,
        Electronicable, Modernizable {
    protected String carMake;
    protected int carManufactureYear;
    protected int mileage;
    protected SparePart[] spareParts;
    private String carWash;
    private String carPaint;
    private String bodyRepairment;
    private String electronicsRepairment;
    private String modernization;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public Car() {
    }

    public Car(
            String carMake, int carManufactureYear,
            int mileage, SparePart[] spareParts
    ) {
        this.carMake = carMake;
        this.carManufactureYear = carManufactureYear;
        this.mileage = mileage;
        this.spareParts = spareParts;
    }

    public Car(
            String carMake, int carManufactureYear,
            int mileage
    ) {
        this.carMake = carMake;
        this.carManufactureYear = carManufactureYear;
        this.mileage = mileage;
    }

    public Car(
            String carMake, int carManufactureYear
    ) {
        this.carMake = carMake;
        this.carManufactureYear = carManufactureYear;
    }

    @Override
    public String isCarWashed(boolean isCarWashed) {
        String result;

        if (isCarWashed) {
            setCarWashed("Машина помыта");
        } else {
            setCarWashed("Машина не помыта");
        }
        result = getCarWashed();
        LOGGER.info(
                ANSI_GREEN + "Внешний вид машины: " + ANSI_RESET
                        + getCarWashed() + ANSI_RESET
        );

        return result;
    }

    @Override
    public String isCarPainted(boolean isCarPainted) {
        String result;

        if (isCarPainted) {
            setCarPainted("Машина перекрашена");
        } else {
            setCarPainted("Машина не перекрашивалась");
        }
        result = getCarPainted();
        LOGGER.info(
                ANSI_GREEN + "Покраска кузова: " + ANSI_RESET
                        + getCarPainted() + ANSI_RESET
        );

        return result;
    }

    @Override
    public String isCarBodyRepaired(boolean isCarBodyRepaired) {
        String result;

        if (isCarBodyRepaired) {
            setCarBodyRepaired("Проводился ремонт кузова");
        } else {
            setCarBodyRepaired("Ремонт кузова не проводился");
        }
        result = getCarBodyRepaired();
        LOGGER.info(
                ANSI_GREEN + "Состояние кузова: " + ANSI_RESET
                        + getCarBodyRepaired() + ANSI_RESET
        );

        return result;
    }

    @Override
    public String isCarElectronicsRepaired(boolean isCarElectronicsRepaired) {
        String result;

        if (isCarElectronicsRepaired) {
            setCarElectronicsRepaired("Проведён ремонт и замена электроники");
        } else {
            setCarElectronicsRepaired("Ремонта и замены электроники не проводилось");
        }
        result = getCarElectronicsRepaired();
        LOGGER.info(
                ANSI_GREEN + "Состояние электроники: " + ANSI_RESET
                        + getCarElectronicsRepaired() + ANSI_RESET
        );

        return result;
    }

    @Override
    public String isCarModernized(boolean isCarModernized) {
        String result;

        if (isCarModernized) {
            setCarModernized("Автомобиль модернизирован");
        } else {
            setCarModernized("Модернизация не проводилась");
        }
        result = getCarModernized();
        LOGGER.info(
                ANSI_GREEN + "Модернизация: " + ANSI_RESET
                        + getCarModernized() + "\n" + ANSI_RESET
        );

        return result;
    }

    public final List<Car> showShortCarInfo(List<Car> cars) {
        for (Car car : cars) {
            LOGGER.info(
                    ANSI_GREEN + "Марка автомобиля: " + ANSI_YELLOW
                            + car.getCarMake() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Год выпуска: " + ANSI_YELLOW
                            + car.getCarManufactureYear() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Пробег км: " + ANSI_YELLOW
                            + car.getMileage() + ANSI_RESET
            );

        }

        return cars;
    }

    public final List<Car> showFullCarInfo(List<Car> cars) {
        for (Car car : cars) {
            LOGGER.info(
                    ANSI_GREEN + "Марка автомобиля: " + ANSI_YELLOW
                            + car.getCarMake() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Год выпуска: " + ANSI_YELLOW
                            + car.getCarManufactureYear() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Пробег км: " + ANSI_YELLOW
                            + car.getMileage() + ANSI_RESET
            );

            showInterfaces(car);
        }

        return cars;
    }

    public final void showInterfaces(Car car) {
        if (car.getCarMake().equals("BMW X6")) {
            isCarWashed(true);
            isCarPainted(false);
            isCarBodyRepaired(true);
            isCarElectronicsRepaired(false);
            isCarModernized(true);
        }

        if (car.getCarMake().equals("Toyota Land Cruiser")) {
            isCarWashed(false);
            isCarPainted(true);
            isCarBodyRepaired(false);
            isCarElectronicsRepaired(true);
            isCarModernized(false);
        }

        if (car.getCarMake().equals("Mercedes Benz")) {
            isCarWashed(true);
            isCarPainted(true);
            isCarBodyRepaired(false);
            isCarElectronicsRepaired(false);
            isCarModernized(true);
        }

    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public int getCarManufactureYear() {
        return carManufactureYear;
    }

    public void setCarManufactureYear(int carManufactureYear) {
        this.carManufactureYear = carManufactureYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public SparePart[] getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(SparePart[] spareParts) {
        this.spareParts = spareParts;
    }

    public String getCarWashed() {
        return carWash;
    }

    public void setCarWashed(String carWash) {
        this.carWash = carWash;
    }

    public String getCarPainted() {
        return carPaint;
    }

    public void setCarPainted(String carPaint) {
        this.carPaint = carPaint;
    }

    public String getCarBodyRepaired() {
        return bodyRepairment;
    }

    public void setCarBodyRepaired(String bodyRepairment) {
        this.bodyRepairment = bodyRepairment;
    }

    public String getCarElectronicsRepaired() {
        return electronicsRepairment;
    }

    public void setCarElectronicsRepaired(String electronicsRepairment) {
        this.electronicsRepairment = electronicsRepairment;
    }

    public String getCarModernized() {
        return modernization;
    }

    public void setCarModernized(String modernization) {
        this.modernization = modernization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carManufactureYear == car.carManufactureYear
                && mileage == car.mileage
                && Objects.equals(carMake, car.carMake)
                && Arrays.equals(spareParts, car.spareParts)
                && Objects.equals(carWash, car.carWash)
                && Objects.equals(carPaint, car.carPaint)
                && Objects.equals(bodyRepairment, car.bodyRepairment)
                && Objects.equals(electronicsRepairment, car.electronicsRepairment)
                && Objects.equals(modernization, car.modernization);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(carMake, carManufactureYear, mileage,
                carWash, carPaint, bodyRepairment, electronicsRepairment, modernization);
        result = 31 * result + Arrays.hashCode(spareParts);
        return result;
    }

    @Override
    public String toString() {
        return ANSI_GREEN + "Марка авто: " + ANSI_YELLOW + carMake + ", " + ANSI_RESET +
                ANSI_GREEN + "Год выпуска авто: " + ANSI_YELLOW + carManufactureYear + ", " + ANSI_RESET +
                ANSI_GREEN + "Пробег: " + ANSI_YELLOW + mileage + ", " + ANSI_RESET +
                ANSI_GREEN + "Запчасти: " + ANSI_YELLOW + Arrays.toString(spareParts) + ", " + ANSI_RESET;
    }
}
