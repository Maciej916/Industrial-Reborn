package com.maciej916.indreb.common.api.multiblock;

@FunctionalInterface
public interface TriPredicate<F,T,V>{
    boolean test(F x, T y, V z);
}