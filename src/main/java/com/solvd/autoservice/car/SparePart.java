package com.solvd.autoservice.car;

import com.solvd.autoservice.customlinkedlist.CustomLinkedList;
import com.solvd.autoservice.helpers.ObjectsCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

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

    private static final ObjectsCreator OBJECTS_CREATOR = new ObjectsCreator();

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

    // Method calculates spare part costs depends on car manufacture year
    public static CustomLinkedList<SparePart> calcSparePartCost(
            List<Car> cars, CustomLinkedList<SparePart> spareParts) {
        double result;
        int sparePartsDeliveryDays;

        // Calculate spare part costs
        for (Car car : cars) {

            for (SparePart sparePart : spareParts) {

                if (car.getCarManufactureYear() > 2015) {
                    result = sparePart.getSparePartCost();
                    sparePart.setSparePartCost(result *= 1.5);

                    if (sparePart.getDeliveryDays() > 0) {
                        sparePartsDeliveryDays = sparePart.getDeliveryDays() * 2;
                        sparePart.setSparePartCost(result += sparePartsDeliveryDays);
                    }

                }
            }
            break;
        }

        return spareParts;
    }

    public List<Car> showSpareParts(List<Car> cars, CustomLinkedList<SparePart> spareParts) {

        for (Car car : cars) {
            LOGGER.info(
                    ANSI_GREEN + "Марка автомобиля: " + ANSI_YELLOW + car.getCarMake() + "," + ANSI_RESET +
                            ANSI_GREEN + "Год выпуска: " + ANSI_YELLOW + car.getCarManufactureYear() + "," + ANSI_RESET +
                            ANSI_GREEN + "Пробег км: " + ANSI_YELLOW + car.getMileage() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Информация о запчастях: " + ANSI_RESET
            );
            for (SparePart sparePart : spareParts) {
                if (sparePart.getDeliveryDays() != 0) {
                    System.out.print(
                            ANSI_RESET + "Тип запчасти: " + ANSI_YELLOW + sparePart.sparePartType + "," + ANSI_RESET +
                                    ANSI_RESET + " Марка запчасти: " + ANSI_YELLOW + sparePart.sparePartMake + "," + ANSI_RESET +
                                    ANSI_RESET + " Наличие в магазине: " + ANSI_YELLOW + sparePart.isInStock + ANSI_RESET +
                                    ANSI_RESET + " Стоимость: " + ANSI_YELLOW + sparePart.sparePartCost + ANSI_RESET +
                                    ANSI_RESET + " Срок поставки в днях: " + ANSI_YELLOW + sparePart.deliveryDays + "\n" + ANSI_RESET
                    );
                } else {
                    System.out.print(
                            ANSI_RESET + "Тип запчасти: " + ANSI_YELLOW + sparePart.sparePartType + "," + ANSI_RESET +
                                    ANSI_RESET + " Марка запчасти: " + ANSI_YELLOW + sparePart.sparePartMake + "," + ANSI_RESET +
                                    ANSI_RESET + " Наличие в магазине: " + ANSI_YELLOW + sparePart.isInStock + ANSI_RESET +
                                    ANSI_RESET + " Стоимость: " + ANSI_YELLOW + sparePart.sparePartCost + "\n" + ANSI_RESET
                    );
                }

            }
            System.out.println();
        }

        return cars;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SparePart sparePart = (SparePart) o;
        return deliveryDays == sparePart.deliveryDays
                && Objects.equals(sparePartType, sparePart.sparePartType)
                && Objects.equals(sparePartMake, sparePart.sparePartMake)
                && Objects.equals(isInStock, sparePart.isInStock)
                && Objects.equals(sparePartCost, sparePart.sparePartCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sparePartType, sparePartMake,
                isInStock, sparePartCost, deliveryDays);
    }

    @Override
    public String toString() {
        return ANSI_RESET + "\nТип запчасти: " + ANSI_YELLOW + sparePartType + "," + ANSI_RESET +
                ANSI_RESET + " Марка запчасти: " + ANSI_YELLOW + sparePartMake + "," + ANSI_RESET +
                ANSI_RESET + " Наличие в магазине: " + ANSI_YELLOW + isInStock + ANSI_RESET +
                ANSI_RESET + " Стоимость: " + ANSI_YELLOW + sparePartCost + ANSI_RESET;
//                ANSI_RESET + " Срок поставки: " + ANSI_YELLOW + deliveryDays + ANSI_RESET;
    }
}
