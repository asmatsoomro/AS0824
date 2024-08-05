package com.as0824.rentaltool.model;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private Charges charges;

    // Constructor
    public Tool(String code, String type, String brand, Charges charges) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.charges = charges;
    }

    // No-arg constructor for frameworks
    public Tool() {}

    // Getters
    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public Charges getCharges() {
        return charges;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCharges(Charges charges) {
        this.charges = charges;
    }
}

