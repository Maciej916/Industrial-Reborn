package com.maciej916.indreb.common.util;

public class UnitHelper {

    private static final double[] lut = {1e-24, 1e-21, 1e-18, 1e-15, 1e-12, 1e-09, 1e-06, 0.001, 1, 1000, 1e+06, 1e+09, 1e+12, 1e+15, 1e+18, 1e+21, 1e+24};
    private static final String[] pre = {"y", "z", "a", "f", "p", "n", "u", "m", "", "k", "M", "G", "T", "P", "E", "Z", "Y"};

    public static String formatNumber(double number, int maxDecimalPlaces, String unit) {

        int ix = 0;
        for (int i = 0; i < lut.length; i++) {
            if (number >= lut[i]) {
                ix = i;
            } else {
                break;
            }
        }

        String prefix = "";
        double resNumber = number;
        if (lut[ix] != 1 && resNumber != 0) {
            prefix = pre[ix];
            resNumber = number/lut[ix];
        }

        String formatted = String.format("%." + maxDecimalPlaces + "f", resNumber);
        formatted = formatted.replaceAll(",.[0]+$", "");

        return String.format("%s %s%s", formatted, prefix, unit);
    }

    public static String formatNumber(int number, int decimalPlaces, String unit) {

        int ix = 0;
        for (int i = 0; i < lut.length; i++) {
            if (number >= lut[i]) {
                ix = i;
            } else {
                break;
            }
        }

        String prefix = "";
        double resNumber = number;
        if (lut[ix] != 1) {
            prefix = pre[ix];
            resNumber = number/lut[ix];
        }

        String formatted = String.format("%." + decimalPlaces + "f", resNumber);
        formatted = formatted.replaceAll(",.[0]+$", "");

        return String.format("%s %s%s", formatted, prefix, unit);
    }



}
