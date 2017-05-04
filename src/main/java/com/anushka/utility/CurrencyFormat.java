package com.anushka.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by rxd2095 on 5/3/17.
 */
public class CurrencyFormat {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
