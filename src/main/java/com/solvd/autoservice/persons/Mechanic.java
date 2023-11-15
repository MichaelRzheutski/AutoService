package com.solvd.autoservice.persons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Objects;

import static com.solvd.autoservice.helpers.ConsoleColors.*;

// Mechanic: Represents a mechanic with his expertise and availability
public final class Mechanic extends Person {
    private String expertise;
    private String availability;

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

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

    public static void showMechanics(Map<Integer, Mechanic> mechanics) {
        for (Map.Entry<Integer, Mechanic> mechanic : mechanics.entrySet()) {
            LOGGER.info(
                    ANSI_GREEN + "Имя и фамилия: " + ANSI_YELLOW
                            + mechanic.getValue().getName() + " " + mechanic.getValue().getSurname() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Экспертиза: " + ANSI_YELLOW
                            + mechanic.getValue().getExpertise() + ANSI_RESET
            );
            LOGGER.info(
                    ANSI_GREEN + "Доступность: " + ANSI_YELLOW
                            + mechanic.getValue().getAvailability() + "\n" + ANSI_RESET
            );
        }
    }

//    public static void showMechanics(Map<Integer, Mechanic> mechanics) {
//        for (Mechanic mechanic : mechanics) {
//            LOGGER.info(
//                    ANSI_GREEN + "Имя и фамилия: " + ANSI_YELLOW
//                            + mechanic.getName() + " " + mechanic.getSurname() + ANSI_RESET
//            );
//            LOGGER.info(
//                    ANSI_GREEN + "Экспертиза: " + ANSI_YELLOW
//                            + mechanic.getExpertise() + ANSI_RESET
//            );
//            LOGGER.info(
//                    ANSI_GREEN + "Доступность: " + ANSI_YELLOW
//                            + mechanic.getAvailability() + "\n" + ANSI_RESET
//            );
//        }
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(expertise, mechanic.expertise)
                && Objects.equals(availability, mechanic.availability
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(expertise, availability);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "expertise='" + expertise + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
