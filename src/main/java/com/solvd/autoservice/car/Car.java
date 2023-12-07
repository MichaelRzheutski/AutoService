package com.solvd.autoservice.car;

import com.solvd.autoservice.customlinkedlist.CustomLinkedList;
import com.solvd.autoservice.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Consumer;

import static com.solvd.autoservice.car.SparePart.calculateSparePartsCost;
import static com.solvd.autoservice.enums.ConsoleColors.*;

// Car: Represents make, model, manufacture year, mileage and spare parts
public class Car implements Washable, Paintable, BodyRepairable,
        Electronicable, Modernizable {
    protected String carMake;
    protected int carManufactureYear;
    protected int mileage;
    private String carWash;
    private String carPaint;
    private String bodyRepairment;
    private String electronicsRepairment;
    private String modernization;
    private CustomLinkedList<SparePart> spareParts;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public Car() {
    }

    public Car(
            String carMake, int carManufactureYear,
            int mileage, CustomLinkedList<SparePart> spareParts
    ) {
        this.carMake = carMake;
        this.carManufactureYear = carManufactureYear;
        this.mileage = mileage;
        this.spareParts = spareParts;
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
                ANSI_GREEN + "Внешний вид машины: " + ANSI_YELLOW
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
                ANSI_GREEN + "Покраска кузова: " + ANSI_YELLOW
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
                ANSI_GREEN + "Состояние кузова: " + ANSI_YELLOW
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
                ANSI_GREEN + "Состояние электроники: " + ANSI_YELLOW
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
                ANSI_GREEN + "Модернизация: " + ANSI_YELLOW
                        + getCarModernized() + ANSI_RESET
        );

        return result;
    }

    // Method shows full info about car uses lambda invocations
    public final void showFullCarInfo(List<Car> cars) {
//        List<Car> carList = new ArrayList<>();
        cars.stream()
                .forEach(car -> {
                    showShortCarInfo.accept(car);
                    showCarServices.accept(car);
                });
    }

    // Method shows spare parts in shop uses lambda invocations
    public final void showSpareParts(List<Car> cars) {
        cars.stream()
                .forEach(car -> {
                    showShortCarInfo.accept(car);
                    calculateSparePartsCost.apply(car);
                });
    }

    // Lambda expression shows short info about car
    Consumer<Car> showShortCarInfo = (car) -> {
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
    };

    // Lambda expression shows car applied cservices for a car
    Consumer<Car> showCarServices = (car) -> {
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
        System.out.println();
    };

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

    public CustomLinkedList<SparePart> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(CustomLinkedList<SparePart> spareParts) {
        this.spareParts = spareParts;
    }
}
