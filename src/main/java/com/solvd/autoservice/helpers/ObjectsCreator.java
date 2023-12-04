package com.solvd.autoservice.helpers;

import com.solvd.autoservice.car.Car;
import com.solvd.autoservice.car.CarDiagnostics;
import com.solvd.autoservice.car.SparePart;
import com.solvd.autoservice.carservice.Appointment;
import com.solvd.autoservice.carservice.Invoice;
import com.solvd.autoservice.carservice.ServiceType;
import com.solvd.autoservice.customlinkedlist.CustomLinkedList;
import com.solvd.autoservice.persons.Customer;
import com.solvd.autoservice.persons.Mechanic;

import java.util.*;

public final class ObjectsCreator {
    public static final String IS_IN_STOCK = "да";
    public static final String IS_NOT_IN_STOCK = "нет";

    // Spare part types
    public static final String ENGINE_OIL = "Моторное масло";
    public static final String TIRE_SET = "Комплект шин";
    public static final String BRAKE_SET = "Комплект тормозов";

    // Spare part makes
    public static final String ENGINE_OIL_MAKE = "Mobil 1";
    public static final String TIRE_SET_MAKE = "Белшина";
    public static final String BRAKE_SET_MAKE = "Шустрые тормоза";

    // Spare part retail cost
    public static final double ENGINE_OIL_RETAIL_COST = 50.00;
    public static final double TIRE_SET_RETAIL_COST = 200.00;
    public static final double BRAKE_SET_RETAIL_COST = 100.00;

    // Cars
    public static final String BMW_X6 = "BMW X6";
    public static final String TOYOTA_LAND_CRUISER = "Toyota Land Cruiser";
    public static final String MERCEDES_BENZ = "Mercedes Benz";

    // Car mileage
    public static final int BMW_X6_MILEAGE = 1000;
    public static final int TOYOTA_LAND_CRUISER_MILEAGE = 20000;
    public static final int MERCEDES_BENZ_MILEAGE = 500000;

    // Car manufacture year
    public static final int BMW_X6_MANUFACTURE_YEAR = 2023;
    public static final int TOYOTA_LAND_CRUISER_MANUFACTURE_YEAR = 2020;
    public static final int MERCEDES_BENZ_MANUFACTURE_YEAR = 2015;

    // Car type of service
    public static final String SERVICE_TYPE_OIL_CHANGE = "Замена моторного масла";
    public static final String SERVICE_TYRE_ROTATION = "Замена шин";
    public static final String SERVICE_BRAKES_CHANGE = "Замена тормозов";

    // Car date and cost of service
    public static final String OIL_CHANGE_SERVICE_DATE = "5 Сентября 2023";
    public static final double OIL_CHANGE_SERVICE_COST = 50.00;
    public static final String TYRE_ROTATION_SERVICE_DATE = "20 Сентября 2023";
    public static final double TYRE_ROTATION_SERVICE_COST = 100.00;
    public static final String BRACKES_CHANGE_SERVICE_DATE = "5 Октября 2023";
    public static final double BRACKES_CHANGE_SERVICE_COST = 25.00;

    // Mechanics of autoservice
    public static final String MECHANIC_VLADLEN_PODDUBITSKY_NAME = "Владлен";
    public static final String MECHANIC_VLADLEN_PODDUBITSKY_SURNAME = "Поддубицкий";
    public static final String MECHANIC_VLADLEN_PODDUBITSKY_EXPERTISE = "Мастер";
    public static final String MECHANIC_VLADLEN_PODDUBITSKY_AVAILABILITY = "Занят";

    public static final String MECHANIC_VYACHESLAV_MARSHAL_NAME = "Вячеслав";
    public static final String MECHANIC_VYACHESLAV_MARSHAL_SURNAME = "Маршал";
    public static final String MECHANIC_VYACHESLAV_MARSHAL_EXPERTISE = "Специалист";
    public static final String MECHANIC_VYACHESLAV_MARSHAL_AVAILABILITY = "Свободен";

    public static final String MECHANIC_ENGENY_BELY_NAME = "Евгений";
    public static final String MECHANIC_ENGENY_BELY_SURNAME = "Белый";
    public static final String MECHANIC_ENGENY_BELY_EXPERTISE = "Стажёр";
    public static final String MECHANIC_ENGENY_BELY_AVAILABILITY = "Свободен";

