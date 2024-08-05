package com.as0824.rentaltool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.as0824.rentaltool.entity.RentalAgreement;
import com.as0824.rentaltool.model.RentalRequest;
import com.as0824.rentaltool.service.RentalService;
import com.as0824.rentaltool.service.ToolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WebMvcTest(RentalController.class)
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolService toolService;

    @MockBean
    private RentalService rentalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void VerifyThat_RentalRequest_Returns_ValidRentalAgreement() throws Exception {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKR");
        request.setRentalDays(4);
        request.setDiscountPercent(25);
        request.setCheckoutDate(LocalDate.parse("2020-07-02"));
        RentalAgreement agreement = new RentalAgreement();
        agreement.setToolCode("JAKR");
        agreement.setRentalDays(4);
        agreement.setDiscountPercent("25%");

        when(rentalService.checkout(request)).thenReturn(agreement);

        mockMvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void VerifyThat_RentalDaysLessThanOne_ReturnsRuntimeException_And_ErrorMessage() throws Exception {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKR");
        request.setRentalDays(0);
        request.setDiscountPercent(25);
        request.setCheckoutDate(LocalDate.parse("2020-07-02"));


        mockMvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Rental day count must be greater than 0", result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    void VerifyThat_DiscountPercentageGreaterThan100_ReturnsRuntimeException_And_ErrorMessage() throws Exception {
        RentalRequest request = new RentalRequest();
        request.setToolCode("JAKR");
        request.setRentalDays(4);
        request.setDiscountPercent(105);
        request.setCheckoutDate(LocalDate.parse("2020-07-02"));


        mockMvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Discount percent must be between 0% and 100%", result.getResolvedException().getMessage()))
                .andDo(print());
    }
}
