package com.maciej916.indreb.common.util;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.List;

public class TextComponentUtil {

    private static DecimalFormat df = new DecimalFormat("###,##0");

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, " k");
        suffixes.put(1_000_000L, " M");
        suffixes.put(1_000_000_000L, " G");
        suffixes.put(1_000_000_000_000L, " T");
        suffixes.put(1_000_000_000_000_000L, " P");
        suffixes.put(1_000_000_000_000_000_000L, " E");
    }

    private static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10);
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static String getFormattedEnergyUnit(float energy) {
        return format((long) energy);
    }

    public static String getFormattedLong(float energy) {
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
        customSymbol.setGroupingSeparator(' ');
        df.setDecimalFormatSymbols(customSymbol);

        return df.format(energy);
    }

    public static MutableComponent build(MutableComponent... components) {
        TranslatableComponent result = new TranslatableComponent("");
        for (MutableComponent component : components) {
            result.append(component);
        }
        return result;
    }

}
