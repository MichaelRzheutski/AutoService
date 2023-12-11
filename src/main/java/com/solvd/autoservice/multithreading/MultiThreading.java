package com.solvd.autoservice.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.solvd.autoservice.enums.ConsoleColors.*;
import static com.solvd.autoservice.helpers.MyLogger.MY_LOGGER;

public class MultiThreading {
    private static final int THREAD_RUNNABLE_SLEEP_MILLISECONDS = 1000;
    private static final int MULTITHREADING_THREADS_NUM = 4;
    private static final int CONNECTION_POOL_THREADS_NUM = 7;
    private static final int THREAD_POOL_THREADS_NUM = 5;
    private static final int COMPLETABLE_FEATURE_THREADS_NUM = 5;

    public void runMultiThreading() {
        MY_LOGGER.info(ANSI_GREEN + "Проверка реализации Thread и Runnable:" + ANSI_RESET);
        checkThreadRunnable();

        MY_LOGGER.info(ANSI_GREEN + "Проверка многопоточности:" + ANSI_RESET);
        checkMultithreading();

        MY_LOGGER.info(ANSI_GREEN + "Проверка ConnectionPool:" + ANSI_RESET);
        checkConnectionPool();

        MY_LOGGER.info(ANSI_GREEN + "Проверка ThreadPool:" + ANSI_RESET);
        checkThreadPool();

        MY_LOGGER.info(ANSI_GREEN + "Проверка CompletableFuture:" + ANSI_RESET);
        checkCompletableFuture();

        MY_LOGGER.info(ANSI_GREEN + "Проверка CompletionStage:" + ANSI_RESET);
        checkCompletionStage();
    }

    private void checkThreadRunnable() {
        RunnableImplementation runnableImplementation = new RunnableImplementation("Runnable");
        Thread threadRunnable = new Thread(runnableImplementation);
        threadRunnable.start();

        ThreadExtending threadExtending = new ThreadExtending("Thread");
        Thread threadThread = new Thread(threadExtending);
        threadThread.start();

        try {
            Thread.sleep(THREAD_RUNNABLE_SLEEP_MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена\n" + ANSI_RESET);
    }

    private void checkMultithreading() {
        Connection connection1 = ConnectionPool.connectionPool.getConnection();
        Thread thread1 = new Thread(connection1);
        thread1.start();

        Connection connection2 = ConnectionPool.connectionPool.getConnection();
        Thread thread2 = new Thread(connection2);
        thread2.start();

        Connection connection3 = ConnectionPool.connectionPool.getConnection();
        Thread thread3 = new Thread(connection3);
        thread3.start();

        ExecutorService executor = Executors.newFixedThreadPool(MULTITHREADING_THREADS_NUM);
        for (int i = 1; i <= MULTITHREADING_THREADS_NUM; i++) {
            executor.submit(ConnectionPool.connectionPool.getConnection());
        }

        executor.shutdown();

        while (true) {
            if (ConnectionPool.connectionPool.getAvailableConnections() ==
                    ConnectionPool.connectionPool.getConnectionPoolSize()) {
                break;
            }
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена\n" + ANSI_RESET);
    }

    private void checkConnectionPool() {
        for (int i = 1; i <= CONNECTION_POOL_THREADS_NUM; i++) {
            Connection connection = ConnectionPool.connectionPool.getConnection();
            Thread threadConnection = new Thread(connection);
            threadConnection.start();
        }

        while (true) {
            if (ConnectionPool.connectionPool.getAvailableConnections() ==
                    ConnectionPool.connectionPool.getConnectionPoolSize()) {
                break;
            }
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена\n" + ANSI_RESET);
    }

    private void checkThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_THREADS_NUM);
        for (int i = 1; i <= CONNECTION_POOL_THREADS_NUM; i++) {
            executor.submit(new RunnableImplementation(Integer.toString(i)));
        }
        executor.shutdown();

        while (true) {
            if (executor.isTerminated()) {
                break;
            }
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена\n" + ANSI_RESET);
    }

    private void checkCompletableFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(COMPLETABLE_FEATURE_THREADS_NUM);
        
        for (int i = 1; i <= CONNECTION_POOL_THREADS_NUM; i++) {
            CompletableFuture.runAsync(new Connection(i), executor);
        }
        executor.shutdown();

        while (true) {
            if (executor.isTerminated()) {
                break;
            }
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена\n" + ANSI_RESET);
    }

    private void checkCompletionStage() {
        for (int i = 1; i <= CONNECTION_POOL_THREADS_NUM; i++) {
            Connection connection = ConnectionPool.connectionPool.getConnection();
            CompletableFuture.runAsync(connection);
        }

        while (true) {
            if (ConnectionPool.connectionPool.getAvailableConnections() ==
                    ConnectionPool.connectionPool.getConnectionPoolSize()) {
                break;
            }
        }

        MY_LOGGER.info(ANSI_GREEN + "Проверка завершена" + ANSI_RESET);
    }
}
