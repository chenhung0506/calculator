package org.example.controller;

import org.example.service.CalculatorServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CalculatorController {
    private double currentValue = 0.0;
    private final List<String> historyMethod = new ArrayList<>();
    private final List<Double> historyOperand = new ArrayList<>();
    private int historyIndex = -1;

    @Autowired
    private CalculatorServiceLocator calculatorServiceLocator;

    @GetMapping("/{method}/{operand}")
    public double add(@PathVariable String method, @PathVariable double operand) {
        currentValue = calculatorServiceLocator.getSpecificCalculatorService(method)
                .execute(currentValue, operand);

        if (historyIndex < historyMethod.size() - 1) {
            historyMethod.subList(historyIndex + 1, historyMethod.size()).clear();
        }
        historyMethod.add(method);
        historyOperand.add(operand);
        historyIndex++;

        return currentValue;
    }

    @GetMapping("/undo")
    public double undo() {
        if (historyIndex >= 0) {
            int targetIndex = historyIndex--;
            currentValue = calculatorServiceLocator.getSpecificCalculatorService(historyMethod.get(targetIndex))
                    .undo(currentValue, historyOperand.get(targetIndex));
        } return currentValue;
    }

    @GetMapping("/redo")
    public double redo() {
        if (historyIndex < historyMethod.size() - 1) {
            int targetIndex = ++historyIndex;
            currentValue = calculatorServiceLocator.getSpecificCalculatorService(historyMethod.get(targetIndex))
                    .execute(currentValue, historyOperand.get(targetIndex));
        }
        return currentValue;
    }

    @GetMapping("/current")
    public double getCurrentValue() {
        return currentValue;
    }
}
