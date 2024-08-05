package com.as0824.rentaltool.service;

import com.as0824.rentaltool.entity.RentalAgreement;
import com.as0824.rentaltool.entity.Tool;
import com.as0824.rentaltool.model.RentalRequest;
import com.as0824.rentaltool.repository.RentalAgreementRepository;
import com.as0824.rentaltool.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class RentalService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    @Autowired
    private HolidayService holidayService;

    public RentalAgreement checkout(RentalRequest request) {
        Tool tool = toolRepository.findById(request.getToolCode())
                .orElseThrow(() -> new IllegalArgumentException("Tool not found with code: " + request.getToolCode()));

        LocalDate checkoutDate = request.getCheckoutDate();
        int rentalDays = request.getRentalDays();
        LocalDate dueDate = calculateDueDate(checkoutDate, rentalDays);
        int chargeDays = calculateChargeableDays(checkoutDate, rentalDays, tool);

        double dailyCharge = tool.getCharges().getDailyCharge();
        double preDiscountCharge = calculatePreDiscountCharge(chargeDays, dailyCharge);
        double discountAmount = calculateDiscountAmount(preDiscountCharge, request.getDiscountPercent());
        double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

        return createRentalAgreement(tool, request, dueDate, chargeDays, preDiscountCharge, discountAmount, finalCharge);
    }

    private LocalDate calculateDueDate(LocalDate startDate, int days) {
        return startDate.plusDays(days);
    }

    private int calculateChargeableDays(LocalDate startDate, int days, Tool tool) {
        int chargeDays = 0;
        LocalDate date = startDate;
        for (int i = 0; i < days; i++) {
            if (isChargeable(date, tool)) {
                chargeDays++;
            }
            date = date.plusDays(1);
        }
        return chargeDays;
    }

    private boolean isChargeable(LocalDate date, Tool tool) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        boolean isHoliday = holidayService.isHoliday(date);

        // First check if it's a weekend and weekends are chargeable
        if (isWeekend) {
            return tool.getCharges().isWeekendChargeApplicable();
        }

        // If it's not a weekend, check if it's a holiday and holidays are not chargeable
        if (isHoliday) {
            return tool.getCharges().isHolidayChargeApplicable();
        }

        // Otherwise, it's a normal weekday
        return tool.getCharges().isWeekdayChargeApplicable();
    }

    private double calculatePreDiscountCharge(int chargeDays, double dailyCharge) {
        return chargeDays * dailyCharge;
    }

    private double calculateDiscountAmount(double preDiscountCharge, int discountPercent) {
        return preDiscountCharge * discountPercent / 100.0;
    }

    private double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
        return preDiscountCharge - discountAmount;
    }

    private RentalAgreement createRentalAgreement(Tool tool, RentalRequest request, LocalDate dueDate,
                                                  int chargeDays, double preDiscountCharge, double discountAmount, double finalCharge) {
        RentalAgreement agreement = new RentalAgreement();
        agreement.setToolCode(tool.getId());
        agreement.setToolType(tool.getName());
        agreement.setToolBrand(tool.getBrand());
        agreement.setRentalDays(request.getRentalDays());
        agreement.setCheckoutDate(request.getCheckoutDate());
        agreement.setDueDate(dueDate);
        agreement.setDailyRentalCharge(tool.getCharges().getDailyCharge());
        agreement.setChargeDays(chargeDays);
        agreement.setPreDiscountCharge(preDiscountCharge);
        agreement.setDiscountPercent(String.format("%d%%", request.getDiscountPercent()));
        agreement.setDiscountAmount(discountAmount);
        agreement.setFinalCharge(String.format("$%.2f", finalCharge));

        return rentalAgreementRepository.save(agreement);
    }
}

