package org.example.enums;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum CalculatorEnum {
    DEFAULT(0, "Feature list"),
    ADD(1, "add"),
    SUBTRACT(2, "subtract"),
    MULTIPLY(3, "multiply"),
    DIVIDE(4, "divide"),
    ;

    Integer code;
    String value;

    CalculatorEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public static CalculatorEnum fromCode(Integer code) {
        for (CalculatorEnum calculatorEnum : values()) {
            if (calculatorEnum.getCode().equals(code)) {
                return calculatorEnum;
            }
        }
        return null;
    }


    public static CalculatorEnum fromValue(String value) {
        for (CalculatorEnum calculatorEnum : values()) {
            if (calculatorEnum.getValue().equalsIgnoreCase(value)) {
                return calculatorEnum;
            }
        }
        return CalculatorEnum.DEFAULT;
    }

    public static List<String> getFeatureList() {
        return Stream.of(CalculatorEnum.values())
                .map(CalculatorEnum::getValue)
                .collect(Collectors.toList());
    }
}