    // Apointment for diagnostics' dates
    public static final String ALEXEY_PRIVOLNOV_APPOINTMENT_DATE = "10 Августа 2023";
    public static final String SERGEY_VLASOV_APPOINTMENT_DATE = "5 Сентября 2023";
    public static final String VLADIMIR_DOLGIN_APPOINTMENT_DATE = "25 Сентября 2023";

    // Costs and payment statuses of invoices
    public static final double ALEXEY_PRIVOLNOV_INVOICE_COST = 150.00;
    public static final String ALEXEY_PRIVOLNOV_INVOICE_PAYMENT_STATUS = "оплачен";

    public static final double SERGEY_VLASOV_INVOICE_COST = 100.00;
    public static final String SERGEY_VLASOV_INVOICE_PAYMENT_STATUS = "оплачен";

    public static final double VLADIMIR_DOLGIN_INVOICE_COST = 50.00;
    public static final String VLADIMIR_DOLGIN_INVOICE_PAYMENT_STATUS = "оплачен";

    // Car diagnostics result, damage severity, number of days for service
    public static final String BMW_X6_DIAGNOSTICS_RESULT_OIL_CHANGE = "Требуется замена моторного масла";
    public static final String BMW_X6_DAMAGES_SEVERITY = "Повреждения отсутствуют";
    public static final int BMW_X6_DIAGNOSTICS_TIME = 1;

    public static final String TOYOTA_LAND_CRUISER_DIAGNOSTICS_TYRES_CHANGE = "Требуется замена шин";
    public static final String TOYOTA_LAND_CRUISER_DAMAGES_SEVERITY = "Лёгкие повреждения";
    public static final int TOYOTA_LAND_CRUISER_DIAGNOSTICS_TIME = 3;

    public static final String MERCEDES_BENZ_DIAGNOSTICS_BRAKES_CHANGE = "Требуется замена тормозных колодок";
    public static final String MERCEDES_BENZ_DAMAGES_SEVERITY = "Серьёзные повреждения";
    public static final int MERCEDES_BENZ_DIAGNOSTICS_TIME = 5;

    // Customer's name, surname and phone number
    public static final String ALEXEY_PRIVOLNOV_NAME = "Алексей";
    public static final String ALEXEY_PRIVOLNOV_SURNAME = "Привольнов";
    public static final String ALEXEY_PRIVOLNOV_PHONE = "+735253458756";

    public static final String SERGEY_VLASOV_NAME = "Сергей";
    public static final String SERGEY_VLASOV_SURNAME = "Власов";
    public static final String SERGEY_VLASOV_PHONE = "+735293879581";

    public static final String VLADIMIR_DOLGIN_NAME = "Владимир";
    public static final String VLADIMIR_DOLGIN_SURNAME = "Долгин";
    public static final String VLADIMIR_DOLGIN_PHONE = "+735337847623";

    public ObjectsCreator() {
    }

    // Create spare parts
    public SparePart oilSpare = new SparePart(
            ENGINE_OIL,
            ENGINE_OIL_MAKE,
            IS_IN_STOCK,
            ENGINE_OIL_RETAIL_COST
    );
    public SparePart tiresSpare = new SparePart(
            TIRE_SET,
            TIRE_SET_MAKE,
            IS_NOT_IN_STOCK,
            TIRE_SET_RETAIL_COST
    );
    public SparePart brakesSpare = new SparePart(
            BRAKE_SET,
            BRAKE_SET_MAKE,
            IS_IN_STOCK,
            BRAKE_SET_RETAIL_COST
    );

    // Create number of spare parts for autos
    public CustomLinkedList<SparePart> bmwX6SpareParts = new CustomLinkedList<>();

    public CustomLinkedList<SparePart> createBmwX6SpareParts() {
        bmwX6SpareParts.add(oilSpare);
        bmwX6SpareParts.add(tiresSpare);
        bmwX6SpareParts.add(brakesSpare);

        return bmwX6SpareParts;
    }

    public CustomLinkedList<SparePart> toyotaLandCruiserSpareParts = new CustomLinkedList<>();

