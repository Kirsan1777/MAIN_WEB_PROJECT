package by.nikita.web.model.dao;

import by.nikita.web.exception.ConnectionDataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {


    private static final String RESOURCE_BUNDLE = "property.database";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    private static final String DRIVER = "driver";
    private static final int POOL_SIZE = 8;
    private static final ConnectionPool instance = new ConnectionPool();
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> busyConnections;


    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE);
            String driver = rb.getString(DRIVER);
            Class.forName(driver);
            String url = rb.getString(URL);
            String username = rb.getString(USERNAME);
            String password = rb.getString(PASSWORD);
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            busyConnections = new ArrayDeque<>(POOL_SIZE);

            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Can't create connection pool", e);
        }
    }

    public Connection getConnection() throws ConnectionDataBaseException {
        try {
            ProxyConnection connection = freeConnections.take();
            busyConnections.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionDataBaseException("Can't get connection", e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && busyConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            //LOGGER.log(Level.WARN, "Invalid connection to release");
        }
    }

    public void deactivatePool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().fullyClose();
            }
            //deregisterDriver();
        } catch (SQLException | InterruptedException e) {
            //LOGGER.log(Level.ERROR, "Can't destroy pool");
        }
    }

    /*private void deregisterDriver() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }*/
}
