package org.example.service.impl;

import org.example.enums.CalculatorEnum;
import org.example.service.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class DivideCommandImpl implements CalculatorService {
    @Override
    public CalculatorEnum getCalculatorType() {
        return CalculatorEnum.DIVIDE;
    }
    @Override
    public double execute(double currentValue, double operand) {
        if (operand == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return currentValue /= operand;
    }
    @Override
    public double undo(double currentValue, double operand) {
        return currentValue *= operand;
    }
}
