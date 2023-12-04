package com.solvd.autoservice.car;

import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.autoservice.helpers.ConsoleColors.*;

// SparePart: Represents type, make, cost, delivery days
// and availability spares in stock
public class SparePart extends Car {
    private String sparePartType;
    private String sparePartMake;
    private String isInStock;
    private double sparePartCost;
    private int deliveryDays;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public SparePart() {
    }

    public SparePart(
            String sparePartType, String sparePartMake,
            String isInStock, double sparePartCost
    ) {
        this.sparePartType = sparePartType;
        this.sparePartMake = sparePartMake;
        this.isInStock = isInStock;
        this.sparePartCost = sparePartCost;
        if (isInStock.equals("нет")) {
            this.deliveryDays = generateDeliveryDays();
        }
    }

    // Method generates random delivery days
    public static int generateDeliveryDays() {
        return (int) (Math.random() * 7) + 1;
    }

    // Method calculates spare part costs depends on car' manufacture year
    // and term of sparparts' delivery days
    public static Car calculateSparePartsCost(Car car) {
        double engineOilSpareRetailCost = ObjectsCreator.ENGINE_OIL_RETAIL_COST;
        double tiresSpareRetailCost = ObjectsCreator.TIRE_SET_RETAIL_COST;
        double brakesSpareRetailCost = ObjectsCreator.BRAKE_SET_RETAIL_COST;

        double newValue;
        double finalValue;

        for (SparePart sparePart : car.getSpareParts()) {

            if (car.getCarManufactureYear() > 2015
                    && sparePart.getSparePartType().equals("Моторное масло")) {

                newValue = engineOilSpareRetailCost * 1.5;
                finalValue = calculateCostByDeliveryDays(sparePart, newValue);
                printSparePartInfo(sparePart, finalValue);

            } else if (car.getCarManufactureYear() <= 2015
                    && sparePart.getSparePartType().equals("Моторное масло")) {
                printSparePartInfo(sparePart, engineOilSpareRetailCost);
            }

            if (car.getCarManufactureYear() > 2015
                    && sparePart.getSparePartType().equals("Комплект шин")) {

                newValue = tiresSpareRetailCost * 1.5;
                finalValue = calculateCostByDeliveryDays(sparePart, newValue);
                printSparePartInfo(sparePart, finalValue);

            } else if (car.getCarManufactureYear() <= 2015
                    && sparePart.getSparePartType().equals("Комплект шин")) {
                printSparePartInfo(sparePart, tiresSpareRetailCost);
            }

            if (car.getCarManufactureYear() > 2015
                    && sparePart.getSparePartType().equals("Комплект тормозов")) {

                newValue = brakesSpareRetailCost * 1.5;
                finalValue = calculateCostByDeliveryDays(sparePart, newValue);
                printSparePartInfo(sparePart, finalValue);

            } else if (car.getCarManufactureYear() <= 2015
                    && sparePart.getSparePartType().equals("Комплект тормозов")) {
                printSparePartInfo(sparePart, brakesSpareRetailCost);
            }
        }
        LOGGER.warn("\n");

        return car;
    }

    // Method calculates spare part cost depends on delivery days
    private static double calculateCostByDeliveryDays(SparePart sparePart, double newValue) {
        int deliveryDays = sparePart.getDeliveryDays();

        if (deliveryDays > 0) {
            for (int i = 0; i < deliveryDays; ++i) {
                newValue += 10;
            }

        }

        return newValue;
    }

    // Method prints info about spare parts
    public static void printSparePartInfo(SparePart sparePart, double value) {
        if (sparePart.getDeliveryDays() != 0) {
            LOGGER.info(
                    ANSI_GREEN + "Информация о запчастях: \nТип запчасти: " + ANSI_YELLOW + sparePart.getSparePartType() + "," + ANSI_RESET +
                            ANSI_GREEN + " Марка запчасти: " + ANSI_YELLOW + sparePart.getSparePartMake() + "," + ANSI_RESET +
                            ANSI_GREEN + " Наличие в магазине: " + ANSI_YELLOW + sparePart.isInStock() + ANSI_RESET +
                            ANSI_GREEN + " Стоимость: " + ANSI_YELLOW + value + ANSI_RESET +
                            ANSI_GREEN + " Срок поставки в днях: " + ANSI_YELLOW + sparePart.getDeliveryDays() + ANSI_RESET
            );
        } else {
            LOGGER.info(
                    ANSI_GREEN + "Информация о запчастях: \nТип запчасти: " + ANSI_YELLOW + sparePart.getSparePartType() + "," + ANSI_RESET +
                            ANSI_GREEN + " Марка запчасти: " + ANSI_YELLOW + sparePart.getSparePartMake() + "," + ANSI_RESET +
                            ANSI_GREEN + " Наличие в магазине: " + ANSI_YELLOW + sparePart.isInStock() + ANSI_RESET +
                            ANSI_GREEN + " Стоимость: " + ANSI_YELLOW + value + ANSI_RESET
            );
        }
    }


    public String getSparePartType() {
        return sparePartType;
    }

    public void setSparePartType(String sparePart) {
        this.sparePartType = sparePart;
    }

    public String getSparePartMake() {
        return sparePartMake;
    }

    public void setSparePartMake(String sparePartMake) {
        this.sparePartMake = sparePartMake;
    }

    public String isInStock() {
        return isInStock;
    }

    public void setInStock(String inStock) {
        isInStock = inStock;
    }

    public double getSparePartCost() {
        return sparePartCost;
    }

    public void setSparePartCost(double sparePartCost) {
        this.sparePartCost = sparePartCost;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

}
