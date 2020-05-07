package max.rzhe.airlines.service.transaction;

import max.rzhe.airlines.service.exception.ServiceException;

public interface TransactionHandler {
    void runWithTransaction(TransactionManager transactionManager) throws ServiceException;
}
