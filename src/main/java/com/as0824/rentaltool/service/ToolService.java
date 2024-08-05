package com.as0824.rentaltool.service;

import com.as0824.rentaltool.entity.Charges;
import com.as0824.rentaltool.entity.Tool;
import com.as0824.rentaltool.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public Tool getToolById(String code) {
        return toolRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Tool not found"));
    }

    public Tool addTool(Tool toolDto) {
        Charges chargesEntity = new Charges(
                toolDto.getCharges().getDailyCharge(),
                toolDto.getCharges().isWeekdayChargeApplicable(),
                toolDto.getCharges().isWeekendChargeApplicable(),
                toolDto.getCharges().isHolidayChargeApplicable()
        );
        Tool toolEntity = new Tool(
                toolDto.getId(),
                toolDto.getName(),
                toolDto.getBrand(),
               chargesEntity
        );

        return toolRepository.save(toolEntity);
    }
}

