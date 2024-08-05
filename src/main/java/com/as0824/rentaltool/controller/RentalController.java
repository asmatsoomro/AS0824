package com.as0824.rentaltool.controller;

import com.as0824.rentaltool.entity.RentalAgreement;
import com.as0824.rentaltool.entity.Tool;
import com.as0824.rentaltool.exception.DiscountPercentageException;
import com.as0824.rentaltool.exception.RentalDayCountException;
import com.as0824.rentaltool.model.RentalRequest;
import com.as0824.rentaltool.service.RentalService;
import com.as0824.rentaltool.service.ToolService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentalController {

    private final ToolService toolService;
    private final RentalService rentalService;

    public RentalController(ToolService toolService, RentalService rentalService) {
        this.toolService = toolService;
        this.rentalService = rentalService;
    }


    @PostMapping("/checkout")
    public ResponseEntity<RentalAgreement> checkout(@RequestBody RentalRequest request) {
        if (request.getRentalDays() <= 0) {
            throw new RentalDayCountException("Rental day count must be greater than 0");
        }
        if (request.getDiscountPercent() < 0 || request.getDiscountPercent() > 100) {
            throw new DiscountPercentageException("Discount percent must be between 0% and 100%");
        }

        RentalAgreement agreement = rentalService.checkout(request);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(agreement);
    }

    @GetMapping("/tools")
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }

    @PostMapping("/add")
    public ResponseEntity<Tool> addTool(@RequestBody Tool tool) {
        Tool savedTool = toolService.addTool(tool);
        return ResponseEntity.ok(savedTool);
    }
}
