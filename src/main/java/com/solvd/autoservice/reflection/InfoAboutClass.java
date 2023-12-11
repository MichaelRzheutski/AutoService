package com.solvd.autoservice.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static com.solvd.autoservice.enums.ConsoleColors.*;

public class InfoAboutClass {
    private final String CLASS_LOCATION = "com.solvd.autoservice.reflection.MyClass";
    private final Class<?> CLASS = geClassInfo(CLASS_LOCATION);

    // Setup Logger log4j2
    static {
        System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public void getInfoAboutClass() {
        LOGGER.info(ANSI_GREEN + "Информация о классе:" + ANSI_RESET);

        // Information about Class
        LOGGER.info(ANSI_GREEN + "Имя пакета: " + ANSI_RESET
                + ANSI_YELLOW + CLASS.getPackageName() + ANSI_RESET);
        LOGGER.info(ANSI_GREEN + "Полное имя класса: " + ANSI_RESET
                + ANSI_YELLOW + CLASS.getCanonicalName() + ANSI_RESET);
        LOGGER.info(ANSI_GREEN + "Краткое имя класса: " + ANSI_RESET
                + ANSI_YELLOW +  CLASS.getSimpleName() + "\n" + ANSI_RESET);

        // Information about Fields
        Field[] fields = getFieldsArray(CLASS);
        LOGGER.info(ANSI_GREEN + "Общее количество полей: " + ANSI_RESET
                + ANSI_YELLOW + Arrays.stream(fields).count() + ANSI_RESET);
        LOGGER.info(ANSI_GREEN + "Модификаторы доступа полей: " + ANSI_RESET);
        Arrays.stream(fields)
                .forEach(field -> LOGGER.info(ANSI_YELLOW + "["
                        + Modifier.toString(field.getModifiers()) + "] "
                        + ANSI_RESET + field.getName()));
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Типы полей: " + ANSI_RESET);
        Arrays.stream(fields)
                .forEach(field -> LOGGER.info(ANSI_YELLOW + "["
                        + field.getType().getSimpleName()+ "] "
                        + ANSI_RESET + field.getName()));
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Финальные поля: " + ANSI_RESET);
        Arrays.stream(fields)
                .filter(field -> (field.getModifiers() & Modifier.FINAL) > 0)
                .forEach(field -> LOGGER.info(field.getName()));
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Статические поля: " + ANSI_RESET);
        Arrays.stream(fields)
                .filter(field -> (field.getModifiers() & Modifier.STATIC) > 0)
                .forEach(field -> LOGGER.info(field.getName()));
        System.out.println();

        // Information about Methods
        Method[] methods = getMethodsArray(CLASS);
        LOGGER.info(ANSI_GREEN + "Общее количество методов: " + ANSI_RESET
                + ANSI_YELLOW + Arrays.stream(methods).count() + ANSI_RESET);
        LOGGER.info(ANSI_GREEN + "Модификаторы доступа методов: " + ANSI_RESET);
        Arrays.stream(methods)
                .forEach(method -> LOGGER.info(ANSI_YELLOW + "["
                        + Modifier.toString(method.getModifiers())
                        + "] " + ANSI_RESET + method.getName()));
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Возвращаемые типы методов: " + ANSI_RESET);
        Arrays.stream(methods)
                .forEach(method -> LOGGER.info(ANSI_YELLOW + "["
                        + method.getReturnType().getSimpleName()
                        + "] " + ANSI_RESET + method.getName()));
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Типы параметров методов: " + ANSI_RESET);
        Arrays.stream(methods)
                .forEach(method -> LOGGER.info(ANSI_YELLOW
                        + Arrays.toString(Arrays.stream(method.getParameterTypes())
                        .map(Class::getSimpleName).toArray())
                        + ANSI_RESET + " " + method.getName()));
        System.out.println();

        // Information about Constructors
        Constructor<?>[] constructors = getConstructorsArray(CLASS);
        LOGGER.info(ANSI_GREEN + "Общее количество конструкторов: "
                + ANSI_RESET + Arrays.stream(constructors).count());

        LOGGER.info(ANSI_GREEN + "Модификаторы доступа конструкторов: " + ANSI_RESET);
        AtomicInteger constructorNum = new AtomicInteger();
        Arrays.stream(constructors)
                .forEach(constructor -> LOGGER.info(ANSI_YELLOW + "["
                        + Modifier.toString(constructor.getModifiers()) + "] " + ANSI_RESET));
        constructorNum.set(0);
        System.out.println();

        LOGGER.info(ANSI_GREEN + "Типы параметров конструктора: " + ANSI_RESET);
        Arrays.stream(constructors)
                .forEach(constructor -> LOGGER.info(ANSI_YELLOW
                        + Arrays.toString(Arrays.stream(constructor.getParameterTypes())
                        .map(Class::getSimpleName)
                        .toArray()) + ANSI_RESET));
        System.out.println();
    }

    private Class<?> geClassInfo(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private Field[] getFieldsArray(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    private Method[] getMethodsArray(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    private Constructor<?>[] getConstructorsArray(Class<?> clazz) {
        return clazz.getDeclaredConstructors();
    }
}
