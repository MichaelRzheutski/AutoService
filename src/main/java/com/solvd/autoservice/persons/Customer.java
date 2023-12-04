package com.solvd.autoservice.persons;

import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.carservice.Appointment;
import com.solvd.autoservice.carservice.Invoice;
import com.solvd.autoservice.carservice.ServiceType;
import com.solvd.autoservice.interfaces.IChildRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

import java.util.Formatter;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

import static com.solvd.autoservice.enums.ConsoleColors.*;

// Customer: Represents a whole information about customer
public final class Customer extends Person implements IChildRoom {
    private Appointment appointment;
    private String contactInformation;
    private String carName;
    private ServiceType serviceType;
    private Mechanic mechanic;
    private SparePart sparePart;
    private Invoice invoice;
    private String childRoom;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public Customer() {
        super();
    }

    public Customer(
            Appointment appointment, String name, String surname,
            String contactInformation, String carName, ServiceType serviceType,
            SparePart sparePart, Invoice invoice, Mechanic mechanic) {
        super(name, surname);
        this.appointment = appointment;
        this.name = name;
        this.contactInformation = contactInformation;
        this.carName = carName;
        this.serviceType = serviceType;
        this.sparePart = sparePart;
        this.invoice = invoice;
        this.mechanic = mechanic;
        this.role = "Клиент";
    }

    @Override
    public String showPersonRole() {
        return role;
    }

    @Override
    public String isChildRoomUsed(boolean isChildRoomUsed) {
        String result;

        if (isChildRoomUsed) {
            setChildRoomUsed("Воспользовался детской комнатой");
        } else {
            setChildRoomUsed("Детской комнатой не пользовался");
        }
        result = getChildRoomUsed();
        LOGGER.info(
                ANSI_GREEN + "Услуги детской комнаты: " + ANSI_YELLOW
                        + getChildRoomUsed() + ANSI_RESET
        );

        return result;
    }

    // Lambda expression shows if the child room used
    public Consumer<Customer> showChildRoomUsage = (customer) -> {
        if (customer.getName().equals("Алексей")
                && customer.getSurname().equals("Привольнов")) {
            isChildRoomUsed(false);
        }

        if (customer.getName().equals("Сергей")
                && customer.getSurname().equals("Власов")) {
            isChildRoomUsed(true);
        }

        if (customer.getName().equals("Владимир")
                && customer.getSurname().equals("Долгин")) {
            isChildRoomUsed(true);
        }

        System.out.println();
    };

    // Method shows whole info about customers uses lambda invocation
    public void showCustomers(Set<Customer> customers) {

        for (Customer customer : customers) {
            LOGGER.info(
                    ANSI_GREEN + "Имя и фамилия клиента: " + ANSI_YELLOW
                            + customer.getName() + " " + customer.getSurname() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Роль: " + ANSI_YELLOW
                            + customer.getRole() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Телефон: " + ANSI_YELLOW
                            + customer.getContactInformation() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Дата поступления авто в обслуживание: " + ANSI_YELLOW
                            + customer.getAppointment().getDate() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Марка авто: " + ANSI_YELLOW
                            + customer.getCarName() + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Услуга: " + ANSI_YELLOW
                            + customer.getServiceRecord().getServiceType()
                            + ANSI_RESET
            );

            LOGGER.info(
                    ANSI_GREEN + "Дата оказания услуги: " + ANSI_YELLOW
                            + customer.getServiceRecord().getServiceDate()
                            + ANSI_RESET
            );

            Formatter serviceCostFormat = new Formatter(Locale.US).format(
                    ANSI_GREEN + "Стоимость услуги: " + ANSI_YELLOW + "%.2f$"
                            + ANSI_RESET, customer.getServiceRecord().getServiceCost());
            LOGGER.info(String.format(serviceCostFormat.toString()));

            if (customer.getSparePart().isInStock().equals("да")) {
                LOGGER.info(
                        ANSI_GREEN + "Запчасть: " + ANSI_YELLOW
                                + customer.getSparePart().getSparePartType() + ANSI_GREEN
                                + " в наличии на складе" + ANSI_RESET

                );
            } else {
                LOGGER.info(
                        ANSI_GREEN + "Запчасть: " + ANSI_YELLOW
                                + customer.getSparePart().getSparePartType() + ANSI_GREEN
                                + " отсутствует на складе" + ANSI_RESET
                );
            }

            Formatter invoiceFormat = new Formatter(Locale.US).format(
                    ANSI_GREEN + "Счёт на сумму: " + ANSI_YELLOW + "%.2f$" + ANSI_GREEN + " %s",
                    customer.getInvoice().getTotalCost(),
                    customer.getInvoice().getPaymentStatus());
            LOGGER.info(String.format(invoiceFormat.toString() + ANSI_RESET));

            LOGGER.info(
                    ANSI_GREEN + "Механик выполнивший работу: " + ANSI_YELLOW
                            + customer.getMechanic().getName() + " "
                            + customer.getMechanic().getSurname() + ANSI_GREEN
                            + " | Квалификация: " + ANSI_YELLOW
                            + customer.getMechanic().getExpertise() + ANSI_RESET
            );

            showChildRoomUsage.accept(customer);
        }
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public ServiceType getServiceRecord() {
        return serviceType;
    }

    public void setServiceRecord(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public SparePart getSparePart() {
        return sparePart;
    }

    public void setSparePart(SparePart sparePart) {
        this.sparePart = sparePart;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public String getChildRoomUsed() {
        return childRoom;
    }

    public void setChildRoomUsed(String childRoom) {
        this.childRoom = childRoom;
    }

}
