package com.solvd.autoservice.carservice;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return Objects.equals(date, appointment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date='" + date + '\'' +
                '}';
    }
}
