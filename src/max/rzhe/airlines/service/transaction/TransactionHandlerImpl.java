package max.rzhe.airlines.service.transaction;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.service.exception.TransactionException;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandlerImpl implements TransactionHandler {
    private static Logger logger = (Logger) LoggerFactory.getLogger(TransactionHandlerImpl.class);
    private Connection connection;

    private Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void runWithTransaction(TransactionManager manager) throws ServiceException {
        try {
            start();
            manager.execute();
            commit();
        } catch (TransactionException e) {
            try {
                rollback();
            } catch (TransactionException e1) {
                throw new TransactionException("Rollback failed: ", e1);
            }
        }
    }

    private void start() throws TransactionException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Error while starting transaction. Caused by: {}", e.getMessage());
            throw new TransactionException(e);
        }
    }

    private void commit() throws TransactionException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error during transaction commitment. Caused by: {}", e.getMessage());
            throw new TransactionException(e);
        }
    }

    private void rollback() throws TransactionException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error while rolling back transaction. Caused by: {}", e.getMessage());
            throw new TransactionException(e);
        }
    }
}