    public CustomLinkedList<SparePart> createToyotaLandCruiserSpareParts() {
        toyotaLandCruiserSpareParts.add(oilSpare);
        toyotaLandCruiserSpareParts.add(tiresSpare);

        return toyotaLandCruiserSpareParts;
    }

    public CustomLinkedList<SparePart> mercedesBenzSpareParts = new CustomLinkedList<>();

    public CustomLinkedList<SparePart> createMercedesBenzSpareParts() {
        mercedesBenzSpareParts.add(oilSpare);
        return mercedesBenzSpareParts;
    }

    // Create mechanics
    public Mechanic vladlenPoddubitsky = new Mechanic(
            MECHANIC_VLADLEN_PODDUBITSKY_NAME,
            MECHANIC_VLADLEN_PODDUBITSKY_SURNAME,
            MECHANIC_VLADLEN_PODDUBITSKY_EXPERTISE,
            MECHANIC_VLADLEN_PODDUBITSKY_AVAILABILITY
    );
    public Mechanic vyacheslavMarshal = new Mechanic(
            MECHANIC_VYACHESLAV_MARSHAL_NAME,
            MECHANIC_VYACHESLAV_MARSHAL_SURNAME,
            MECHANIC_VYACHESLAV_MARSHAL_EXPERTISE,
            MECHANIC_VYACHESLAV_MARSHAL_AVAILABILITY
    );
    public Mechanic evgenyBely = new Mechanic(
            MECHANIC_ENGENY_BELY_NAME,
            MECHANIC_ENGENY_BELY_SURNAME,
            MECHANIC_ENGENY_BELY_EXPERTISE,
            MECHANIC_ENGENY_BELY_AVAILABILITY
    );
    public Set<Mechanic> mechanicSet = new HashSet<>();

    // Method creates HashMap of mechanics
    public Set<Mechanic> createMechanicSet() {
        mechanicSet.add(vladlenPoddubitsky);
        mechanicSet.add(vyacheslavMarshal);
        mechanicSet.add(evgenyBely);

        return mechanicSet;
    }

    // Create cars
    public Car bmwX6 = new Car(
            BMW_X6,
            BMW_X6_MANUFACTURE_YEAR,
            BMW_X6_MILEAGE,
            createBmwX6SpareParts()
    );

    public Car toyotaLandCruiser = new Car(
            TOYOTA_LAND_CRUISER,
            TOYOTA_LAND_CRUISER_MANUFACTURE_YEAR,
            TOYOTA_LAND_CRUISER_MILEAGE,
            createToyotaLandCruiserSpareParts()
    );
    public Car mercedesBenz = new Car(
            MERCEDES_BENZ,
            MERCEDES_BENZ_MANUFACTURE_YEAR,
            MERCEDES_BENZ_MILEAGE,
            createMercedesBenzSpareParts()
    );
    public List<Car> carList = new ArrayList<>();

    // Method creates ArrayList of cars
    public List<Car> createCarList() {
        carList.add(bmwX6);
        carList.add(toyotaLandCruiser);
        carList.add(mercedesBenz);

        return carList;
    }

    // Create service records for customers
    public ServiceType alexeyPrivolnovService = new ServiceType(
            SERVICE_TYPE_OIL_CHANGE,
            OIL_CHANGE_SERVICE_DATE,
            OIL_CHANGE_SERVICE_COST,
            vladlenPoddubitsky
    );
    public ServiceType sergeyVlasovService = new ServiceType(
            SERVICE_TYRE_ROTATION,
            TYRE_ROTATION_SERVICE_DATE,
            TYRE_ROTATION_SERVICE_COST,
            vyacheslavMarshal
    );
    public ServiceType vladimirDolginService = new ServiceType(
            SERVICE_BRAKES_CHANGE,
            BRACKES_CHANGE_SERVICE_DATE,
            BRACKES_CHANGE_SERVICE_COST,
            evgenyBely
    );

    // Create appointments
    public Appointment alexeyPrivolnovAppointment = new Appointment(
            ALEXEY_PRIVOLNOV_APPOINTMENT_DATE
    );
    public Appointment sergeyVlasovAppointment = new Appointment(
            SERGEY_VLASOV_APPOINTMENT_DATE
    );
    public Appointment vladimirDolginAppointment = new Appointment(
            VLADIMIR_DOLGIN_APPOINTMENT_DATE
    );

