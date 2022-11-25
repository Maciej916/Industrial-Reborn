package com.maciej916.indreb.common.util;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class Cache<T> {
    private T t;
    private final Supplier<T> supplier;

    private Cache(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (t == null) {
            t = supplier.get();
        }

        return t;
    }

    public T invalidate() {
        T tmp = t;
        t = null;
        return tmp;
    }

    public <C> C cast() {
        return (C) get();
    }

    public static <T> Cache<T> create(@Nonnull Supplier<T> supplier) {
        return new Cache<>(supplier);
    }
}

