package org.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceLocator {
    @Autowired
    private List<CalculatorService> calculatorService;

    public CalculatorService getSpecificCalculatorService(String value) {
        return calculatorService.stream()
                .filter(specificCalculatorService -> specificCalculatorService.getCalculatorType().getValue().equals(value))
                .findFirst()
                .orElseThrow(()->new RuntimeException("No calculate method found"));
    }
}
