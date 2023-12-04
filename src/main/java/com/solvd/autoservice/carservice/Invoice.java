package com.solvd.autoservice.carservice;

// Invoice: Represents an invoice for a customer with total cost and payment status
public final class Invoice extends CarService {
    private double totalCost;
    private String paymentStatus;

    public Invoice(double totalCost, String paymentStatus) {
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
