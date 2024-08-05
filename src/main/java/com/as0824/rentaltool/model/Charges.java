package com.as0824.rentaltool.model;

public class Charges {
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    private boolean isDailyChargeApplicable;
    private boolean isWeekdayChargeApplicable;
    private boolean isWeekendChargeApplicable;


    private boolean isHolidayChargeApplicable;

    // Constructor, getters, and setters
    public Charges(double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge,
                   boolean isDailyChargeApplicable, boolean isWeekdayChargeApplicable,
                   boolean isWeekendChargeApplicable, boolean isHolidayChargeApplicable) {
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
        this.isDailyChargeApplicable = isDailyChargeApplicable;
        this.isWeekdayChargeApplicable = isWeekdayChargeApplicable;
        this.isWeekendChargeApplicable = isWeekendChargeApplicable;
        this.isHolidayChargeApplicable = isHolidayChargeApplicable;
    }
    // No-arg constructor for JPA and frameworks
    public Charges() {}

    // Getters
    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    // Setters
    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    public boolean isHolidayChargeApplicable() {
        return isHolidayChargeApplicable;
    }

    public void setHolidayChargeApplicable(boolean holidayChargeApplicable) {
        isHolidayChargeApplicable = holidayChargeApplicable;
    }

    public boolean isWeekendChargeApplicable() {
        return isWeekendChargeApplicable;
    }

    public void setWeekendChargeApplicable(boolean weekendChargeApplicable) {
        isWeekendChargeApplicable = weekendChargeApplicable;
    }

    public boolean isWeekdayChargeApplicable() {
        return isWeekdayChargeApplicable;
    }

    public void setWeekdayChargeApplicable(boolean weekdayChargeApplicable) {
        isWeekdayChargeApplicable = weekdayChargeApplicable;
    }

    public boolean isDailyChargeApplicable() {
        return isDailyChargeApplicable;
    }

    public void setDailyChargeApplicable(boolean dailyChargeApplicable) {
        isDailyChargeApplicable = dailyChargeApplicable;
    }

}
