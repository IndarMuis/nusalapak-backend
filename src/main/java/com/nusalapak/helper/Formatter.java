package com.nusalapak.helper;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@Component
public class Formatter {

    public String priceToIDR(Double price) {
//        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols idrFormat = new DecimalFormatSymbols();
//
//
//        idrFormat.setCurrencySymbol("Rp. ");
//        idrFormat.setMonetaryDecimalSeparator(',');
//        idrFormat.setGroupingSeparator('.');
//        decimalFormat.setDecimalFormatSymbols(idrFormat);
//        return decimalFormat.format(price);

        Locale localeID = new Locale("in", "ID");

        NumberFormat IDRFormat = NumberFormat.getCurrencyInstance(localeID);

        return IDRFormat.format(price);

    }

}
