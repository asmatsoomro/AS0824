package com.as0824.rentaltool.service;

import com.as0824.rentaltool.entity.Charges;
import com.as0824.rentaltool.entity.RentalAgreement;
import com.as0824.rentaltool.entity.Tool;
import com.as0824.rentaltool.model.RentalRequest;
import com.as0824.rentaltool.repository.ToolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalServiceTest {

    @Mock
    private ToolRepository toolRepository;

    @Autowired
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void VerifyThat_LadderRentedDuringTheHoliday_DoesNotHaveHolidayCharge() {
        RentalRequest request = new RentalRequest();
        request.setToolCode("LADW");
        request.setCheckoutDate(LocalDate.parse("2020-07-02"));
        request.setDiscountPercent(10);
        request.setRentalDays(3);

        Tool tool = new Tool("LADW", "Ladder", "Werner",
                new Charges(1.99, true, true, false));
        when(toolRepository.findById("LADW")).thenReturn(java.util.Optional.of(tool));

        RentalAgreement agreement = rentalService.checkout(request);
        assertNotNull(agreement);
        assertEquals(2, agreement.getChargeDays());
        assertEquals("$3.58",agreement.getFinalCharge());
        assertEquals("10%", agreement.getDiscountPercent());
        assertEquals("Ladder", agreement.getToolType());
    }

    @Test
    void VerifyThat_ChainsawRentedDuringTheWeekendAndHoliday_DoesNotHaveWeekendChargeButAppliesHolidayCharge() {
        RentalRequest request = new RentalRequest();
        request.setToolCode("CHNS");
        request.setCheckoutDate(LocalDate.parse("2015-07-02"));
        request.setDiscountPercent(25);
        request.setRentalDays(5);

        Tool tool = new Tool("CHNS", "Chainsaw", "Stihl",
                new Charges(1.49,  true, true, false));
        when(toolRepository.findById("CHNS")).thenReturn(java.util.Optional.of(tool));

        RentalAgreement agreement = rentalService.checkout(request);
        assertNotNull(agreement);
        assertEquals(4, agreement.getChargeDays());
        assertEquals("$4.47",agreement.getFinalCharge());
        assertEquals("25%", agreement.getDiscountPercent());
        assertEquals("Chainsaw", agreement.getToolType());
    }


    @Test
    void VerifyThat_JackHammerDRentedDuringTheWeekendAndHoliday_DoesNotHaveWeekendChargeOrHolidayChargeApplied() {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKD");
        request.setCheckoutDate(LocalDate.parse("2015-09-03"));
        request.setDiscountPercent(0);
        request.setRentalDays(6);

        Tool tool = new Tool("JAKD", "Jackhammer", "DeWalt",
                new Charges(2.99, true, false, false));
        when(toolRepository.findById("JAKD")).thenReturn(java.util.Optional.of(tool));

        RentalAgreement agreement = rentalService.checkout(request);
        assertNotNull(agreement);
        assertEquals(3, agreement.getChargeDays());
        assertEquals("$8.97",agreement.getFinalCharge());
        assertEquals("0%", agreement.getDiscountPercent());
        assertEquals("Jackhammer", agreement.getToolType());
    }

    @Test
    void VerifyThat_JackHammerRRentedDuringTheWeekendAndHoliday_DoesNotHaveWeekendChargeOrObservedHolidayChargeApplied() {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKR");
        request.setCheckoutDate(LocalDate.parse("2015-07-02"));
        request.setDiscountPercent(0);
        request.setRentalDays(9);

        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid",
                new Charges(2.99, true, false, false));
        when(toolRepository.findById("JAKR")).thenReturn(java.util.Optional.of(tool));

        RentalAgreement agreement = rentalService.checkout(request);
        assertNotNull(agreement);
        assertEquals(6, agreement.getChargeDays());
        assertEquals("$17.94",agreement.getFinalCharge());
        assertEquals("0%", agreement.getDiscountPercent());
        assertEquals("Jackhammer", agreement.getToolType());
    }

    @Test
    void VerifyThat_JackHammerRRentedDuringTheWeekendAndHoliday_DoesNotHaveWeekendChargeOrObservedHolidayChargeApplied_AndSpecifiedDiscountPercentApplied() {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKR");
        request.setCheckoutDate(LocalDate.parse("2015-07-02"));
        request.setDiscountPercent(50);
        request.setRentalDays(4);

        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid",
                new Charges(2.99, true, false, false));
        when(toolRepository.findById("JAKR")).thenReturn(java.util.Optional.of(tool));

        RentalAgreement agreement = rentalService.checkout(request);
        assertNotNull(agreement);
        assertEquals(1, agreement.getChargeDays());
        assertEquals("$1.50",agreement.getFinalCharge());
        assertEquals("50%", agreement.getDiscountPercent());
        assertEquals("Jackhammer", agreement.getToolType());
    }

}
