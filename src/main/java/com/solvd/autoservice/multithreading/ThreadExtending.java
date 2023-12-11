package com.solvd.autoservice.multithreading;

import java.util.Random;

import static com.solvd.autoservice.enums.ConsoleColors.ANSI_RESET;
import static com.solvd.autoservice.enums.ConsoleColors.ANSI_YELLOW;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class ThreadExtending extends Thread {
    private final String connectionType;
    private static final int RANDOM_NUM_BOUND = 1000;

    public ThreadExtending(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public void run() {
        MY_LOGGER.info(ANSI_YELLOW + "Соединение типа " + connectionType + " началось" + ANSI_RESET);
        int duration = new Random().nextInt(RANDOM_NUM_BOUND);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MY_LOGGER.info(ANSI_YELLOW + "Соединение типа " + connectionType + " закончилось "
                + "(" + duration + " мс)" + ANSI_RESET);
    }
}
