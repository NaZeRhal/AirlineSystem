package max.rzhe.airlines.util.connectionpool;

import max.rzhe.airlines.ioc.Factory;
import max.rzhe.airlines.ioc.IoCException;
import max.rzhe.airlines.util.TestConnector;

import java.sql.Connection;

public class ConnectionFactory implements Factory<Connection> {
    @Override
    public Connection get(String key) throws IoCException {
        try {
            return ConnectionPool.getInstance().getConnection();
//            return TestConnector.connect();

        } catch (PoolException e) {
            throw new IoCException(e);
        }
    }
}
