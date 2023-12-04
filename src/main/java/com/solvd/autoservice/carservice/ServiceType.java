package com.solvd.autoservice.carservice;

import com.solvd.autoservice.persons.Mechanic;

// ServiceRecord: Represents provided service, date and cost
public final class ServiceType extends CarService {
    private String serviceType;
    private String serviceDate;
    private double serviceCost;
    private Mechanic mechanic;

    public ServiceType(
            String serviceType, String serviceDate,
            double serviceCost, Mechanic mechanic
    ) {
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.serviceCost = serviceCost;
        this.mechanic = mechanic;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }
}
