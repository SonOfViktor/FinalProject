package edu.epam.task6.model.pool;

import edu.epam.task6.exception.LocalPropertyException;
import edu.epam.task6.util.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static final ReentrantLock locker = new ReentrantLock();
    private static final AtomicBoolean create = new AtomicBoolean(false);

    private static final int CONNECTION_POOL_SIZE = 8;
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;

    public static final String DATABASE_PROPERTY_PATH = "database.properties";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_DRIVER = "driverClassName";

    private ConnectionPool() {
        try{
            PropertyReader reader = new PropertyReader();
            Properties properties = reader.read(DATABASE_PROPERTY_PATH);
            String url = properties.getProperty(DATABASE_URL);
            String driver = properties.getProperty(DATABASE_DRIVER);
            Class.forName(driver);
            freeConnections = new LinkedBlockingDeque<>(CONNECTION_POOL_SIZE);
            busyConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
        } catch (SQLException | ClassNotFoundException | LocalPropertyException e) {
            logger.fatal("Fatal error during connection pool creation.", e);
            throw new RuntimeException("Fatal error during connection pool creation.", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Error while getting connection.", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean result = true;
        if(connection instanceof ProxyConnection && busyConnections.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Error: wrong connection to put in free connections.", e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.error("Error: wrong connection to release.");
            result = false;
        }
        return result;
    }

    public void destroyPool() {
        try {
            //TODO заменить на обычный цикл
            for (ProxyConnection freeConnection : freeConnections) {
                freeConnection.reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException e) {
            logger.error("Error while destroying connection pool.", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while(DriverManager.getDrivers().hasMoreElements()){
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
