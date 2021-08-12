package edy.epam.task6.model.validator;

import java.math.BigDecimal;

public class MinMaxRangeValidator {

    private MinMaxRangeValidator(){};

    public static boolean validate(BigDecimal min, BigDecimal max) {
        boolean result = false;
        if (min.doubleValue() < max.doubleValue() && min.doubleValue() > 0) {
            result = true;
        }
        return result;
    }
}
