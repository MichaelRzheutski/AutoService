package com.solvd.autoservice.carservice;

import com.solvd.autoservice.persons.Mechanic;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceType that = (ServiceType) o;
        return Objects.equals(serviceType, that.serviceType)
                && Objects.equals(serviceDate, that.serviceDate)
                && Objects.equals(serviceCost, that.serviceCost)
                && Objects.equals(mechanic, that.mechanic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceType, serviceDate,
                serviceCost, mechanic);
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "serviceType='" + serviceType + '\'' +
                ", serviceDate='" + serviceDate + '\'' +
                ", serviceCost=" + serviceCost +
                ", mechanic=" + mechanic +
                '}';
    }
}
