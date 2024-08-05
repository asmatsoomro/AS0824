package com.as0824.rentaltool.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tools")
public class Tool {

    @Id
    private String id;
    private String name;
    private String brand;

    @Embedded
    private Charges charges;

    // Constructors
    public Tool() {
    }

    public Tool(String id, String name, String brand, Charges charges) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.charges = charges;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Charges getCharges() {
        return charges;
    }

    public void setCharges(Charges charges) {
        this.charges = charges;
    }
}
