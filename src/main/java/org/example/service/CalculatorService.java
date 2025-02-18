package org.example.service;

import org.example.enums.CalculatorEnum;

public interface CalculatorService {
    CalculatorEnum getCalculatorType();
    double execute(double currentValue, double operand);
    double undo(double currentValue, double operand);
}