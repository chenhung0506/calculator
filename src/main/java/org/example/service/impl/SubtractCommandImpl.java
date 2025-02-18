package org.example.service.impl;

import org.example.enums.CalculatorEnum;
import org.example.service.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class SubtractCommandImpl implements CalculatorService {
    @Override
    public CalculatorEnum getCalculatorType() {
        return CalculatorEnum.SUBTRACT;
    }
    @Override
    public double execute(double currentValue, double operand) {
        return currentValue -= operand;
    }
    @Override
    public double undo(double currentValue, double operand) {
        return currentValue += operand;
    }
}
