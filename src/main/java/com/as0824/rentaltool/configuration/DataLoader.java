package com.as0824.rentaltool.configuration;

import com.as0824.rentaltool.entity.Charges;
import com.as0824.rentaltool.entity.Holiday;
import com.as0824.rentaltool.entity.Tool;
import com.as0824.rentaltool.repository.HolidayRepository;
import com.as0824.rentaltool.repository.ToolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ToolRepository toolRepository) {
        return args -> {
            toolRepository.save(new Tool("CHNS", "Chainsaw", "Stihl",
                    new Charges(1.49,  true, true, false)));
            toolRepository.save(new Tool("LADW", "Ladder", "Werner",
                    new Charges(1.99, true, true, false)));
            toolRepository.save(new Tool("JAKD", "Jackhammer", "DeWalt",
                    new Charges(2.99, true, false, false)));
            toolRepository.save(new Tool("JAKR", "Jackhammer", "Ridgid",
                    new Charges(2.99, true, false, false)));
        };
    }

    @Bean
    CommandLineRunner initDatabase(HolidayRepository repository) {
        return args -> {
            repository.save(new Holiday("Independence Day", LocalDate.of(2023, 7, 4)));
            repository.save(new Holiday("Labor Day", LocalDate.of(2023, 9, 4)));
            // Add more holidays as needed
        };
    }
}

