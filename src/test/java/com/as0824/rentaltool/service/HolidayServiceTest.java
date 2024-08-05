package com.as0824.rentaltool.service;

import com.as0824.rentaltool.entity.Holiday;
import com.as0824.rentaltool.repository.HolidayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HolidayServiceTest {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidayService holidayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsHoliday_RegularDay() {
        LocalDate date = LocalDate.of(2023, 7, 5); // Not a holiday
        when(holidayRepository.findByDate(date)).thenReturn(Collections.emptyList());
        assertFalse(holidayService.isHoliday(date));
    }

    @Test
    void testIsHoliday_IndependenceDay() {
        LocalDate date = LocalDate.of(2023, 7, 4);
        when(holidayRepository.findByDate(date)).thenReturn(Collections.singletonList(new Holiday("Independence Day", date)));
        assertTrue(holidayService.isHoliday(date));
    }

    @Test
    void testIsHoliday_ObservedIndependenceDay_Monday() {
        LocalDate date = LocalDate.of(2021, 7, 5); // Monday observation if July 4 is a Sunday
        when(holidayRepository.findByDate(date)).thenReturn(Collections.emptyList());
        assertTrue(holidayService.isHoliday(date));
    }

    @Test
    void testIsHoliday_ObservedIndependenceDay_Friday() {
        LocalDate date = LocalDate.of(2020, 7, 3); // Friday observation if July 4 is a Saturday
        when(holidayRepository.findByDate(date)).thenReturn(Collections.emptyList());
        assertTrue(holidayService.isHoliday(date));
    }

    @Test
    void testIsHoliday_LaborDay() {
        LocalDate date = LocalDate.of(2023, 9, 4); // First Monday of September
        when(holidayRepository.findByDate(date)).thenReturn(Collections.singletonList(new Holiday("Labor Day", date)));
        assertTrue(holidayService.isHoliday(date));
    }
}

