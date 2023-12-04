package com.solvd.autoservice.myfunctionalinterfaces;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}
