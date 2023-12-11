package com.solvd.autoservice.persons;

import java.util.Set;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

// Mechanic: Represents a mechanic with his expertise and availability
public final class Mechanic extends Person {
    private String expertise;
    private String availability;

    public Mechanic(
            String name, String surname,
            String expertise, String availability
    ) {
        super(name, surname);
        this.name = name;
        this.surname = surname;
        this.expertise = expertise;
        this.availability = availability;
        this.role = "Механик";
    }

    public static void showMechanics(Set<Mechanic> mechanics) {
        for (Mechanic mechanic : mechanics) {
            MY_LOGGER.info(
                    ANSI_GREEN + "Имя и фамилия: " + ANSI_YELLOW
                            + mechanic.getName() + " " + mechanic.getSurname() + ANSI_RESET
            );
            MY_LOGGER.info(
                    ANSI_GREEN + "Экспертиза: " + ANSI_YELLOW
                            + mechanic.getExpertise() + ANSI_RESET
            );
            MY_LOGGER.info(
                    ANSI_GREEN + "Доступность: " + ANSI_YELLOW
                            + mechanic.getAvailability() + "\n" + ANSI_RESET
            );
        }
    }

    @Override
    public String showPersonRole() {
        return role;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
