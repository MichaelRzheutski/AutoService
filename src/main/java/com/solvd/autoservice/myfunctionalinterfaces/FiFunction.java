package com.solvd.autoservice.myfunctionalinterfaces;

@FunctionalInterface
public interface FiFunction<T, U, V, W, X, R>  {
    R apply(T t, U u, V v, W w, X x);
}
