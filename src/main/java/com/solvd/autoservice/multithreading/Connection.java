package com.solvd.autoservice.multithreading;

import java.util.Random;

import static com.solvd.autoservice.enums.ConsoleColors.ANSI_RESET;
import static com.solvd.autoservice.enums.ConsoleColors.ANSI_YELLOW;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class Connection implements Runnable {
    private final int connectionNum;
    private static final int RANDOM_NUM_BOUND = 1000;

    public Connection(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    @Override
    public void run() {
        MY_LOGGER.info(ANSI_YELLOW + "Соединение " + connectionNum + " началось" + ANSI_RESET);
        int duration = new Random().nextInt(RANDOM_NUM_BOUND);

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MY_LOGGER.info(ANSI_YELLOW + "Соединение " + connectionNum + " закончилось "
                + "(" + duration + " мс)" + ANSI_RESET);

        ConnectionPool.connectionPool.releaseConnection(this);
    }
}
