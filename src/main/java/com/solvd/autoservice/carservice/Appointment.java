package com.solvd.autoservice.carservice;

// Appointment: Represents a date of appointment.
public final class Appointment extends CarService {
    private String date;

    public Appointment(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
