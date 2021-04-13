package com.solution.vasilieva;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int[] array = {1, 6, 45, 19, 9};
        BaseTypes baseTypes = new BaseTypes();
        LOG.info("Exponential form: " + baseTypes.toggleScientificNotation("0.2562666"));
        LOG.info("normal form: " + baseTypes.toggleScientificNotation("1e+6"));
        LOG.info("sort array: " + Arrays.toString(baseTypes.sort(array)));
        LOG.info("average: " + baseTypes.arithmeticMean(array));
        LOG.info("Countries form: " + baseTypes.format(232.561, "UA"));
        LOG.info("plus: " + baseTypes.plus("18", "26.3254"));
        LOG.info("minus: " + baseTypes.minus("4565.234", "23"));
        LOG.info("multiplication: " + baseTypes.mul("234", "23"));
        LOG.info("division: " + baseTypes.div("45", "5"));

    }
}
