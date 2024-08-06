package com.as0824.rentaltool.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RentalRequest {

    @NotNull(message = "Tool code is required field")
    private String toolCode;

    private int rentalDays;

    private int discountPercent;

    @NotNull(message = "checkoutDate is a required field")
    private LocalDate checkoutDate;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}