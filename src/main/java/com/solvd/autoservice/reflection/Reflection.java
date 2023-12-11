package com.solvd.autoservice.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.solvd.autoservice.enums.ConsoleColors.ANSI_GREEN;
import static com.solvd.autoservice.enums.ConsoleColors.ANSI_RESET;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class Reflection {
    private final String CLASS_LOCATION = "com.solvd.autoservice.reflection.MyClass";

    private final Class<MyClass> CLASS = (Class<MyClass>) getClass(CLASS_LOCATION);

    public void runReflection() {
        try {
            Constructor<MyClass> twoParamsConstructor = CLASS
                    .getDeclaredConstructor(String.class);
            Constructor<MyClass> sevenParamsConstructor = CLASS
                    .getDeclaredConstructor(String.class,
                            int.class, long.class, float.class, double.class, char.class, boolean.class);

            MyClass oneParamConstructorInstance = twoParamsConstructor.newInstance("Привет из Reflection API");
            MyClass tenParamsConstructorInstance = sevenParamsConstructor.newInstance("Строка",
                    200_000_000, 500_000_000_000_000L, 1.456f, 1.456789012345678, 'c', true);

            Method printInfo = CLASS.getDeclaredMethod("printInfo");
            printInfo.setAccessible(true);

            MY_LOGGER.info(ANSI_GREEN + "Вызов приватного метода printInfo():" + ANSI_RESET);
            printInfo.invoke(oneParamConstructorInstance);
            Method toStringMethod = CLASS.getDeclaredMethod("toString");

            MY_LOGGER.info(ANSI_GREEN + "Вызов публичного метода toString():" + ANSI_RESET);
            MY_LOGGER.info((toStringMethod.invoke(tenParamsConstructorInstance)));
        } catch (NoSuchMethodException | InstantiationException
                 | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
