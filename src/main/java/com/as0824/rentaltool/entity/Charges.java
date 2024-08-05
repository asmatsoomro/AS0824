package com.as0824.rentaltool.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Charges {

    private double dailyCharge;
    private boolean isWeekdayChargeApplicable;
    private boolean isWeekendChargeApplicable;
    private boolean isHolidayChargeApplicable;

    protected Charges() {
    }

    public Charges(double dailyCharge,boolean isWeekdayChargeApplicable,
                   boolean isWeekendChargeApplicable, boolean isHolidayChargeApplicable) {
        this.dailyCharge = dailyCharge;
        this.isWeekdayChargeApplicable = isWeekdayChargeApplicable;
        this.isWeekendChargeApplicable = isWeekendChargeApplicable;
        this.isHolidayChargeApplicable = isHolidayChargeApplicable;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayChargeApplicable() {
        return isWeekdayChargeApplicable;
    }

    public void setWeekdayChargeApplicable(boolean isWeekdayChargeApplicable) {
        this.isWeekdayChargeApplicable = isWeekdayChargeApplicable;
    }

    public boolean isWeekendChargeApplicable() {
        return isWeekendChargeApplicable;
    }

    public void setWeekendChargeApplicable(boolean isWeekendChargeApplicable) {
        this.isWeekendChargeApplicable = isWeekendChargeApplicable;
    }

    public boolean isHolidayChargeApplicable() {
        return isHolidayChargeApplicable;
    }

    public void setHolidayChargeApplicable(boolean isHolidayChargeApplicable) {
        this.isHolidayChargeApplicable = isHolidayChargeApplicable;
    }
}
