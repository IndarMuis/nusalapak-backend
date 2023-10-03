package com.nusalapak.helper;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Component
public class Formatter {

    public String priceToIDR(Double price) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols idrFormat = new DecimalFormatSymbols();

        idrFormat.setCurrencySymbol("Rp. ");
        idrFormat.setMonetaryDecimalSeparator(',');
        idrFormat.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(idrFormat);

        return decimalFormat.format(price);
    }

}
