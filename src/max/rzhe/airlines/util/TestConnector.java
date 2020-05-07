package max.rzhe.airlines.util;


import max.rzhe.airlines.util.connectionpool.ConnectionPool;

import java.sql.Connection;

import max.rzhe.airlines.util.connectionpool.PoolException;

public class TestConnector {
    public static Connection connect() {
        Connection conn = null;

        try {
            ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/new-airlines?serverTimezone=Europe/Moscow",
                    "root", "adminmax", 1, 100);
            conn = ConnectionPool.getInstance().getConnection();
        } catch (PoolException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
