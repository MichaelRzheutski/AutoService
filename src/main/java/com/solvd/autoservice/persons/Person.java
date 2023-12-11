package com.solvd.autoservice.persons;

// Person: Represents an abstract person
public abstract class Person {
    protected String name;
    protected String surname;
    protected String role;

    public Person() {
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public abstract String showPersonRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
