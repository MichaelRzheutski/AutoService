package com.solvd.autoservice.multithreading;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private final int connectionPoolSize;
    private final Queue<Connection> connectionQueue = new ConcurrentLinkedQueue<>();
    private static final int CONNECTION_NUM = 5;
    private static volatile ConnectionPool instance;
    static volatile ConnectionPool connectionPool = getInstance(CONNECTION_NUM);

    private ConnectionPool(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;

        for (int i = 0; i < this.connectionPoolSize; i++) {
            connectionQueue.add(new Connection(i + 1));
        }

    }

    public static synchronized ConnectionPool getInstance(int connectionPoolSize) {
        if (instance == null) {
            instance = new ConnectionPool(connectionPoolSize);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        while (connectionQueue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionQueue.remove();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connectionQueue.size() < this.connectionPoolSize) {
            connectionQueue.add(connection);
        }
        notify();
    }

    public synchronized int getConnectionPoolSize() {
        return this.connectionPoolSize;
    }

    public synchronized int getAvailableConnections() {
        return connectionQueue.size();
    }
}
