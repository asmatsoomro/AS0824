package com.as0824.rentaltool.service;

import com.as0824.rentaltool.entity.Holiday;
import com.as0824.rentaltool.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HolidayService {

    private static final Logger logger = LoggerFactory.getLogger(HolidayService.class);


    @Autowired
    private HolidayRepository holidayRepository;

    public boolean isHoliday(LocalDate date) {
        List<Holiday> holidays = holidayRepository.findByDate(date);

        if (!holidays.isEmpty()) {
            return true;
        }

        // Check for dynamic observance of July 4th
        if (date.getMonthValue() == 7) {
            if (date.getDayOfMonth() == 4) {
                return true;
            } else if ((date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() == 5) ||
                    (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 3)) {
                logger.info("4th of July happens to be on {}", date.getDayOfWeek());
                return true;
            }
        }

        // Check for Labor Day (first Monday in September)
        if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
            return true;
        }

        return false;
    }
}

