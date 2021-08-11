package edy.epam.task6.model.validator;

import java.math.BigDecimal;

public class MinMaxRangeValidator {

    private MinMaxRangeValidator(){};

    public static boolean validate(BigDecimal min, BigDecimal max) {
        boolean result = false;
        if (min.intValue() > 0 || max.intValue() > 0 && min.intValue() < max.intValue()) {
            result = true;
        }
        return result;
    }
}
