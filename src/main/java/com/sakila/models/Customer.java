package com.sakila.models;

/**
 * Modelo que representa un cliente de la base de datos Sakila.
 * @author Alexander
 */
public class Customer {

    private int customerId;
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;

    public Customer() {
    }

    public Customer(int customerId, int storeId, String firstName, String lastName, String email, boolean active) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return customerId + " | " + firstName + " " + lastName + " | " + email + " | Store: " + storeId + " | Activo: " + active;
    }
}