    // Create invoices
    public Invoice alexeyPrivolnovInvoice = new Invoice(
            ALEXEY_PRIVOLNOV_INVOICE_COST,
            ALEXEY_PRIVOLNOV_INVOICE_PAYMENT_STATUS
    );
    public Invoice sergeyVlasovInvoice = new Invoice(
            SERGEY_VLASOV_INVOICE_COST,
            SERGEY_VLASOV_INVOICE_PAYMENT_STATUS
    );
    public Invoice vladimirDolginInvoice = new Invoice(
            VLADIMIR_DOLGIN_INVOICE_COST,
            VLADIMIR_DOLGIN_INVOICE_PAYMENT_STATUS
    );
    public List<Invoice> invoiceList = new LinkedList<>();

    // Method creates LinkedList of Invoices
    public List<Invoice> createInvoiceList() {
        invoiceList.add(alexeyPrivolnovInvoice);
        invoiceList.add(sergeyVlasovInvoice);
        invoiceList.add(vladimirDolginInvoice);

        return invoiceList;
    }

    // Car diagnostics
    public CarDiagnostics bmwX6Diagnostics = new CarDiagnostics(
            bmwX6,
            BMW_X6_DIAGNOSTICS_RESULT_OIL_CHANGE,
            BMW_X6_DAMAGES_SEVERITY,
            BMW_X6_DIAGNOSTICS_TIME
    );
    public CarDiagnostics toyotaLandCruiserDiagnostics = new CarDiagnostics(
            toyotaLandCruiser,
            TOYOTA_LAND_CRUISER_DIAGNOSTICS_TYRES_CHANGE,
            TOYOTA_LAND_CRUISER_DAMAGES_SEVERITY,
            TOYOTA_LAND_CRUISER_DIAGNOSTICS_TIME
    );
    public CarDiagnostics mercedesBenzDiagnostics = new CarDiagnostics(
            mercedesBenz,
            MERCEDES_BENZ_DIAGNOSTICS_BRAKES_CHANGE,
            MERCEDES_BENZ_DAMAGES_SEVERITY,
            MERCEDES_BENZ_DIAGNOSTICS_TIME
    );
    public Set<CarDiagnostics> carDiagnosticsSet = new HashSet<>();

    // Method creates HashSet of car diagnostics
    public Set<CarDiagnostics> createCarDiagnosticsSet() {
        carList.add(bmwX6);
        carList.add(toyotaLandCruiser);
        carList.add(mercedesBenz);

        return carDiagnosticsSet;
    }

    // Create customers
    public Customer alexeyPrivolnov = new Customer(
            alexeyPrivolnovAppointment,
            ALEXEY_PRIVOLNOV_NAME,
            ALEXEY_PRIVOLNOV_SURNAME,
            ALEXEY_PRIVOLNOV_PHONE,
            bmwX6.getCarMake(),
            alexeyPrivolnovService,
            oilSpare,
            alexeyPrivolnovInvoice,
            vladlenPoddubitsky
    );

    public Customer sergeyVlasov = new Customer(
            sergeyVlasovAppointment,
            SERGEY_VLASOV_NAME,
            SERGEY_VLASOV_SURNAME,
            SERGEY_VLASOV_PHONE,
            toyotaLandCruiser.getCarMake(),
            sergeyVlasovService,
            tiresSpare,
            sergeyVlasovInvoice,
            vyacheslavMarshal
    );

    public Customer vladimirDolgin = new Customer(
            vladimirDolginAppointment,
            VLADIMIR_DOLGIN_NAME,
            VLADIMIR_DOLGIN_SURNAME,
            VLADIMIR_DOLGIN_PHONE,
            mercedesBenz.getCarMake(),
            vladimirDolginService,
            brakesSpare,
            vladimirDolginInvoice,
            evgenyBely
    );
    public Set<Customer> customersLinkedHashSet = new LinkedHashSet<>();

    // Method creates LinkedHashSet of customers
    public Set<Customer> createCustomersSet() {
        customersLinkedHashSet.add(alexeyPrivolnov);
        customersLinkedHashSet.add(sergeyVlasov);
        customersLinkedHashSet.add(vladimirDolgin);

        return customersLinkedHashSet;
    }

}
